import java.awt.*;

public class HealthBar {
    private static HealthBar instance = null;
    private double x, y, width, height;
    public Color color;

    public HealthBar(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        color = Color.RED;
    }

    public static HealthBar getInstance(){
        if (HealthBar.instance == null) {
           // HealthBar.instance = new HealthBar(GamePanel.WIDTH/2 - 200, GamePanel.HEIGHT - 85, 400, 40);
           // HealthBar.instance = new HealthBar(GamePanel.WIDTH/2 - GameWorld.gameObjects.get(0).getRadius(), GamePanel.HEIGHT/2 - GameWorld.gameObjects.get(0).getRadius(), GameWorld.gameObjects.get(0).getRadius()*2, 10);
            HealthBar.instance = new HealthBar(GamePanel.WIDTH/2 - 30, GamePanel.HEIGHT/2 - 40, 60, 3);
        }
        return HealthBar.instance;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
