package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;

import dk.dtu.app.controller.Action;
import dk.dtu.app.controller.ActionHandler;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;

public class ActionReceiver implements Runnable {
    RemoteSpace clientRoom;
    public SequentialSpace hostRoom;
    public static Object[] actionInfo;
    private String callSign;

    public ActionReceiver(String uri, RemoteSpace room, String callSign) throws UnknownHostException, IOException {
        this.clientRoom = new RemoteSpace(uri);
        this.callSign = callSign;
    }

    public ActionReceiver(SequentialSpace room, String callSign) {
        this.hostRoom = room;
        this.callSign = callSign;
    }


    @Override
    public void run() {
        System.out.println(callSign + ": ActionReceiver thread: run() has started");
        // What client receives from the host
        if (clientRoom != null) {
            try {
                while (true) {
                    actionInfo = clientRoom.get(
                                new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(Action.ActionType.class));
                    if (actionInfo != null) {
                        System.out.println(
                                "Received action from Host (" + (ActionType) actionInfo[2] + ") successfully!");
                        Platform.runLater(() -> {
                            ActionHandler.selectAction(actionInfo, MultiplayerBoard.rightBoard);
                        });
                        System.out.println("Placed tower");

                    }
                }
            } catch (InterruptedException e) {
                Platform.runLater(() -> {
                    MultiplayerBoard.boardStage.close();
                });
                e.printStackTrace();
            }
            // What the host receives from the client
        } else {
            try {
                while (true) {
                    actionInfo = hostRoom.get(
                                new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(Action.ActionType.class));
                    if (actionInfo != null) {
                        System.out.println(
                                "Received action from Client (" + (ActionType) actionInfo[2] + ") successfully!");
                        Platform.runLater(() -> {
                            ActionHandler.selectAction(actionInfo, MultiplayerBoard.rightBoard);
                        });

                    }
                }
            } catch (InterruptedException e) {
                Platform.runLater(() -> {
                    MultiplayerBoard.boardStage.close();
                });
            }
        }

    }
}
