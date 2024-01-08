package dk.dtu.app.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application{

    private int sizeX = 800;
    private int sizeY = 600;
    private Button singleplayerBtn = new Button();
    private Button multiplayerBtn = new Button();
    private Button exitBtn = new Button();
    private Stage mainMenuStage = new Stage();

    @Override
    public void start(Stage primaryStage) {
         mainMenuStage = primaryStage;
        // Application layout
        mainMenuStage.setTitle("Main Menu");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(
            singleplayerBtn,
            multiplayerBtn,
            exitBtn);

        Scene scene = new Scene(vbox, sizeX, sizeY);
        mainMenuStage.setScene(scene);
        mainMenuStage.show();


        // Buttons
        singleplayerBtn.setText("Singleplayer");
        singleplayerBtn.setOnAction(this::startSingleplayerGame);

        multiplayerBtn.setText("Multiplayer");
        multiplayerBtn.setOnAction(this::multiplayerMenu);

        exitBtn.setText("Exit");
        exitBtn.setOnAction(this::closeProgram);

    }

    // Button interaction and functions
    private void startSingleplayerGame(ActionEvent event) {
        SingleplayerBoard singleplayerBoard = new SingleplayerBoard();
        singleplayerBoard.start(SingleplayerBoard.boardStage);
    
        // Close the current MainMenu stage
        mainMenuStage.close();
    
        // Show the new SingleplayerBoard stage
        SingleplayerBoard.boardStage.show();
    }
    

    private void multiplayerMenu (ActionEvent event) {
        MultiplayerBoard multiplayerBoard = new MultiplayerBoard();
        multiplayerBoard.start(MultiplayerBoard.boardStage);

        // Show the new MultiplayerBoard stage
        MultiplayerBoard.boardStage.show();

        // Close the current MainMenu stage
        mainMenuStage.close();
    }

    private void closeProgram (ActionEvent event) {
        System.exit(0);
    }


    
}
