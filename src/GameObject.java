import java.awt.Color;

public abstract class GameObject {
    public double x,y;
    public double alfa;
    public double speed;
    public Color  color = Color.ORANGE;
    private Boolean alive=true;

    protected double  destX;
    protected double  destY;
    protected boolean isMoving = false;
    protected int radius = 2;

    protected static GamePhysics physicsSystem;
    protected static GameWorld         world;


    public GameObject(double x_, double y_, double a_, double s_) {
        x=x_; y=y_; alfa=a_; speed=s_;
    }

    public void move(double diffSeconds) {
        x += Math.cos(alfa)*speed*diffSeconds;
        y += Math.sin(alfa)*speed*diffSeconds;
    }

    public void setDestination(double dx, double dy) {
        isMoving = true;
        destX    = dx;
        destY    = dy;

        alfa = Math.atan2(dy-y, dx-x);
    }

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public static void setPhysicsSystem(GamePhysics ps) {physicsSystem=ps;}
    public static void setWorld(GameWorld w) {world=w;}
}
