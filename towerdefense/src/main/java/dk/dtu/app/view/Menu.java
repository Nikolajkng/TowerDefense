package dk.dtu.app.view;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application{

    private int sizeX = 700;
    private int sizeY = 700;
    public Button singleplayerBtn = new Button();
    public Button multiplayerBtn = new Button();
    public Button testBtn = new Button("Nikos test knap"); // Til test af multiplayer boardet.
    public Button exitBtn = new Button();
    public Button rulesBtn = new Button(); 
    public static Stage mainMenuStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
         mainMenuStage = primaryStage;
        // Application layout
        mainMenuStage.setTitle("Main Menu");

        // Buttons
        Button singleplayerBtn = new Button();
        singleplayerBtn.setText("Singleplayer");
        singleplayerBtn.setOnAction(this::startSingleplayerGame);
        
        Button multiplayerBtn = new Button();
        multiplayerBtn.setText("Multiplayer");
        multiplayerBtn.setOnAction(this::multiplayerMenu);

        Button rulesBtn = new Button();
        rulesBtn.setText("Game Rules"); // Set text for the rules button
        rulesBtn.setOnAction(this::showGameRules); 

        Button exitBtn = new Button();
        exitBtn.setText("Exit");
        exitBtn.setOnAction(this::closeProgram);

    
        // Definer baggrundsbilledet og dens egenskaber
        Image image = new Image(new File("towerdefense/src/main/java/dk/dtu/app/view/billeder/TowerDefensev3.png").toURI().toURL().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
        imageView.setPreserveRatio(true);
         // Opret og konfigurer knappen
        // Opret en StackPane og tilføj ImageView og Button
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, singleplayerBtn, multiplayerBtn, rulesBtn, exitBtn, testBtn); // Knap oven på billedet
        StackPane.setAlignment(singleplayerBtn, Pos.TOP_CENTER);
        StackPane.setAlignment(multiplayerBtn, Pos.TOP_CENTER);
        StackPane.setAlignment(rulesBtn, Pos.TOP_CENTER);
        StackPane.setAlignment(exitBtn, Pos.TOP_CENTER);
        StackPane.setMargin(singleplayerBtn, new javafx.geometry.Insets(200, 0, 0, 0));
        StackPane.setMargin(multiplayerBtn, new javafx.geometry.Insets(260, 0, 0, 0));
        StackPane.setMargin(rulesBtn, new javafx.geometry.Insets(320, 0, 0, 0));
        StackPane.setMargin(exitBtn, new javafx.geometry.Insets(380, 0, 0, 0));
        StackPane.setMargin(testBtn, new javafx.geometry.Insets(440, 0, 0, 0));
        
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        singleplayerBtn.setStyle(buttonStyle);
        multiplayerBtn.setStyle(buttonStyle);
        rulesBtn.setStyle(buttonStyle);
        exitBtn.setStyle(buttonStyle);

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

    
        // Opret Scene med StackPane og sæt den til Stage
        Scene scene2 = new Scene(stackPane, sizeX, sizeY);
        mainMenuStage.setScene(scene2);
        mainMenuStage.show();

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
