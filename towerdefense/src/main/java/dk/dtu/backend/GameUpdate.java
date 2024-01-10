package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;

import dk.dtu.app.controller.ActionHandler;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;

public class GameUpdate implements Runnable {
    String uri = "";
    RemoteSpace clientRoom;
    SequentialSpace hostRoom;
    public static Object[] actionInfo;

    public GameUpdate(String uri, RemoteSpace room) throws UnknownHostException, IOException {
        this.uri = uri;
        this.clientRoom = new RemoteSpace(uri);
    }

    public GameUpdate(SequentialSpace room) {
        this.hostRoom = room;
    }

    @Override
    public void run() {

        // What client receives from the host
        if (clientRoom != null) {
            try {
                while (true) {
                    Object[] info = clientRoom.queryp(
                            new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(String.class));
                    if (info != null) {
                        actionInfo = clientRoom.get(
                                new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(String.class));
                        System.out.println("Received action from Host: " + (String) actionInfo[2]);
                        Platform.runLater(() -> {
                            ActionHandler.selectAction(actionInfo, MultiplayerBoard.rightBoard);
                        });

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // What the host receives from the client
        } else {
            try {
                while (true) {
                    Object[] info = hostRoom.queryp(new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(String.class));
                    if (info != null) {
                        actionInfo = hostRoom.get(new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(String.class));
                        System.out.println("Received action from Client: " + (String) actionInfo[2]);
                        Platform.runLater(() -> {
                            ActionHandler.selectAction(actionInfo, MultiplayerBoard.rightBoard);
                        });

                    }
                }
            } catch (InterruptedException e) {
            }
        }

    }
}
