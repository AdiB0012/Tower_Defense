import java.awt.*;

public class Projectile extends GameObject{

    public Projectile(double x, double y, double a, double s, Color c) {
        super(x,y,a,s);
        radius = 7;
        color = c;
    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }
}
