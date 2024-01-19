package dk.dtu.app.view.MenuGUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameRules {

    private int sizeX = 1400;
    private int sizeY = 900;

    public void showGameRules() {
        Stage rulesStage = new Stage();
        Label rulesText = new Label(
            "Welcome to our Tower Defense game!\n" +
            "In this game, your goal is to strategically place towers to prevent rabbits from reaching the end of the path. " +
            "Each time one of your towers successfully shoots a rabbit, you earn coins." +
            "These coins can be used to purchase additional towers, enhancing your defense capabilities.\n" +
            "Every rabbit that makes it to the end of the path will cost you a life. " +
            "If you run out of lives, reaching 0, it's game over. \n" +
            "The game features two separate paths: your own on the left side and your opponent's on the right side. " +
            "Plan your strategy wisely, build the ultimate defense, and ensure that no rabbits breach your barriers.\n" +
            "Additionally, there's a chat feature represented by the gray box, where you can communicate with your opponent. \n" +
            "Good luck, and may the best defender win!"
        );
        rulesText.setWrapText(true); // Enable text wrapping
        rulesText.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-family: 'Commic Sans MS'; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 10px;");

        // Load the background image
        Image backgroundImage = new Image("/dk/dtu/app/view/Images/grass_tile_2.png");
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER, 
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        Image gameRulesImage = new Image("/dk/dtu/app/view/Images/GameRulesBoard.png"); // Replace with the correct path
            ImageView gameRulesImageView = new ImageView(gameRulesImage);
            gameRulesImageView.setFitHeight(900); // Set the desired height
            gameRulesImageView.setFitWidth(900); // Set the desired width
            gameRulesImageView.setPreserveRatio(true); // Preserve the aspect ratio
                                                  
        // Define the button styles
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
                           + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; "
                           + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;";

        // Create and style the back to menu button
        Button backToMenu = new Button("Back to Menu");
        backToMenu.setStyle(buttonStyle);
        backToMenu.setOnMouseEntered(e -> backToMenu.setStyle(buttonStyle + hoverStyle));
        backToMenu.setOnMouseExited(e -> backToMenu.setStyle(buttonStyle));
        backToMenu.setOnAction(e -> {
            rulesStage.close();
            Menu.mainMenuStage.show();
        });

    
        //Text for next Button 
        Label nextText = new Label("Next"); // Initialize rulesText variable
        nextText.setStyle("-fx-font-size: 16px; " +
                           "-fx-text-fill: white; " +
                           "-fx-font-family: 'Commic Sans MS';");


        // Layout with the background image
        StackPane layout = new StackPane();
        layout.getChildren().addAll(rulesText, backToMenu, gameRulesImageView); 
        layout.setBackground(new Background(bgImage));
        StackPane.setAlignment(backToMenu, javafx.geometry.Pos.BOTTOM_LEFT);
        StackPane.setMargin(backToMenu, new javafx.geometry.Insets(20));
        StackPane.setAlignment(rulesText, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setAlignment(gameRulesImageView, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(gameRulesImageView, new javafx.geometry.Insets(200, 0, 0, 0)); // Adjust top margin to move image down

        Scene scene = new Scene(layout, sizeX, sizeY);

        rulesStage.setScene(scene);
        rulesStage.show();
    }

    
}
