package dk.dtu.app.view.GameBoardsGUI;

import java.util.ArrayList;
import java.util.List;

import org.kordamp.ikonli.javafx.Icon;

import dk.dtu.app.controller.MyButton;
import dk.dtu.app.controller.TowerSelection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application {

    // Global field variables
    public static Stage boardStage = new Stage();
    public static MyButton[][] leftBoard = new MyButton[0][0];
    public static MyButton[][] rightBoard = new MyButton[0][0];
    public static Button towerBtn1 = new Button("Tower1 $0");
    public static Button towerBtn2 = new Button("Tower2 $0");
    public static Button towerBtn3 = new Button("Tower3 $0");
    public static Button towerBtn4 = new Button("Tower4 $0");
    public static Button towerBtn5 = new Button("Tower5 $0");
    public static Button attackEnemy1btn = new Button("Attack enemy");
    public static Button attackEnemy2btn = new Button("Attack enemy");
    public static Button attackEnemy3btn = new Button("Attack enemy");
    public static Button attackEnemy4btn = new Button("Attack enemy");
    public static Button attackEnemy5btn = new Button("Attack enemy");
    public static Label healthP1 = new Label("100");
    public static Label healthP2 = new Label("100");
    public static Label topTitle = new Label("RABBIT HUNTER");

    // Local field variables
    private GridPane leftPane = new GridPane();
    private GridPane rightPane = new GridPane();
    private int sizeX = 1600;
    private int sizeY = 1000; // MyButton cell = new MyButton(0);
    private VBox chatBox = new VBox(5);
    private Image healthIcon = new Image(getClass().getResource("/dk/dtu/app/view/Images/heart.png").toExternalForm());
    private ImageView showHealthIcon1 = new ImageView(healthIcon);
    private ImageView showHealthIcon2 = new ImageView(healthIcon);

    // Program start
    @Override
    public void start(Stage stage) {

        // Stage setup
        boardStage = stage;
        boardStage.setTitle("Multiplayer Board");
        boardStage.setResizable(false);

        // Application layout
        BorderPane borderPane = new BorderPane();
        HBox centerPane = new HBox(leftPane, rightPane);
        VBox leftVbox = new VBox();
        VBox rightVbox = new VBox();
        HBox bottomHUD = new HBox();
        HBox topBar = new HBox();

        // Color of background of Panes
        String myColor = "90EE90";
        centerPane.setStyle("-fx-background-color: #" + myColor);
        leftVbox.setStyle("-fx-background-color: #" + myColor);
        rightVbox.setStyle("-fx-background-color: #" + myColor);
        bottomHUD.setStyle("-fx-background-color: #" + myColor);
        topBar.setStyle("-fx-background-color: #" + myColor);

        // Positions of all panes
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setSpacing(100);

        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(centerPane);
        borderPane.setBottom(bottomHUD);
        borderPane.setTop(topBar);
        bottomHUD.setAlignment(Pos.BOTTOM_CENTER);
        leftVbox.setAlignment(Pos.TOP_CENTER);
        leftVbox.setSpacing(30);
        rightVbox.setAlignment(Pos.TOP_CENTER);
        rightVbox.setSpacing(30);

        // Size of boxPanes
        leftVbox.setPrefWidth(sizeX / 8 - 25);
        rightVbox.setPrefWidth(sizeX / 8 - 25);
        bottomHUD.setPrefHeight(sizeY / 8 + 25);
        topBar.setPrefHeight(sizeY / 8 + 25);

        // Left vbox-menu setup:
        leftVbox.getChildren().addAll(towerBtn1, towerBtn2, towerBtn3, towerBtn4, towerBtn5);

        // Right vbox-menu setup
        rightVbox.getChildren().addAll(attackEnemy1btn, attackEnemy2btn, attackEnemy3btn, attackEnemy4btn,
                attackEnemy5btn);

        // Chat box setup
        Button sendBtn = new Button("Send Message");
        bottomHUD.getChildren().addAll(chatBox,sendBtn);
        ChatGUI.createChatBox(chatBox, sendBtn);

        // Button sizes
        int towerBtnWidth = 120;
        int towerBtnHeight = 100;
        towerBtn1.setPrefSize(towerBtnWidth, towerBtnHeight);
        towerBtn2.setPrefSize(towerBtnWidth, towerBtnHeight);
        towerBtn3.setPrefSize(towerBtnWidth, towerBtnHeight);
        towerBtn4.setPrefSize(towerBtnWidth, towerBtnHeight);
        towerBtn5.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy1btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy2btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy3btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy4btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy5btn.setPrefSize(towerBtnWidth, towerBtnHeight);

        // Scene setup
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        // Topbar alignment
        topBar.setPrefSize(140, 100);
        showHealthIcon1.setFitWidth(50);
        showHealthIcon1.setFitHeight(50);
        showHealthIcon2.setFitWidth(50);
        showHealthIcon2.setFitHeight(50);
        HBox leftSide = new HBox(healthP1, showHealthIcon1);
        HBox rightSide = new HBox(healthP2, showHealthIcon2);
        topBar.getChildren().addAll(leftSide, topTitle, rightSide);
        topBar.setSpacing(150);
        topBar.setAlignment(Pos.CENTER);
        leftSide.setSpacing(10);
        rightSide.setSpacing(10);
        leftSide.setAlignment(Pos.BOTTOM_LEFT);
        rightSide.setAlignment(Pos.BOTTOM_RIGHT);

        // Health styling
        healthP1.setStyle("-fx-font-size: 40; -fx-text-fill: #ff0000");
        healthP2.setStyle("-fx-font-size: 40; -fx-text-fill: #ff0000");
        topTitle.setStyle("-fx-font-size: 60; -fx-text-fill: #ff9966");
        leftSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7; -fx-border-radius: 20");
        rightSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7; -fx-border-radius: 20");
        leftSide.setPadding(new Insets(10));
        leftSide.setMaxHeight(40);
        rightSide.setPadding(new Insets(10));
        rightSide.setMaxHeight(40);

        // Creating boards for two players
        leftBoard = Board.createPlayerBoard(leftPane, 100, 14, 10, 0);
        rightBoard = Board.createPlayerBoard(rightPane, 100, 14, 10, -1);

        // Activate button functionality in Controller
        TowerSelection.selectTower();

    }

}
