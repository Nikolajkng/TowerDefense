package dk.dtu.app.view.MenuGUI;

import java.net.MalformedURLException;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Menu extends Application{

    private int sizeX = 700;
    private int sizeY = 700;
    public Button multiplayerBtn = new Button();
    public Button exitBtn = new Button();
    public Button rulesBtn = new Button(); 
    public static Stage mainMenuStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
         mainMenuStage = primaryStage;
         mainMenuStage.setResizable(false);
        // Application layout
        mainMenuStage.setTitle("Main Menu");

        // Buttons
        Button multiplayerBtn = new Button();
        multiplayerBtn.setText("Multiplayer");
        multiplayerBtn.setOnAction(this::multiplayerMenu);

        Button rulesBtn = new Button();
        rulesBtn.setText("Game Rules"); // Set text for the rules button
        rulesBtn.setOnAction(this::showGameRules); 

        Button exitBtn = new Button();
        exitBtn.setText("Exit");
        exitBtn.setOnAction(this::closeProgram);


        // Changed this one - it works
        Image image = new Image(getClass().getResource("/dk/dtu/app/view/Images/TowerDefensev3.png").toExternalForm());

        // Rest of your code remains the same
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
        imageView.setPreserveRatio(true);

         // Opret og konfigurer knappen
        // Opret en StackPane og tilføj ImageView og Button
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, multiplayerBtn, rulesBtn, exitBtn); // Knap oven på billedet
        StackPane.setAlignment(multiplayerBtn, Pos.TOP_CENTER);
        StackPane.setAlignment(rulesBtn, Pos.TOP_CENTER);
        StackPane.setAlignment(exitBtn, Pos.TOP_CENTER);
        StackPane.setMargin(multiplayerBtn, new javafx.geometry.Insets(260, 0, 0, 0));
        StackPane.setMargin(rulesBtn, new javafx.geometry.Insets(320, 0, 0, 0));
        StackPane.setMargin(exitBtn, new javafx.geometry.Insets(380, 0, 0, 0));
        
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        multiplayerBtn.setStyle(buttonStyle);
        rulesBtn.setStyle(buttonStyle);
        exitBtn.setStyle(buttonStyle);

        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Gør knappen 10% større i både x- og y-retningen

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
    

    // public void multiplayerBoard (ActionEvent event) throws UnknownHostException, IOException {
    //     MultiplayerBoard multiplayerBoard = new MultiplayerBoard();
    //     multiplayerBoard.start(MultiplayerBoard.boardStage);

    //     // Show the new MultiplayerBoard stage
    //     MultiplayerBoard.boardStage.show();

    //     // Close the current MainMenu stage
    //     mainMenuStage.close();
    // }
    
    private void showGameRules(ActionEvent event) {
        GameRules gameRules = new GameRules();
        gameRules.showGameRules();
        mainMenuStage.close();
    
        // Optionally close the main menu, or handle other logic here
        // mainMenuStage.close();
    }
    

    private void closeProgram (ActionEvent event) {
        System.exit(0);
    }

    public void openProgram (ActionEvent event){
        System.out.println("Open MultiplayerMenu");
    }


    
}
