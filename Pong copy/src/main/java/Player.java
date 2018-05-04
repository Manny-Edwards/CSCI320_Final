import com.gigaspaces.annotation.pojo.SpaceId;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Player implements Serializable{
    private int Playerx;
    private int Playery;
    private String name;
    private int yVel = 0;
    private int width = GameStart.WINDOW_WIDTH/80;
    private int height = GameStart.WINDOW_HEIGHT/6;

    public Player(int x, int y, String name) {
        this.name = name;
        Playerx=x;
        Playery=y;

    }
    @SpaceId
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update() {
        Playery = Playery + yVel;
    }

    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(Playerx, Playery, width, height);
    }

    public void setYV(int speed) {
        yVel = speed;
    }

    public int getX() {
        return Playerx;
    }

    public int getY() {
        return Playery;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
