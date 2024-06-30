import javax.swing.JFrame;

public class SnakeGame{

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    public static void main(String args[]){
        final JFrame frame = new JFrame("Snake Game");
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }
}