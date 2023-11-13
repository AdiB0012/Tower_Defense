import javax.swing.*;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 2L;

    private final GamePanel panel;
    public GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);

        panel = new GamePanel();
        this.setContentPane(panel);
    }

    public GamePanel getPanel() {return panel;}
}
