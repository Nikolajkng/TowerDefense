package dk.dtu.app.view.GameBoardsGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChatGUI {
    private static int messageCount = 0;
    private static List<Label> messageList = new ArrayList<>();


    public static void createChatBox(ScrollPane scrollPane, VBox chatBox, Button sendBtn) {

        // Chat box apperance
        chatBox.setPrefSize(400, scrollPane.getHeight());
        chatBox.setStyle("-fx-background-color: #D7E0E0");


        // Chat box content: When player click "Send Message"
        sendBtn.setOnAction(e -> {
            messageList.add(new Label("Player 1: " + "Hello"));
            messageList.get(messageCount).setStyle("-fx-background-color: #ADDFFF");
            chatBox.getChildren().addAll(messageList.get(messageCount));
            messageCount++;
            scrollPane.setVvalue(1.0);
       });     

    }
}
