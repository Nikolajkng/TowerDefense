package dk.dtu.app.view.GameBoardsGUI;

import java.io.IOException;
import java.net.UnknownHostException;

import dk.dtu.app.controller.BoardLogic.ChatController;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatGUI {
    // Global Fields
    public static VBox chatBox = new VBox(5);

    // Local Fields
    private static ScrollPane scrollPane = new ScrollPane();
    private static VBox chatModule = new VBox(5);
    private static TextField chatField = new TextField();

    // Constructs the chat GUI
    public static void createChatGUI() throws UnknownHostException, IOException {
        // Position of chat panes
        MultiplayerBoard.bottomHUD.getChildren().addAll(chatModule);
        chatModule.getChildren().addAll(scrollPane, new HBox(chatField));
        chatModule.setAlignment(Pos.CENTER);
        ChatController.createChatBox(scrollPane, chatBox, chatField);

        // scrollPane settings
        int height = 110;
        scrollPane.setContent(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(height);
        scrollPane.setStyle(""
                + "-fx-background-color: #D7E0E0;"
                + "-fx-border-color: black;"
                + "-fx-border-width: 2px;"
                + "-fx-border-radius: 5px;");

        // chat box customization
        int width = 400;
        chatBox.setPrefSize(width, height);
        chatBox.setStyle(""
                + "-fx-background-color: #D7E0E0;"
                + "-fx-border-radius: 5px;");
        chatField.setPrefWidth(width);

    }

}
