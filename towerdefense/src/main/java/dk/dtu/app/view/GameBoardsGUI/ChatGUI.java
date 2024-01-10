package dk.dtu.app.view.GameBoardsGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ChatGUI {
    private static int messageCount = 0;
    private static List<Label> messageList = new ArrayList<>();


    public static void createChatBox(VBox chatBox, Button sendBtn) {

        // Chat box apperance
        chatBox.setPrefSize(400, 100);
        chatBox.setStyle("-fx-background-color: #D7E0E0");


        // Chat box content
        List<Label> messageList = new ArrayList<>();
        sendBtn.setOnAction(e -> {
            // Add message to messageList
            Label myMessage = new Label("Hej");
            myMessage.setStyle("-fx-background-color: #ADDFFF");
            messageList.add(myMessage);
   
            chatBox.getChildren().addAll(messageList.get(messageCount));
            messageCount++;
            System.out.println(messageCount);
       });     




    }
}
