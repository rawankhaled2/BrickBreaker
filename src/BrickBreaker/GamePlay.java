package BrickBreaker;
import javax.swing.JPanel;  
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int delay = 5;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();
    }
    @Override
     public void paint(Graphics g) {
         //backgroung
        g.setColor(Color.white);
        g.fillRect(1, 1, 692, 592);
        //drawing map
        map.draw((Graphics2D) g);
        //broders
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //scores
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        //the paddel
        g.setColor(Color.red);
        g.fillRect(playerX, 550, 100, 8);
        // the ball
        g.setColor(Color.black);
        g.fillOval(ballposX, ballposY, 20, 20);
        
       //whrn a plyer wins
        if(totalbricks <= 0){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    You won "+score,190,300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }
        
        //when a plyer loses
        if(ballposY> 570){
            play = false;
            ballYdir = 0;
            ballXdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("    Game Over: "+score,190,300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("   Press Enter to Restart", 190, 340);
        }
        g.dispose();

    }
   @Override
    public void actionPerformed(ActionEvent e) {
        
        Timer.start();
        
        if (play) {
                       if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {

                ballYdir = -ballYdir;
            }
            A:
            
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0){
                       
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        
                        Rectangle brickrect = rect;
                        
                        if (ballrect.intersects(brickrect)) {
                            
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            score += 5;
                            
                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                      } else {

ballYdir = -ballYdir;
                            }
                           break A;
                        } } }}
            
            ballposX += ballXdir;
            ballposY += ballYdir;
            
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }
    @Override
   
    public void keyTyped(KeyEvent e) {
       }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
       
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalbricks = 61;
                map = new MapGenerator(6, 10);
                repaint();
            }} }
     public void moveRight ()
        {
            play = true;
            playerX += 20;
        }

        public void moveLeft ()
        {
            play = true;
            playerX -= 20;
        }
}