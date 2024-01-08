package dk.dtu.app.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application{

    private int sizeX = 700;
    private int sizeY = 700;
    private Button singleplayerBtn = new Button();
    private Button multiplayerBtn = new Button();
    public Button testBtn = new Button("Nikos test knap"); // Til test af multiplayer boardet.
    private Button exitBtn = new Button();
    private Button rulesBtn = new Button(); 
    public static Stage mainMenuStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
         mainMenuStage = primaryStage;
        // Application layout
        mainMenuStage.setTitle("Main Menu");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 2em; -fx-min-width: 100px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        singleplayerBtn.setStyle(buttonStyle);
        multiplayerBtn.setStyle(buttonStyle);
        rulesBtn.setStyle(buttonStyle);
        exitBtn.setStyle(buttonStyle);
        vbox.getChildren().addAll(
            singleplayerBtn,
            multiplayerBtn,
            rulesBtn,
            exitBtn,
            testBtn);
        
        Scene scene = new Scene(vbox, sizeX, sizeY);
        mainMenuStage.setScene(scene);

       
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Gør knappen 10% større i både x- og y-retningen

        singleplayerBtn.setOnMouseEntered(e -> singleplayerBtn.setStyle(buttonStyle + hoverStyle));
        singleplayerBtn.setOnMouseExited(e -> singleplayerBtn.setStyle(buttonStyle));

        multiplayerBtn.setOnMouseEntered(e -> multiplayerBtn.setStyle(buttonStyle + hoverStyle));
        multiplayerBtn.setOnMouseExited(e -> multiplayerBtn.setStyle(buttonStyle));

        rulesBtn.setOnMouseEntered(e -> rulesBtn.setStyle(buttonStyle + hoverStyle));
        rulesBtn.setOnMouseExited(e -> rulesBtn.setStyle(buttonStyle));

        exitBtn.setStyle(buttonStyle);
        exitBtn.setOnMouseEntered(e -> exitBtn.setStyle(buttonStyle + hoverStyle));
        exitBtn.setOnMouseExited(e -> exitBtn.setStyle(buttonStyle));


        // Buttons
        singleplayerBtn.setText("Singleplayer");
        singleplayerBtn.setOnAction(this::startSingleplayerGame);

        multiplayerBtn.setText("Multiplayer");
        multiplayerBtn.setOnAction(this::multiplayerMenu);

        rulesBtn.setText("Game Rules"); // Set text for the rules button
        rulesBtn.setOnAction(this::showGameRules); 

        exitBtn.setText("Exit");
        exitBtn.setOnAction(this::closeProgram);

        testBtn.setOnAction(this::multiplayerBoard);

        mainMenuStage.show();
                
        // Definer baggrundsbilledet og dens egenskaber
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:TowerDefensev3.png", sizeX, sizeY, false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);

        // Anvend baggrunden på din VBox (eller en anden layout-container)
        vbox.setBackground(background);

        // Buttons
        singleplayerBtn.setText("Singleplayer");
        singleplayerBtn.setOnAction(this::startSingleplayerGame);

        multiplayerBtn.setText("Multiplayer");
        multiplayerBtn.setOnAction(this::multiplayerMenu);

        rulesBtn.setText("Game Rules"); // Set text for the rules button
        rulesBtn.setOnAction(this::showGameRules); 

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

    private void multiplayerMenu(ActionEvent event) {
        MultiplayerMenu multiplayerMenu = new MultiplayerMenu();

        // Start the MultiplayerMenu stage
        try {
            multiplayerMenu.start(MultiplayerMenu.boardStage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        MultiplayerMenu.boardStage.show();
        mainMenuStage.close();
    }
    

    private void multiplayerBoard (ActionEvent event) {
        MultiplayerBoard multiplayerBoard = new MultiplayerBoard();
        multiplayerBoard.start(MultiplayerBoard.boardStage);

        // Show the new MultiplayerBoard stage
        MultiplayerBoard.boardStage.show();

        // Close the current MainMenu stage
        mainMenuStage.close();
    }
    
    private void showGameRules(ActionEvent event) {
        // Implement logic to display the game rules (e.g., in a dialog or separate screen)
        // You can use a Pop-up, Dialog, or navigate to a different scene for displaying rules.
        // For simplicity, you can show a message here.
        System.out.println("Game Rules:"); // Replace with your game rules display logic.
    }

    private void closeProgram (ActionEvent event) {
        System.exit(0);
    }

    public void openProgram (ActionEvent event){
        System.out.println("Open MultiplayerMenu");
    }


    
}
