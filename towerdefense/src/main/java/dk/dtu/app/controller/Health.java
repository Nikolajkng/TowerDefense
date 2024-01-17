package dk.dtu.app.controller;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;

public class Health {

    public static void healthTracker(boolean belongsToLeftBoard, int currentHealthMe, int currentHealthYou, int rabbitDamage ){
              if (belongsToLeftBoard) {
                int newHealthMe = currentHealthMe - rabbitDamage;
                Platform.runLater(() -> {
                    MultiplayerBoard.healthP1.setText(Integer.toString(newHealthMe));
                });
                currentHealthMe = newHealthMe;
                if (currentHealthMe <= 0) {
                    System.out.println("Game over: " + BattleLogic.myInfo.getCallsign() + " has lost");
                }

            } else if (!belongsToLeftBoard) {
                int newHealthYou = currentHealthYou - rabbitDamage;
                Platform.runLater(() -> {
                    MultiplayerBoard.healthP2.setText(Integer.toString(newHealthYou));
                });
                currentHealthYou = newHealthYou;
                if (currentHealthYou <= 0) {
                    
                    System.out.println("Game over: " + BattleLogic.opponentInfo.getCallsign() + " has lost");
                }
            }
    }
}
