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

public class MultiplayerMenu extends Application{

    private int sizeX = 700;
    private int sizeY = 700;
    private Button backButton = new Button("Back to Menu");
    private Button joinButton = new Button("Join Game");
    private Button hostButton = new Button("Host Game");
    public static Stage boardStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
         boardStage = primaryStage;
        // Application layout
        boardStage.setTitle("Multiplayer Menu");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(
            joinButton,
            hostButton,
            backButton);

        // Style of buttons
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 2em; -fx-min-width: 100px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        joinButton.setStyle(buttonStyle);
        hostButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);
        
        
        Scene scene = new Scene(vbox, sizeX, sizeY);
        boardStage.setScene(scene);

       
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Gør knappen 10% større i både x- og y-retningen

        joinButton.setOnMouseEntered(e -> joinButton.setStyle(buttonStyle + hoverStyle));
        joinButton.setOnMouseExited(e -> joinButton.setStyle(buttonStyle));
        hostButton.setOnMouseEntered(e -> hostButton.setStyle(buttonStyle + hoverStyle));
        hostButton.setOnMouseExited(e -> hostButton.setStyle(buttonStyle));
        backButton.setOnMouseEntered(e -> backButton.setStyle(buttonStyle + hoverStyle));
        backButton.setOnMouseExited(e -> backButton.setStyle(buttonStyle));



        // Buttons
        joinButton.setOnAction(this::joinGame);

        hostButton.setOnAction(this::hostGame);

        backButton.setOnAction(this::backToMenu);

        // Show the stage
        boardStage.show();
                
        // Background of the Menu
        BackgroundImage backgroundImage = new BackgroundImage(
            new Image("file:TowerDefensev3.png", sizeX, sizeY, false, true),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);
        vbox.setBackground(background);
    }

    ////////////////////////////////////// Button interaction and functions //////////////////////////////////////
    private void joinGame(ActionEvent event) {
        System.out.println("Joining game...");
        // Code here:

    }

    private void hostGame(ActionEvent event){
              System.out.println("Hosting game...");
        // Code here:
        
    }

    private void backToMenu (ActionEvent event) {
        System.out.println("Back to Menu...");

        // Close the current MainMenu stage
        Menu.mainMenuStage.show();
        boardStage.close();
    }
    
    
}
