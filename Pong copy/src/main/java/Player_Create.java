import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Player_Create extends JFrame implements WindowListener{
    private String name;
    int WINDOW_HEIGHT=280;
    int WINDOW_WIDTH=400;
    GamePanel game;
    public Player_Create(){
        name = (JOptionPane.showInputDialog(null, "Name", GameStart.TITLE, JOptionPane.QUESTION_MESSAGE));
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        game=new GamePanel(name);
        setContentPane(game);
        setVisible(true);
        run();
    }

    public void run() {
        while(true) {
            GameStart game_start = game.space.takeById(GameStart.class, GameStart.DEFAULT_GAME_ID);
            if (game_start != null) {
                System.out.println("You got the game status " + game_start);
                Player player1 = game_start.getPlayerByName(name);
                if (game_start.getStatus() == game_start.GAME_NOT_STARTED) {

                    if (player1==null) {
                        if(game_start.Size()==0) {
                            System.out.println(name + " joined the game...");
                            player1 = new Player(game_start.WINDOW_WIDTH / 16, game_start.WINDOW_HEIGHT / 2, name);

                            game_start.addPlayer(player1);
                        }
                        if(game_start.Size()==1&& !game_start.hasPlayer(player1)) {
                            System.out.println(name + " joined the game second...");
                            player1 = new Player((game_start.WINDOW_WIDTH / 16), game_start.WINDOW_HEIGHT / 2, name);
                            game_start.addPlayer(player1);
                        }
                        if(game_start.getStatus()==GameStart.GAME_STARTED){
                            System.out.println("The game is full. Please try again later...");
                            System.exit(0);
                        }
                    }
                    if (game_start.Size() >= 2) {
                        game_start.setStatus(GameStart.GAME_STARTED);
                    }
                }
                else if(game_start.getStatus() == GameStart.GAME_STARTED){
                    System.out.println("Game started");
                    game.setPlay(true);
                    if (game.ball.getplayerScore()==11){
                        game.setPlay(false);
                        System.out.println(name + " has lost.");

                    }
                }
                game.space.write(game_start);
            }
            else
                System.out.println("Couldn't get game status!");

            try {
                Thread.sleep(1500);
            }
            catch (InterruptedException ex) {

            }
        }
    }

    public static void main(String[] args) {
        new Player_Create();
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        game.space.takeById(Player.class, name);
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}
