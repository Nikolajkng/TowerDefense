package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.controller.BoardLogic.ChatController;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;

public class ChatReceiver implements Runnable {
    String callsign;
    RemoteSpace chatRoom;

    public ChatReceiver(String callsign) throws UnknownHostException, IOException {
        this.callsign = callsign;
        this.chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/ChatRoom?keep");

    }

    @Override
    public void run() {

        try {

            listenForMessages();
            Thread.sleep(100); // Adds a time delay in the while-loop of 100 ms

        } catch (InterruptedException e) {
            if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                System.out.println("Lort virker");
            } else {
                e.printStackTrace();
            }
        }

    }

    private void listenForMessages() {
        if (callsign == "Host") {
            try {
                while (true) {

                    Object[] message = chatRoom.get(new ActualField("Client"), new FormalField(String.class));
                    if (((String) message[1]).startsWith("msg:")) {
                        System.out.println("Received message from Client: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatController.updateChatBox("Client", (String) message[1]);
                        });
                    } else if (((String) message[1]).equals("disconnect")){
                        System.out.println("Received disconnect from Client");
                        Platform.runLater(() -> {
                            MultiplayerBoard.boardStage.close();
                        });
                    } else{
                        System.out.println("hej client: " + message[1]);
                    }

                }
            } catch (InterruptedException e) {
                if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                    Platform.runLater(() -> {
                        MultiplayerBoard.boardStage.close();
                    });
                    System.out.println("Lort virker2");
                } else {
                    e.printStackTrace();
                }

            }
        } else if (callsign == "Client") {

            try {
                while (true) {

                    Object[] message = chatRoom.get(new ActualField("Host"), new FormalField(String.class));
                    if (((String) message[1]).startsWith("msg:")) {
                        System.out.println("Received message from Host: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatController.updateChatBox("Host", (String) message[1]);
                        });
                    } else if (((String) message[1]).equals("disconnect")){
                        System.out.println("Received disconnect from Host");
                        Platform.runLater(() -> {
                            MultiplayerBoard.boardStage.close();
                        });
                    } else{
                        System.out.println("hej host: " + message[1]);
                    }

                }
            } catch (InterruptedException e) {
                if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                    Platform.runLater(() -> {
                        MultiplayerBoard.boardStage.close();
                    });
                    System.out.println("Lort virker3");
                } else {
                    e.printStackTrace();
                }

            }
        }

    }
}
