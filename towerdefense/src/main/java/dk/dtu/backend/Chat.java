package dk.dtu.backend;

import java.io.IOException;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.view.GameBoardsGUI.ChatGUI;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chat implements Runnable {
    String callsign;

    public Chat(String callsign) {
        this.callsign = callsign;
    }

    @Override
    public void run() {
        try {
            RemoteSpace chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/chatRoom?keep");
            if(callsign == "Host"){
                while(true){
                    String[] message = (String[]) chatRoom.get(new ActualField("client"), new FormalField(String.class));
                    System.out.println("Client: " + message[1]);
                    Platform.runLater(() -> {
                        ChatGUI.messageList.add(new Label("Player 2: "+ message[1]));
                    });
                }
            } else if(callsign == "client"){
                while(true){
                    String[] message = (String[]) chatRoom.get(new ActualField("Host"), new FormalField(String.class));
                    System.out.println("Host: " + message[1]);
                Platform.runLater(() -> {
                        ChatGUI.messageList.add(new Label("Player 1: "+ message[1]));
                    });                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Chat started");
    }

}
