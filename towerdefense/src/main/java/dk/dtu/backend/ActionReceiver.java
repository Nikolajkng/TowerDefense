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
    String uri = "";
    RemoteSpace clientRoom;
    public SequentialSpace hostRoom;
    public static Object[] actionInfo;

    public ActionReceiver(String uri, RemoteSpace room) throws UnknownHostException, IOException {
        this.uri = uri;
        this.clientRoom = new RemoteSpace(uri);
    }

    public ActionReceiver(SequentialSpace room) {
        this.hostRoom = room;
    }

    // Client: clientActionListenerThread = new Thread(new ActionReceiver(P1P2_uri, P1P2room));
    // Host  : hostActionListenerThread = new Thread(new ActionReceiver(Server.P2P1room));


    @Override
    public void run() {

        // What client receives from the host
        if (clientRoom != null) {
            try {
                while (true) {
                    Object[] info = clientRoom.queryp(
                            new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(Action.ActionType.class));
                    if (info != null) {
                        actionInfo = clientRoom.get(
                                new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(Action.ActionType.class));
                        System.out.println(
                                "Received action from Host (" + (ActionType) actionInfo[2] + ") successfully!");
                        Platform.runLater(() -> {
                            ActionHandler.selectAction(actionInfo, MultiplayerBoard.rightBoard);
                        });
                        System.out.println("Placed tower");

                    }
                }
            } catch (InterruptedException e) {
                PlayerConnection.clientActionListenerThread.interrupt();
                Platform.runLater(() -> {
                    MultiplayerBoard.boardStage.close();
                });
                e.printStackTrace();
            }
            // What the host receives from the client
        } else {
            try {
                while (true) {
                    Object[] info = hostRoom.queryp(new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(Action.ActionType.class));
                    if (info != null) {
                        actionInfo = hostRoom.get(new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(Action.ActionType.class));
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
