import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class GameWorld {
    private GameGraphics graphicSystem;
    private  InputSystem inputSystem;
    private final GamePhysics physicsSystem;
    private UserInput userInput;
    private final Avatar avatar = new Avatar((double) GamePanel.WIDTH /2, (double) GamePanel.HEIGHT /2);

    public static Vector<GameObject> gameObjects = new Vector<>();
    public static Vector<Projectile> projectiles = new Vector<>();

    private final HealthBar healthBar = HealthBar.getInstance();

    protected void init(){
        gameObjects.add(avatar);
        Timer timer = new Timer();

        // Create a timer task that adds a new dot to the game objects list.
        TimerTask addDotTask = new TimerTask() {
                @Override
                public void run() {
                    // Generate a random case.
                    int caseNumber = (int) (Math.random() * 4);

                    // Generate a random coordinate for the dot based on the case.
                    double x, y;
                    double dx, dy, angle;
                    switch (caseNumber) {
                        case 0:
                            x = 0;
                            y = Math.random() * GamePanel.HEIGHT;
                            dx = (double) GamePanel.WIDTH / 2 - x;
                            dy = (double) GamePanel.HEIGHT / 2 - y;
                            angle = Math.atan2(dy, dx);
                            break;
                        case 1:
                            x = GamePanel.WIDTH - 1;
                            y = Math.random() * GamePanel.HEIGHT;
                            dx = (double) GamePanel.WIDTH / 2 - x;
                            dy = (double) GamePanel.HEIGHT / 2 - y;
                            angle = Math.atan2(dy, dx);
                            break;
                        case 2:
                            y = 0;
                            x = Math.random() * GamePanel.WIDTH;
                            dx = (double) GamePanel.WIDTH / 2 - x;
                            dy = (double) GamePanel.HEIGHT / 2 - y;
                            angle = Math.atan2(dy, dx);
                            break;
                        case 3:
                            y = GamePanel.HEIGHT;
                            x = Math.random() * GamePanel.WIDTH;
                            dx = (double) GamePanel.WIDTH / 2 - x;
                            dy = (double) GamePanel.HEIGHT / 2 - y;
                            angle = Math.atan2(dy, dx);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected case number: " + caseNumber);
                    }

                    // Create the dot.
                    if (gameObjects.get(0).isAlive()) {
                    Dot dot = new Dot(x, y, angle, 75, Color.RED);
                    gameObjects.add(dot);
                    }
                }
            };
            // Schedule the timer task to run every 100 milliseconds.
            timer.scheduleAtFixedRate(addDotTask, 0, 1000);
    }

    protected void processUserInput(UserInput input){
        if (avatar.isAlive()) {
            avatar.shoot(userInput);
        }
    }

    public GameWorld() {
        physicsSystem = new GamePhysics(this);
    }

    public void run() {
        long lastTick =  System.currentTimeMillis();

        while(true) {
            long currentTick    = System.currentTimeMillis();
            double diffSeconds  = (currentTick-lastTick)/1000.0;
            lastTick            = currentTick;

            // process User Input
            userInput = inputSystem.getUserInput();
            if(userInput!=null) {
                processUserInput(userInput);
            }

            // move all Objects
            for(int i=1; i<gameObjects.size(); i++) {
                if (gameObjects.get(0).isAlive())
                    gameObjects.get(i).move(diffSeconds);
            }

            // draw all Objects
            graphicSystem.clear();
            for(int i=1; i<gameObjects.size(); i++) {
                if (avatar.isAlive()){
                    graphicSystem.draw(gameObjects.get(0));
                    graphicSystem.drawBar(healthBar);
                }
                graphicSystem.draw(gameObjects.get(i));
            }


            for(Projectile dot: projectiles) {
                dot.move(diffSeconds);
                graphicSystem.draw(dot);

            }

            // draw Avatar
            if (avatar.isAlive()){
                graphicSystem.draw(gameObjects.get(0));
            }

            if ( !avatar.isAlive()) {
                String text = "Game Over";
                GamePanel.graphics.setFont(new Font("Arial", Font.BOLD, 50));
                FontMetrics fm = GamePanel.graphics.getFontMetrics();
                fm.getStringBounds(text, GamePanel.graphics);
                Rectangle2D r2 = fm.getStringBounds(text, GamePanel.graphics);
                double textWidth = r2.getWidth();
                double textHeight = r2.getHeight();
                double x = (GamePanel.WIDTH - textWidth) / 2;
                double y = (GamePanel.HEIGHT - textHeight) / 2;
                GamePanel.graphics.drawString(text, (int) x, (int) y + fm.getAscent());
            }

            String text = "Score: " + Dot.score;
            GamePanel.graphics.setFont(new Font("Arial", Font.BOLD, 15));
            FontMetrics fm = GamePanel.graphics.getFontMetrics();
            fm.getStringBounds(text, GamePanel.graphics);
            GamePanel.graphics.drawString(text, 10, 10 + fm.getAscent());

            // redraw everything
            graphicSystem.redraw();
        }
    }

    public void setGraphicSystem(GameGraphics p) {
        graphicSystem = p;
    }

    public void setInputSystem(InputSystem p) {
        inputSystem   = p;
    }

    public GamePhysics getPhysicsSystem() {
        return physicsSystem;
    }
}
