package dk.dtu.app.view.GameBoardsGUI;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ChatGUI {
    public static void createChatBox(VBox chatBox){
    Label msg1 = new Label("hej");
    Label msg2 = new Label("farvel");
        
        chatBox.getChildren().addAll(msg1, msg2);
        chatBox.setPrefSize(400, 100);
        chatBox.setStyle("-fx-background-color: #D7E0E0");


        
    }
}
