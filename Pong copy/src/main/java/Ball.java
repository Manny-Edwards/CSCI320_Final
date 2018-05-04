import java.awt.*;
import java.util.Random;

public class Ball {
    private Random random = new Random();
    private int ballX = GameStart.WINDOW_WIDTH / 2;
    private int ballY = GameStart.WINDOW_HEIGHT / 2;
    private int xVelocity = -5;
    public int variance=5;
    private int yVelocity = random.nextInt(variance)+1;
    private int size = GameStart.WINDOW_HEIGHT/20;
    private int playerScore = 0;


    public void update() {
        ballX = ballX + xVelocity;
        ballY = ballY + yVelocity;

        if (ballX < 0) {
            xVelocity = 4;
        } else if (ballX + size > GameStart.WINDOW_WIDTH - 6) {
            xVelocity = -4;
        }

        if (ballY < 0) {
            yVelocity = 4;
        }
        else if (ballY + size > GameStart.WINDOW_HEIGHT - 28) {
            yVelocity = -4;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0,0, GameStart.WINDOW_WIDTH, GameStart.WINDOW_HEIGHT/9);
        g.setColor(Color.green);
        g.fillOval(ballX, ballY, size, size);
        g.drawString(toPlayer(), 5, 15);
    }

    public void collision(Player player) {
        if (this.ballX > player.getX() && this.ballX < player.getX() + player.getWidth()) {
            if (this.ballY > player.getY() && this.ballY < player.getY() + player.getHeight()) {
                xVelocity = xVelocity*-1;
                variance++;
            }
        }
    }

    public void wall() {
        if (ballY <= GameStart.WINDOW_HEIGHT/9) {
            yVelocity = yVelocity*-1;
            playerScore = playerScore + 1;
        }
    }

    public int getx() {
        return ballX;
    }

    public int gety() {
        return ballY;
    }

    public int getplayerScore() {
        return playerScore;
    }

    public String toPlayer() {
        String playData = "";
        playData = "Opponent's Score: " + playerScore;
        return playData;
    }

}
