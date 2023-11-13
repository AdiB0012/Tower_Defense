import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.Serial;

public class GamePanel extends JPanel implements GameGraphics, InputSystem, MouseListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean newInput = false;
    private int mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton;
    private char keyPressed;

    private final BufferedImage imageBuffer;
    public static Graphics graphics;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public GamePanel() {
        this.setSize(WIDTH, HEIGHT);
        GraphicsConfiguration graphicsConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        imageBuffer = graphicsConf.createCompatibleImage(this.getWidth(), this.getHeight());
        graphics = imageBuffer.getGraphics();


      /* healthLabel = new JLabel("Health: 100");
      // Set the foreground color of the label to red
      healthLabel.setForeground(Color.RED);
      // Add the health label to the panel
      this.add(healthLabel);*/

        // initialize Listeners
        this.addMouseListener(this);
    }

    public void clear() {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 1000, 800);
    }

    public final void draw(GameObject dot) {
        int x = (int)dot.x-dot.radius;
        int y = (int)dot.y-dot.radius;
        int r = dot.radius*2;

        graphics.setColor(dot.color);
        graphics.fillOval(x, y, r, r);
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawOval(x,y,r,r);
        graphics.setColor(Color.DARK_GRAY);
    }

    public final void drawBar(HealthBar rect) {
        int x = (int)rect.getX();
        int y = (int)rect.getY();
        int w = (int)rect.getWidth();
        int h = (int)rect.getHeight();
        graphics.setColor(rect.color);
        graphics.fillRect(x, y, w, h);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(WIDTH/2 - 30, HEIGHT/2 - 40, 60, 3);
    }

    public void redraw() {
        this.getGraphics().drawImage(imageBuffer, 0, 0, this);
        //graphics.drawString(healthLabel.getText(), 10, 20);
    }

    public void mousePressed(MouseEvent evt) {
        // an input Event occurs
        newInput = true;

        mousePressedX = evt.getX();
        mousePressedY = evt.getY();
        mouseButton   = evt.getButton();
    }

    public UserInput getUserInput() {
        if(!newInput) return null;

        newInput = false;
        return new UserInput(mousePressedX,mousePressedY,
                mouseMovedX,mouseMovedY,mouseButton,keyPressed);
    }

    // direct the Avatar
    public void command(GameObject av, UserInput input) {
        Avatar avatar = (Avatar)av;
        avatar.setDestination(input.mousePressedX, input.mousePressedY);
    }

    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mouseClicked(MouseEvent evt){}
    public void mouseReleased(MouseEvent evt){}
}
