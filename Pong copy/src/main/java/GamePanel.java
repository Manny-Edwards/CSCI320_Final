
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Player player=new Player(GameStart.WINDOW_HEIGHT/2,GameStart.WINDOW_WIDTH/2,name);
    private static String name;
    public Ball ball=new Ball();
    public static GigaSpace space;
    private boolean playGame=false;

    private static final String SPACE_URL = "jini://localhost/*/pong";

    public GamePanel(String name) {
        GamePanel.name =name;
        GigaSpaceConfigurer conf = new GigaSpaceConfigurer(new UrlSpaceConfigurer(SPACE_URL));
        space = conf.create();
        //run();
        this.addKeyListener(this);
        this.setFocusable(true);
        Timer time=new Timer(50,this);
        time.start();
    }
    public void setPlay(Boolean play){
        this.playGame=play;
    }
    public boolean playgame(){
        return playGame;
    }
    public void update(){
        if(playgame()) {
            player.update();
            ball.collision(player);
            ball.wall();
            ball.update();
            repaint();
            validate();
        }
    }
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }


    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, GameStart.WINDOW_WIDTH, GameStart.WINDOW_HEIGHT);

        player.paint(g);
        ball.paint(g);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setYV(-4);
            if (player.getY() <= GameStart.WINDOW_HEIGHT / 9) {
                player.setYV(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setYV(4);
            if (player.getY() + (GameStart.WINDOW_HEIGHT / 4) > GameStart.WINDOW_HEIGHT) {
                player.setYV(0);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setYV(0);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setYV(0);

        }
    }

    public void keyTyped(KeyEvent e) {

    }

}
