package dk.dtu.backend;

import org.jspace.ActualField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.LostAlertGUI;
import javafx.stage.Stage;

public class PlayerInfo implements Runnable {
    
    static int life = 100;

    private static Stage loserStage;
    int money = 100;
    SequentialSpace space;

    public PlayerInfo(SequentialSpace space) {
        this.space = space;

    }

    public static void lifeTracker(Space space){

        try {
            Object[] object = space.get(new ActualField("finish"));
            if (object != null){
                life --;
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

    public static void moneyTracker(){
           
    }

    @Override
    public void run() {
        
    }
}
