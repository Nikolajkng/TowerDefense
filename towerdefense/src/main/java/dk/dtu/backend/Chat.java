package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Remote;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.view.GameBoardsGUI.ChatGUI;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chat implements Runnable {
    String callsign;
    RemoteSpace chatRoom;

    public Chat(String callsign) throws UnknownHostException, IOException {
        this.callsign = callsign;
        this.chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/chatRoom?keep");

    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(PlayerConnection.inputIP);
                if (callsign == "Host") {
                    while (true) {
                        Object[] message = chatRoom.get(new ActualField("client"), new FormalField(String.class));
                        System.out.println("Client: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatGUI.messageList.add(new Label("Player 2: " + (String) message[1]));
                        });
                    }
                } else if (callsign == "client") {
                    while (true) {
                        Object[] message = chatRoom.get(new ActualField("Host"), new FormalField(String.class));
                        System.out.println("Host: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatGUI.messageList.add(new Label("Player 1: " + (String) message[1]));
                        });
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Chat started");
        }
    }
}
