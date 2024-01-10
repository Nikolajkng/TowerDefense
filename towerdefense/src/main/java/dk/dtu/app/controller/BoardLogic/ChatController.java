package dk.dtu.app.controller.BoardLogic;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.jspace.RemoteSpace;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.PlayerConnection;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatController {
    private static int messageCount = 0;
    public static List<Label> messageList = new ArrayList<>();
    private static RemoteSpace chatRoom;

    public static void createChatBox(ScrollPane scrollPane, VBox chatBox, TextField chatField)
            throws UnknownHostException, IOException {
        chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/ChatRoom?keep");

        /////////////////////////////////////////// Chat TextField Enter feature ////////////////////////////////////////////
        chatField.setOnAction(e -> {
            if (!chatField.getText().isEmpty()) {
                // User message input
                String userMessage = chatField.getText();
                updateChatBox(userMessage);

                // Auto clear Textfield after user send message
                chatField.setText("");

                // Auto scroll to bottom
                scrollPane.setVvalue(1.0);

                // Send message to chatRoom
                try {
                    chatRoom.put(PlayerConnection.callsign, userMessage);
                    System.out.println(PlayerConnection.callsign + "Invoked chatRoom.put(" + PlayerConnection.callsign + ", " + userMessage + ")");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            } else {
                System.out.println("Cannot send empty message");
            }
        });

    }

    public static void updateChatBox(String userMessage) {
                messageList.add(new Label(PlayerConnection.callsign + ": " + userMessage));
                messageList.get(messageCount).setStyle("-fx-background-color: #ADDFFF");
                MultiplayerBoard.chatBox.getChildren().addAll(messageList.get(messageCount));
                messageCount++;

    }
}
