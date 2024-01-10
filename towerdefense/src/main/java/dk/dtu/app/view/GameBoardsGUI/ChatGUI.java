package dk.dtu.app.view.GameBoardsGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ChatGUI {
    private static int messageCount = 0;
    private static List<Label> messageList = new ArrayList<>();


    public static void createChatBox(ScrollPane scrollPane, VBox chatBox, Button sendBtn, TextField chatField) {
        // Chat box content: When player click "Send Message"
        sendBtn.setOnAction(e -> {
            if (!chatField.getText().isEmpty()){
            // User message input
            String userMessage = chatField.getText();
            messageList.add(new Label("Player 1: " + userMessage));
            messageList.get(messageCount).setStyle("-fx-background-color: #ADDFFF");
            chatBox.getChildren().addAll(messageList.get(messageCount));
            messageCount++;

            // Auto clear Textfield after user send message
            chatField.setText("");

            // Auto scroll to bottom
            scrollPane.setVvalue(1.0);
            } else {
                System.out.println("Please enter a message");
            }
       });     

    }
}
