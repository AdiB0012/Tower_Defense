import java.awt.Color;

public class Avatar extends GameObject {

    private static final Color COLOR_AVATAR  = new Color(96,96,255);

    public Avatar(double x, double y) {
        super((double) GamePanel.WIDTH /2, (double) GamePanel.HEIGHT /2,0,200);
        color  = COLOR_AVATAR;
        radius = 30;
    }

    public void shoot(UserInput userInput) {
        Projectile projectile = new Projectile((double) GamePanel.WIDTH /2, (double) GamePanel.HEIGHT /2, 2, 200, Color.BLUE);
        projectile.setDestination(userInput.mousePressedX, userInput.mousePressedY);
        GameWorld.projectiles.add(projectile);
    }
}