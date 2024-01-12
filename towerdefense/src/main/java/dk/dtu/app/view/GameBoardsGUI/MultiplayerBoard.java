package dk.dtu.app.view.GameBoardsGUI;

import java.io.IOException;
import java.net.UnknownHostException;


import dk.dtu.app.controller.MyButton;
import dk.dtu.app.controller.TowerSelection;
import dk.dtu.app.controller.BoardLogic.BoardController;

import dk.dtu.app.controller.BoardLogic.ChatController;
import dk.dtu.app.view.MenuGUI.Menu;
import dk.dtu.backend.PlayerConnection;

import dk.dtu.app.view.Figures.Enemy1_BunnyGUI;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower_HunterGUI;
import dk.dtu.app.view.Figures.Tower_KillerPlant;
import dk.dtu.backend.PlayerInfo;

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
    public static Button plant = Tower1GUI.plant;
    public static Button hunter = Tower_HunterGUI.hunter;
    public static Button killerPlant = Tower_KillerPlant.killerPlant;
    public static Button attackEnemy1btn = Enemy1_BunnyGUI.bunny;
    public static Button attackEnemy2btn = new Button("Attack enemy");
    public static Button attackEnemy3btn = new Button("Attack enemy");
    public static Button attackEnemy4btn = new Button("Attack enemy");
    public static Button attackEnemy5btn = new Button("Attack enemy");
    public static Label healthP1 = new Label("" + PlayerInfo.getLife());
    public static Label healthP2 = new Label("" + PlayerInfo.getLife());
    public static Label topTitle = new Label("RABBIT HUNTER");
    public static HBox bottomHUD = new HBox();
    public static final int sizeX = 1400;
    private static final int sizeY = 900;
    // Local field variables
    private GridPane leftPane = new GridPane();
    private GridPane rightPane = new GridPane();
    private Image healthIcon = new Image(getClass().getResource("/dk/dtu/app/view/Images/heart.png").toExternalForm());
    private ImageView showHealthIcon1 = new ImageView(healthIcon);
    private ImageView showHealthIcon2 = new ImageView(healthIcon);
    private String callsign;

    public MultiplayerBoard(String callsign) {
        this.callsign = callsign;
    }

    // Program start
    @Override
    public void start(Stage stage) throws UnknownHostException, IOException {

        // Stage setup
        boardStage = stage;
        boardStage.setTitle("Multiplayer Board");
        boardStage.setResizable(false);
        boardStage.setMaximized(true);
        boardStage.setOnCloseRequest(event -> {

            Menu.mainMenuStage.show();
            if (callsign == "Host") {
                try {
                    ChatController.chatRoom.put("lost connection", callsign);
                    System.out.println(callsign + " lost connection");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PlayerConnection.hostChatListenerThread.interrupt();
                // try {
                //     Server.P2P1room.put("lost connection");
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                PlayerConnection.hostActionListenerThread.interrupt();
            } else {
                try {
                    ChatController.chatRoom.put("lost connection", callsign);
                    System.out.println(callsign + " lost connection");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PlayerConnection.clientChatListenerThread.interrupt();
                // try {
                //     Server.P1P2room.put("lost connection");
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                PlayerConnection.clientActionListenerThread.interrupt();
            }

            System.exit(0);

        });

        // Application layout
        BorderPane borderPane = new BorderPane();
        HBox centerPane = new HBox(leftPane, rightPane);
        VBox leftVbox = new VBox();
        VBox rightVbox = new VBox();
        HBox topBar = new HBox();
        borderPane.setMaxSize(sizeY, sizeX);

        // Color of background of Panes
    
        centerPane.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        leftVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        rightVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        bottomHUD.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        topBar.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");

        //Tower design 
        plant.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/ZdPH.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        plant.setPrefSize(130, 130);

        hunter.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/giphy.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        hunter.setPrefSize(130, 130);

        killerPlant.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/SYKT7E.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        killerPlant.setPrefSize(130, 130);

        //Enemie design 
        attackEnemy1btn.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        attackEnemy1btn.setPrefSize(130, 130);

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
        bottomHUD.setPrefHeight(sizeY / 8 + 35);
        topBar.setPrefHeight(sizeY / 8 + 25);

        // Left vbox-menu setup:
        leftVbox.getChildren().addAll(plant, hunter, killerPlant);
        VBox.setMargin(plant, new javafx.geometry.Insets(20, 0, 0, 0));
        VBox.setMargin(hunter, new javafx.geometry.Insets(20, 0, 0, 0));
        VBox.setMargin(killerPlant, new javafx.geometry.Insets(20, 0, 0, 0));
        
        // Right vbox-menu setup

        rightVbox.getChildren().addAll(attackEnemy1btn, attackEnemy2btn, attackEnemy3btn, attackEnemy4btn,
                attackEnemy5btn);


        // Button sizes
        int towerBtnWidth = 120;
        int towerBtnHeight = 100;
        plant.setPrefSize(towerBtnWidth, towerBtnHeight);
        hunter.setPrefSize(towerBtnWidth, towerBtnHeight);
        killerPlant.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy1btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        /*attackEnemy2btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy3btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy4btn.setPrefSize(towerBtnWidth, towerBtnHeight);
        attackEnemy5btn.setPrefSize(towerBtnWidth, towerBtnHeight);*/

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
        healthP1.setStyle("-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        healthP2.setStyle("-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        topTitle.setStyle("-fx-fill: white; -fx-font-size: 60px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        //leftSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7; -fx-border-radius: 20");
        //rightSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7; -fx-border-radius: 20");
        leftSide.setPadding(new Insets(10));
        leftSide.setMaxHeight(40);
        rightSide.setPadding(new Insets(10));
        rightSide.setMaxHeight(40);

        // Creating boards for two players
        leftBoard = BoardController.createPlayerBoard(leftPane, 90, 12, 10, 0);
        rightBoard = BoardController.createPlayerBoard(rightPane, 90, 12, 10, -1);

        // Activate button functionality in Controller
        TowerSelection.selectTower();

        // Start construction of chat GUI
        ChatGUI.createChatGUI();
        ;

    }

}
