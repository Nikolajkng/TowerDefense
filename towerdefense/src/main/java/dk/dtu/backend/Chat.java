package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.controller.BoardLogic.ChatController;
import javafx.application.Platform;

public class Chat implements Runnable {
    String callsign;
    RemoteSpace chatRoom;

    public Chat(String callsign) throws UnknownHostException, IOException {
        this.callsign = callsign;
        this.chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/ChatRoom?keep");

    }

    @Override
    public void run() {
        while (true) {
            try {
                listenForMessages();
                Thread.sleep(100); // Adds a time delay in the while-loop of 100 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void listenForMessages() {
        try {
            if (callsign == "Host") {
                while (true) {
                    Object[] message = chatRoom.get(new ActualField("Client"), new FormalField(String.class));
                    System.out.println("Received message from Client: " + (String) message[1]);
                    Platform.runLater(() -> {
                        ChatController.updateChatBox((String) message[1]);
                        // ChatController.messageList.add(new Label("Player 2: " + (String)
                        // message[1]));
                    });
                }
            } else if (callsign == "Client") {
                while (true) {
                    Object[] message = chatRoom.get(new ActualField("Host"), new FormalField(String.class));
                    System.out.println("Received message from Host: " + (String) message[1]);
                    Platform.runLater(() -> {
                        ChatController.updateChatBox((String) message[1]);

                        // ChatController.messageList.add(new Label("Player 1: " + (String)
                        // message[1]));
                    });
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Chat started");
    }
}
