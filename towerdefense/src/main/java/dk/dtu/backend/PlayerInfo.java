package dk.dtu.backend;

import org.jspace.SequentialSpace;
import javafx.stage.Stage;

public class PlayerInfo {

    protected static int money = 50;
    protected static int life = 100;
    protected SequentialSpace space;
    protected String callsign;

    public PlayerInfo(String callsign) {
        this.callsign = callsign;
    }

    public static int getLife() {
        return life;
    }

    public static void setLife(int life) {
        PlayerInfo.life = life;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        PlayerInfo.money = money;
    }
}
