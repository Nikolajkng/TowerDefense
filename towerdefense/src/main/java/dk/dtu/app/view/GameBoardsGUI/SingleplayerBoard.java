package dk.dtu.app.view.GameBoardsGUI;

import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.view.MenuGUI.Menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class SingleplayerBoard extends Application {

    public static Stage boardStage = new Stage();
    int sizeX = 1400;
    int sizeY = 900;
    Button exitGame = new Button("EXIT");
    Button heartButton = new Button();
    Button moneyTracker = new Button();

    Button Tower1 = createRoundButton("/dk/dtu/app/view/Images/ZdPH.gif");
    Button Tower2 = createRoundButton("/dk/dtu/app/view/Images/giphy.gif");
    Button Tower3 = createRoundButton("/dk/dtu/app/view/Images/SYKT7E.gif");

    // Application layout
    BorderPane borderPane = new BorderPane();
    GridPane pane = new GridPane();

    static VBox leftVbox = new VBox();
    static VBox rightVbox = new VBox();

    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setTitle("Single Player");

        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(pane);
        
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        //Visual of the sides 
        leftVbox.setPrefWidth(sizeX / 8);
        leftVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        rightVbox.setPrefWidth(sizeX / 8);
        rightVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");

        //tower design
        Tower1.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/ZdPH.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower1.setPrefSize(130, 130);

        Tower2.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/giphy.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower2.setPrefSize(130, 130);

        Tower3.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/SYKT7E.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower3.setPrefSize(130, 130);

        //Other buttons design
        heartButton.setText("25");
        heartButton.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/heart.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; "
        + "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        heartButton.setPrefSize(140, 140);

        moneyTracker.setText("9969");
        moneyTracker.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/coin.png');"
        + "-fx-background-size: cover; -fx-background-color: transparent; "
        + "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        moneyTracker.setPrefSize(130, 130);

        //Effect for the buttons
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; 

        exitGame.setStyle(buttonStyle);
        exitGame.setOnMouseEntered(e -> exitGame.setStyle(buttonStyle + hoverStyle));
        exitGame.setOnMouseExited(e -> exitGame.setStyle(buttonStyle));
        exitGame.setOnAction(this::exitGame);

        //Left side bar 
        leftVbox.getChildren().addAll(heartButton, Tower1, Tower2, Tower3, moneyTracker);
        VBox.setMargin(Tower1, new Insets(10)); // Optional: Add margin around the button for better visibility
        leftVbox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(heartButton, new javafx.geometry.Insets(20, 0, 0, 0));
        VBox.setMargin(Tower1, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower2, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower3, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(moneyTracker, new javafx.geometry.Insets(45, 0, 0, 0));
        
        //Right side bar
        rightVbox.getChildren().addAll(exitGame);
        rightVbox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(exitGame, new javafx.geometry.Insets(0, 0, 80, 0));

        BoardController.createPlayerBoard(pane, 86, 14, 10, 0);
    }

   // Create a circle to be used as the button's shape
    private Button createRoundButton(String imageUrl) {
        Circle circle = new Circle(50);
        
        Button roundButton = new Button();
        roundButton.setShape(circle);
        roundButton.setMinSize(100, 100); // Set the size of the button

        // Set hover effects
        String buttonStyle = "-fx-background-image: url('"+ imageUrl +"');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent;";
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Make the button 10% larger in both x and y directions
        
        roundButton.setOnMouseEntered(e -> {
            roundButton.setStyle(buttonStyle + hoverStyle);
        });
        
        roundButton.setOnMouseExited(e -> {
            roundButton.setStyle(buttonStyle);
        });
        
        return roundButton;
    }
    
    private void exitGame(ActionEvent event) {
        Menu.mainMenuStage.show();
        boardStage.close();
    }
}