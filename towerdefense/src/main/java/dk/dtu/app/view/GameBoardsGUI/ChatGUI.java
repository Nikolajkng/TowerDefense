package dk.dtu.app.view.GameBoardsGUI;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ChatGUI {
    private List<Label> messages = new ArrayList<>();

    public static void createChatBox(VBox chatBox) {

        Label msg1 = new Label("hej");
        Label msg2 = new Label("farvel");

        chatBox.getChildren().addAll(msg1, msg2);
        chatBox.setPrefSize(400, 100);
        chatBox.setStyle("-fx-background-color: #D7E0E0");

    }
}
