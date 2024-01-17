package dk.dtu.backend;

import org.jspace.SequentialSpace;

public class PlayerInfo {

    protected static int money = 50;
    protected static int health = 100;
    protected SequentialSpace space;
    protected String callsign;

    public PlayerInfo(String callsign) {
        this.callsign = callsign;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int life) {
        PlayerInfo.health = life;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        PlayerInfo.money = money;
    }
}
