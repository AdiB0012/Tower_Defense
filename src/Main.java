// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private GameWorld world = null;

    public Main() {
        GameFrame frame = new GameFrame();
        frame.setVisible(true);

        world = new GameWorld();

        world.setGraphicSystem(frame.getPanel());
        world.setInputSystem(frame.getPanel());

        GameObject.setPhysicsSystem(world.getPhysicsSystem());
        GameObject.setWorld(world);

        world.init();
        world.run();
    }

    public static void main(String[] args) {
        new Main();
    }
}