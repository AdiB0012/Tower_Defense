import java.util.ArrayList;

public class GamePhysics {
    private GameWorld world;

    public GamePhysics(GameWorld w) {
        world = w;
    }

    public ArrayList<GameObject> getCollisions(GameObject object) {
        ArrayList<GameObject> result = new ArrayList<>();

        int len = GameWorld.gameObjects.size();
        for(int i=0; i<len; i++) {
            GameObject obj2 = GameWorld.gameObjects.get(i);
            if(obj2.getClass()==object.getClass()) continue;

            // check if they touch each other
            double dist = object.radius+obj2.radius;
            double dx   = Math.abs(object.x-obj2.x);
            double dy   = Math.abs(object.y-obj2.y);

            if(dx<dist && dy<dist) {
                if(dx*dx+dy*dy < dist*dist) {
                    result.add(obj2);
                    HealthBar.getInstance().setWidth(HealthBar.getInstance().getWidth()-15);
                    if (HealthBar.getInstance().getWidth() <= 0){
                        GameWorld.gameObjects.get(0).setAlive(false);
                    }
                }
            }
        }

        int lenght = GameWorld.projectiles.size();
        for(int i=0; i<lenght; i++) {

            GameObject obj2 = GameWorld.projectiles.get(i);
            if(obj2.getClass()==object.getClass()) continue;

            // check if they touch each other
            double dist = object.radius+obj2.radius;
            double dx   = Math.abs(object.x-obj2.x);
            double dy   = Math.abs(object.y-obj2.y);

            if(dx<dist && dy<dist) {
                if(dx*dx+dy*dy < dist*dist) {
                    result.add(obj2);
                }
            }
        }
        return result;
    }
}
