package dk.dtu.app.view.GameBoardsGUI;

import java.io.IOException;
import java.net.UnknownHostException;

import dk.dtu.app.controller.BoardLogic.ChatController;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatGUI {
    public static VBox chatBox = new VBox(5);

    public static void createChatGUI() throws UnknownHostException, IOException {
        // Chat box setup
        ScrollPane scrollPane = new ScrollPane();
        VBox chatModule = new VBox(5);
        TextField chatField = new TextField();
        scrollPane.setContent(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(100);
        scrollPane.setStyle("-fx-background-color: #D7E0E0");

        // Position of chat panes
        MultiplayerBoard.bottomHUD.getChildren().addAll(chatModule);
        chatModule.getChildren().addAll(scrollPane, new HBox(chatField));
        ChatController.createChatBox(scrollPane, chatBox, chatField);
    }

}
