package dk.dtu.app.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.RemoteSpace;

import dk.dtu.app.controller.Enemy.Enemy;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.PlayerConnection;
import javafx.application.Platform;

public class Health {
    private static RemoteSpace space;

    public static void healthTracker(boolean belongsToLeftBoard, int currentHealthMe, int currentHealthYou, int rabbitDamage ){

        try {
            space = new RemoteSpace("tcp://"+ PlayerConnection.inputIP + ":55000/GameRoom?keep");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

              if (belongsToLeftBoard) {
                int newHealthMe = currentHealthMe - rabbitDamage;
                Platform.runLater(() -> {
                    MultiplayerBoard.healthP1.setText(Integer.toString(newHealthMe));
                });
                Enemy.currentHealthMe = newHealthMe;
                if (currentHealthMe <= 0) {
                    try {
                        System.out.println("Game over: " + BattleLogic.myInfo.getCallsign() + " has lost");
                        space.put(BattleLogic.myInfo.getCallsign(), "lost");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } else if (!belongsToLeftBoard) {
                int newHealthYou = currentHealthYou - rabbitDamage;
                Platform.runLater(() -> {
                    MultiplayerBoard.healthP2.setText(Integer.toString(newHealthYou));
                });
                Enemy.currentHealthYou = newHealthYou;
                if (currentHealthYou <= 0) {
                    try {
                        System.out.println("Game over: " + BattleLogic.opponentInfo.getCallsign() + " has lost");
                        space.put(BattleLogic.opponentInfo.getCallsign(), "lost");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
