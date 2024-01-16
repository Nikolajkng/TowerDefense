package dk.dtu.backend;

import org.jspace.ActualField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.LostAlertGUI;
import javafx.stage.Stage;

public class PlayerInfo implements Runnable {

    static int player;
    static int life = 100;

    private static Stage loserStage;
    static int money = 500; // Infinity money gliiiith -Niko

    SequentialSpace space;

    public PlayerInfo(int player) {
        PlayerInfo.player = player;
    }

    public static void lifeTracker(Space space) {

        try {
            Object[] object = space.get(new ActualField("finish"));
            if (object != null) {
                life--;
                if (life <= 0) {
                    new LostAlertGUI().start(loserStage);
                }
            }
        } catch (InterruptedException e) {
        }

    }

    public static int getLife() {
        return life;
    }

    public static void setLife(int life) {
        PlayerInfo.life = life;
    }

    public static void moneyTracker(Space space) {

        try {
            Object[] object2 = space.get(new ActualField("Terminate"));
            if (object2 != null) {
                money++;

                // skal nok ikke bruges?
                // if (money <= 0) {
                // new LostAlertGUI().start(loserStage);
                // }
            }
        } catch (InterruptedException e) {
        }

    }

    public static int getMoney() {
        return money;
    }

    @Override
    public void run() {

    }
}
