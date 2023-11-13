import java.awt.Color;
import java.util.ArrayList;

public class Dot extends GameObject {
    public static int score = 0;

    public Dot(double x, double y, double a, double s, Color c) {
        super(x,y,a,s);
        radius = 15;
        color = c;
    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
        //removeAtBorders();

        // ?? shall colliding Dot_s be removed??
        //if(false) return;

        ArrayList<GameObject> collisions = physicsSystem.getCollisions(this);

        for(int i=0; i<collisions.size(); i++) {
            GameObject obj = collisions.get(i);
            if(obj!= GameWorld.gameObjects.get(0))
                GameWorld.gameObjects.remove(obj);

        }

        if(!collisions.isEmpty() && this!= GameWorld.gameObjects.get(0)){
            GameWorld.gameObjects.remove(this);
            score++;
        }
    }
}
