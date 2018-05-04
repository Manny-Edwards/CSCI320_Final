import com.gigaspaces.annotation.pojo.SpaceId;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class GameStart{
    static final String TITLE = "CSCI320: GameStart";
    static int WINDOW_WIDTH=400;
    static int WINDOW_HEIGHT=280;
    static final String SPACE_URL = "jini://localhost/*/pong";

    private String       id;
    private List<Player> players;
    private Integer      status;

    public static int GAME_NOT_STARTED = 0;
    public static int GAME_STARTED     = 1;
    public static int GAME_ENDED       = 2;
    public static String DEFAULT_GAME_ID  = "1";

    private GameStart() {
        id = DEFAULT_GAME_ID;
    }

    @SpaceId
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Player getPlayerByName(String name) {
        if (players == null)
            return null;

        for (Player player: players)
            if (player.getName().equals(name))
                return player;
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (players == null)
            players = new LinkedList<Player>();
        players.add(player);
    }

    public boolean hasPlayer(Player player) {
        if (players == null)
            return false;
        return players.contains(player);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int Size() {
        if (players != null)
            return players.size();
        return 0;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", players=" + players +
                ", status=" + status +
                '}';
    }

    public static void main(String[] args) {
        GigaSpaceConfigurer conf = new GigaSpaceConfigurer(new UrlSpaceConfigurer(SPACE_URL));
        GigaSpace space = conf.create();
        GameStart game_start=new GameStart();
        game_start.setStatus(GAME_NOT_STARTED);
        System.out.println("The game's size will be " + WINDOW_WIDTH + "x" + WINDOW_HEIGHT);
        space.write(game_start);
    }
}
