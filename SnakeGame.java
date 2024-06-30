import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {
    private int width; 
    private int height;
    private boolean GameStarted = false;
    private static final int FRAME_RATE = 20;
    private Timer timer;
    private final List<GamePoint> snake = new ArrayList<>();
    private final List food = new ArrayList(); 
    private final int cellsize;



    public SnakeGame(final int width, final int height){
        super();
        this.width = width;
        this.height = height; 
        this.cellsize = width/ (FRAME_RATE * 2);
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.GRAY);
    }


    public void startGame(){
        resetGameData();   
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent e) {
                // Handle key press events here
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE){
                    // start the game
                    // 
                    GameStarted = true;
                    
                }
            }
        });

        timer = new Timer(1000 / FRAME_RATE, this);
        timer.start();
        // repaint();
         
    }

    private void resetGameData(){
        // reset the game data here
        snake.clear();
        snake.add(new GamePoint(width / 2, height / 2));
        
    }
    protected void paintComponent(final java.awt.Graphics g){
        
        super.paintComponent(g);
        if(!GameStarted){
            g.setColor(Color.WHITE);
            // g.drawString("You Fucker" , 300 , 400);
            g.setFont(g.getFont().deriveFont(30f));
            int curr_height = height / 4;
            final var graphics2D = (Graphics2D) g;
            final var frc = graphics2D.getFontRenderContext();
            final String message = "Press Space to start the game";
            for(final var line : message.split("\n")){
                final var layout = new java.awt.font.TextLayout(line, graphics2D.getFont(), frc);
                final var bounds = layout.getBounds();
                final var targetWidth = (width - (int)bounds.getWidth()) / 2;
                layout.draw(graphics2D, targetWidth, curr_height);
                curr_height += g.getFontMetrics().getHeight();
            } 
        } else {
            g.setColor(Color.GREEN);
            for(final var point : snake) {
                g.fillRect(point.x, point.y , cellsize, cellsize);
            }
        }      
    }
    private void move(){
        final GamePoint currentHead = snake.getFirst();
        final GamePoint newHead = new GamePoint(currentHead.x + cellsize, currentHead.y);
        snake.addFirst(newHead);

        if(isCollision()) {
            snake.removeFirst ();
        } else {
            snake.removeLast();
        }
    }


    private boolean isCollision(){
        final GamePoint head = snake.getFirst();
        final var invalidWidth = (head.x <0 ) || (head.x >= width);
        final var invalidHeight = (head.y <0 ) || (head.y >= height);
        }

    public void actionPerformed(ActionEvent e) {
        if (GameStarted) {
            move();
        }
        repaint();
    }

    private record GamePoint(int x, int y){}

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    public static void main(String args[]){
        final JFrame frame = new JFrame("Snake Game");
        frame.setSize(WIDTH, HEIGHT);
        SnakeGame game = new SnakeGame(WIDTH, HEIGHT);
        frame.add(game);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        game.startGame();
        
    }
}







