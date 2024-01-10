package dk.dtu.app.view.GameBoardsGUI;

import java.rmi.Remote;

import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import dk.dtu.app.controller.MyButton;
import dk.dtu.app.controller.TowerSelection;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application {

    protected SequentialSpace space;

    public MultiplayerBoard(SequentialSpace room){
        this.space = room;
    }

    // Global field variables
    public static Stage boardStage = new Stage();
    public static MyButton[][] leftBoard = new MyButton[0][0];
    public MyButton[][] rightBoard = new MyButton[0][0];
    public static Button towerBtn1 = new Button("Tower1 $0");
    public static Button towerBtn2 = new Button("Tower2 $0");
    public static Button towerBtn3 = new Button("Tower3 $0");
    // Local field variables
    GridPane leftPane = new GridPane();
    GridPane rightPane = new GridPane();
    int sizeX = 1600;
    int sizeY = 1000; // MyButton cell = new MyButton(0);
    Button btn3 = new Button("333");
    Button btn4 = new Button("4444");

    

    // Program start
    @Override
    public void start(Stage stage) {

        // Stage setup
        boardStage = stage;
        boardStage.setTitle("Multiplayer Board");

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
        bottomHUD.setSpacing(10);


        // Size of boxPanes
        leftVbox.setPrefWidth(sizeX / 8 - 25);
        rightVbox.setPrefWidth(sizeX / 8 - 25);
        bottomHUD.setPrefHeight(sizeY/8 + 25);
        topBar.setPrefHeight(sizeY/8 + 25);

        
        // Scene setup
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        // Creating boards for two players
        Board board = new Board(space);
        leftBoard = board.createPlayerBoard(leftPane, 100, 14, 10, 0);
        rightBoard = board.createPlayerBoard(rightPane, 100, 14, 10, -1);

        // Position of buttons
        bottomHUD.getChildren().addAll(towerBtn1, towerBtn2, towerBtn3);
        leftVbox.getChildren().addAll(btn3);
        rightVbox.getChildren().addAll(btn4);

        // Button functionality
        towerBtn1.setPrefSize(140, 100);
        towerBtn2.setPrefSize(140, 100);
        towerBtn3.setPrefSize(140, 100);


        // Activate button functionality in Controller
        TowerSelection.selectTower();
        
    
    }


}
