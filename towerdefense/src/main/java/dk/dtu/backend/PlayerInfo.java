package dk.dtu.backend;

import org.jspace.ActualField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.LostAlertGUI;
import javafx.stage.Stage;

public class PlayerInfo {
    
    static int life = 100;

    private static Stage loserStage;
    public static int money = 50; // Infinity money gliiiith -Niko

    SequentialSpace space;

    public PlayerInfo(SequentialSpace space) {
        this.space = space;

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
