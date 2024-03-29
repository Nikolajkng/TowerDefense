package dk.dtu.app.view.GameBoardsGUI;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import dk.dtu.app.controller.BattleLogic;
import dk.dtu.app.controller.Projectile;
import dk.dtu.app.controller.TowerSelection;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.backend.Server;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;
import dk.dtu.backend.DisconnectionHandler;
import dk.dtu.backend.PlayerInfo;
import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy;
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
        public static Label healthP1 = new Label(BattleLogic.opponentInfo.getHealth()+"");
        public static Label healthP2 = new Label(BattleLogic.opponentInfo.getHealth()+"");
        public static Label topTitle = new Label("RABBIT HUNTER");
        public static HBox bottomHUD = new HBox();
        public static final int sizeX = 1400;
        public static final int sizeY = 900;
        public static HBox centerPane = new HBox(); 
        public static Label countdownLabel = new Label();
        public static List<Enemy> leftEnemyList = new ArrayList<>();
        public static List<Enemy> rightEnemyList = new ArrayList<>();

        // Local field variables
        private static int numOfEnemiesCreated = 0;
        private Image healthIcon = new Image(
                        getClass().getResource("/dk/dtu/app/view/Images/heart.png").toExternalForm());
        private ImageView showHealthIcon1 = new ImageView(healthIcon);
        private ImageView showHealthIcon2 = new ImageView(healthIcon);
        private static String callsign;
        private static Label coinButton;

        // Constructor
        public MultiplayerBoard(String callsign) {
                MultiplayerBoard.callsign = callsign;
        }

        // Program start
        @Override
        public void start(Stage stage) throws UnknownHostException, IOException {

                // Stage setup
                boardStage = stage;
                boardStage.setTitle("Multiplayer Board");
                boardStage.setResizable(false);
                DisconnectionHandler.handleClosedApplication(boardStage, callsign);

                // Main Layout-structure of MultiplayerBoard
                BorderPane borderPane = new BorderPane();
                VBox leftVbox = new VBox();
                VBox rightVbox = new VBox();
                HBox topBar = new HBox();
                borderPane.setMaxSize(sizeY, sizeX);

                // Positions of all panes
                centerPane.setAlignment(Pos.CENTER);
                //centerPane.setSpacing(100);
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
                bottomHUD.setPrefHeight(sizeY / 8 + 75);
                topBar.setPrefHeight(sizeY / 8 + 25);

                Image coin = new Image("/dk/dtu/app/view/Images/coin.png");
                ImageView imageView = new ImageView(coin);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                coinButton = new Label("" + PlayerInfo.getMoney(), imageView);
                coinButton.setStyle("-fx-background-size: cover; -fx-background-color: transparent; "
                                + "-fx-fill: white; -fx-font-size: 40px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
                coinButton.setPrefSize(140, 140);

                // Left vbox-menu setup:
                leftVbox.getChildren().addAll(towerBtn1, towerBtn2, towerBtn3);
                VBox.setMargin(towerBtn1, new javafx.geometry.Insets(20, 0, 0, 0));
                VBox.setMargin(towerBtn2, new javafx.geometry.Insets(8, 0, 0, 0));
                VBox.setMargin(towerBtn3, new javafx.geometry.Insets(8, 0, 0, 0));

                bottomHUD.getChildren().addAll(coinButton);
                HBox.setMargin(coinButton, new javafx.geometry.Insets(0, 800, 30, 0));

                // Button sizes
                int towerBtnWidth = 115;
                int towerBtnHeight = 110;
                towerBtn1.setPrefSize(towerBtnWidth, towerBtnHeight);
                towerBtn2.setPrefSize(towerBtnWidth, towerBtnHeight);
                towerBtn3.setPrefSize(towerBtnWidth, towerBtnHeight);

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

                leftSide.setPadding(new Insets(10));
                leftSide.setMaxHeight(40);
                rightSide.setPadding(new Insets(10));
                rightSide.setMaxHeight(40);

                // Creating boards for two players
                leftBoard = BoardController.createPlayerBoard(leftPane, 1);
                rightBoard = BoardController.createPlayerBoard(rightPane, -1);
                VBox vBox = new VBox();
                vBox.setPrefWidth(100);
                vBox.setStyle("-fx-background-color: transparent;");
                countdownLabel.setAlignment(Pos.CENTER);
                countdownLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: black; -fx-font-weight: bold;");
                vBox.getChildren().add(countdownLabel);
                vBox.setAlignment(Pos.CENTER);
                centerPane.getChildren().addAll(leftBoard, vBox, rightBoard);

                // Activate button functionality in Controller
                TowerSelection.selectTower();

                // Start construction of chat GUI
                ChatGUI.createChatGUI();

                // Peters TEST:
                try {
                        Server.gameRoom.put("MyBoard", leftBoard);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

        }


        public static void startSpawnEnemy() {
                new Enemy(leftBoard, numOfEnemiesCreated, callsign, true);
                numOfEnemiesCreated++;
                new Enemy(rightBoard, numOfEnemiesCreated, callsign, false);
                numOfEnemiesCreated++;
        }

        public static void projectile(double startX, double startY, double endX, double endY, MyPane board) {
                new Projectile(startX, startY, endX, endY, board);
        }

        

        // Color of background of Panes
        private void colorThePanes(HBox centerPane, VBox leftVbox, VBox rightVbox, HBox bottomHUD, HBox topBar,
                        MyPane leftBoard, MyPane rightBoard) {
                centerPane.setStyle("-fx-background-color:#46910b;");
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
                towerBtn1.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower1pane.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                towerBtn2.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower2pane.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");

                towerBtn3.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower3pane.png');"
                                + "-fx-background-repeat: repeat;"
                                + "-fx-background-size: cover; -fx-background-color: transparent; ");
        }

        public static void changeMoney(int change) {
                BattleLogic.myInfo.setMoney(BattleLogic.myInfo.getMoney() + change);
                coinButton.setText(Integer.toString(PlayerInfo.getMoney()));
                Tower1GUI.tower1.setDisable(PlayerInfo.getMoney() < 50);
                Tower2GUI.tower2.setDisable(PlayerInfo.getMoney() < 100);
                Tower3GUI.tower3.setDisable(PlayerInfo.getMoney() < 200);
        }

}
