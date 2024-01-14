package dk.dtu.app.view.GameBoardsGUI;

import java.io.IOException;
import java.net.UnknownHostException;

import dk.dtu.app.controller.TowerSelection;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.backend.Server;
import dk.dtu.app.controller.BoardLogic.ChatController;
import dk.dtu.app.view.Figures.Enemy1_BunnyGUI;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;
import dk.dtu.backend.PlayerConnection;
import dk.dtu.backend.PlayerInfo;
import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application {

        // Global field variables
        public static Stage boardStage = new Stage();
        public static MyPane leftPane = new MyPane();
        public static MyPane rightPane = new MyPane();
        public static MyPane leftBoard = new MyPane();
        public static MyPane rightBoard = new MyPane();
        public static Button towerBtn1 = Tower1GUI.tower1;
        public static Button towerBtn2 = Tower2GUI.tower2;
        public static Button towerBtn3 = Tower3GUI.tower3;
        public static Button attackEnemy1btn = Enemy1_BunnyGUI.enemy1;
        /*
         * public static Button attackEnemy2btn = Enemy1_BunnyGUI.enemy1;
         * public static Button attackEnemy3btn = Enemy1_BunnyGUI.enemy1;
         * public static Button attackEnemy4btn = Enemy1_BunnyGUI.enemy1;
         * public static Button attackEnemy5btn = Enemy1_BunnyGUI.enemy1;
         */
        public static Label healthP1 = new Label("" + PlayerInfo.getLife());
        public static Label healthP2 = new Label("" + PlayerInfo.getLife());
        public static Label topTitle = new Label("RABBIT HUNTER");
        public static HBox bottomHUD = new HBox();
        public static final int sizeX = 1400;
        public static final int sizeY = 900;
        
        // Local field variables
        private Image healthIcon = new Image(
                        getClass().getResource("/dk/dtu/app/view/Images/heart.png").toExternalForm());
        private ImageView showHealthIcon1 = new ImageView(healthIcon);
        private ImageView showHealthIcon2 = new ImageView(healthIcon);
        private String callsign;

        public MultiplayerBoard(String callsign) {
                this.callsign = callsign;
        }

        // Program start
        @Override
        public void start(Stage stage) throws UnknownHostException, IOException {
                // Spawn bunnies
                //Wave.spawnEnemy(leftBoard, 3, new Circle(30));

                // Stage setup
                boardStage = stage;
                boardStage.setTitle("Multiplayer Board");
                boardStage.setResizable(false);
                handleClosedApplication();

                // Main Layout-structure of MultiplayerBoard
                BorderPane borderPane = new BorderPane();
                HBox centerPane = new HBox(); // For the leftboard and rightboard
                VBox leftVbox = new VBox();
                VBox rightVbox = new VBox();
                HBox topBar = new HBox();
                borderPane.setMaxSize(sizeY, sizeX);

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
                leftVbox.getChildren().addAll(towerBtn1, towerBtn2, towerBtn3);
                VBox.setMargin(towerBtn1, new javafx.geometry.Insets(20, 0, 0, 0));
                VBox.setMargin(towerBtn2, new javafx.geometry.Insets(20, 0, 0, 0));
                VBox.setMargin(towerBtn3, new javafx.geometry.Insets(20, 0, 0, 0));

                // Right vbox-menu setup
                rightVbox.getChildren().addAll(attackEnemy1btn /*
                                                                * , attackEnemy2btn, attackEnemy3btn, attackEnemy4btn,
                                                                * attackEnemy5btn
                                                                */);

                // Button sizes
                int towerBtnWidth = 120;
                int towerBtnHeight = 100;
                towerBtn1.setPrefSize(towerBtnWidth, towerBtnHeight);
                towerBtn2.setPrefSize(towerBtnWidth, towerBtnHeight);
                towerBtn3.setPrefSize(towerBtnWidth, towerBtnHeight);
                attackEnemy1btn.setPrefSize(towerBtnWidth, towerBtnHeight);
                /*
                 * attackEnemy2btn.setPrefSize(towerBtnWidth, towerBtnHeight);
                 * attackEnemy3btn.setPrefSize(towerBtnWidth, towerBtnHeight);
                 * attackEnemy4btn.setPrefSize(towerBtnWidth, towerBtnHeight);
                 * attackEnemy5btn.setPrefSize(towerBtnWidth, towerBtnHeight);
                 */

                // Invoke the coloring of background of Panes
                colorThePanes(centerPane, leftVbox, rightVbox, bottomHUD, topBar, leftBoard, rightBoard);

                // Invoke the styling of tower buttons and enemy buttons
                setStyleButtons();

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
                healthP1.setStyle(
                                "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
                healthP2.setStyle(
                                "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
                topTitle.setStyle(
                                "-fx-fill: white; -fx-font-size: 60px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
                // leftSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7;
                // -fx-border-radius: 20");
                // rightSide.setStyle("-fx-border-color: #00e600; -fx-border-width: 7;
                // -fx-border-radius: 20");
                leftSide.setPadding(new Insets(10));
                leftSide.setMaxHeight(40);
                rightSide.setPadding(new Insets(10));
                rightSide.setMaxHeight(40);

                // Creating boards for two players
                leftBoard = BoardController.createPlayerBoard(leftPane, 1);
                rightBoard = BoardController.createPlayerBoard(rightPane, -1);
                centerPane.getChildren().addAll(leftBoard, rightBoard);

                // Activate button functionality in Controller
                TowerSelection.selectTower();

                // Start construction of chat GUI
                ChatGUI.createChatGUI();

                // TEST: Start spawning enemy after a time delay
                // Wave.spawnEnemy(leftBoard);


                   // Peters TEST:
                   try {
                        Server.gameRoom.put("MyBoard", leftBoard);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            

        }

        // Close the current MultiplayerBoard stage
        private void handleClosedApplication() {
                boardStage.setOnCloseRequest(event -> {

                        //Menu.mainMenuStage.show();
                        if (callsign == "Host") {
                                try {
                                        ChatController.chatRoom.put("lost connection", callsign);
                                        System.out.println(callsign + " lost connection");
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                                PlayerConnection.hostChatListenerThread.interrupt();
                                // try {
                                // Server.P2P1room.put("lost connection");
                                // } catch (InterruptedException e) {
                                // e.printStackTrace();
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
                                // Server.P1P2room.put("lost connection");
                                // } catch (InterruptedException e) {
                                // e.printStackTrace();
                                // }
                                PlayerConnection.clientActionListenerThread.interrupt();
                        }

                });
        }
        // Color of background of Panes
        private void colorThePanes(HBox centerPane, VBox leftVbox, VBox rightVbox, HBox bottomHUD, HBox topBar, MyPane leftBoard, MyPane rightBoard) {
                centerPane.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover;");
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

        }

        // Style of tower buttons and enemy buttons
        private void setStyleButtons() {
                towerBtn1.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower1.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                towerBtn2.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower2.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                towerBtn3.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower3.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                // Enemy design
                attackEnemy1btn.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                /*
                 * attackEnemy2btn.
                 * setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
                 * + "-fx-background-repeat: repeat;"
                 * + "-fx-background-size: cover; -fx-background-color: transparent; ");
                 * 
                 * attackEnemy3btn.
                 * setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
                 * + "-fx-background-repeat: repeat;"
                 * + "-fx-background-size: cover; -fx-background-color: transparent; ");
                 * 
                 * attackEnemy4btn.
                 * setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
                 * + "-fx-background-repeat: repeat;"
                 * + "-fx-background-size: cover; -fx-background-color: transparent; ");
                 * 
                 * attackEnemy5btn.
                 * setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
                 * + "-fx-background-repeat: repeat;"
                 * + "-fx-background-size: cover; -fx-background-color: transparent; ");
                 */

        }

}
