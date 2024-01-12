package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.controller.BoardLogic.ChatController;
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
                        ChatController.updateChatBox("Client", (String) message[1]);
                    });
                }
            } else if (callsign == "Client") {
                while (true) {
                    Object[] message = chatRoom.get(new ActualField("Host"), new FormalField(String.class));
                    System.out.println("Received message from Host: " + (String) message[1]);
                    Platform.runLater(() -> {
                        ChatController.updateChatBox("Host", (String) message[1]);
                    });
                }
            }

        } catch (InterruptedException e) {
            System.out.println("listenForMessages Error: Could not receive message");

        }
    }
}
