package dk.dtu.backend;

import org.jspace.SequentialSpace;

public class PlayerInfo {

    protected static int money = 50;
    protected static int health = 100;
    protected static SequentialSpace space;
    protected String callsign;

    public PlayerInfo(String callsign2) {
        this.callsign = callsign2;
    }

    public int getHealth() {
        return health;
    }

    public static void setHealth(int life) {
        health = life;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int penge) {
        money = penge;
    }

    public String getCallsign(){
        return callsign;
    }
}
