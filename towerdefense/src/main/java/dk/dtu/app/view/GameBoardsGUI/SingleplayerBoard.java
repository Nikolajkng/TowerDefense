package dk.dtu.app.view.GameBoardsGUI;

import dk.dtu.app.controller.MyButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class SingleplayerBoard extends Application {

    public static Stage boardStage = new Stage();
    int sizeX = 1400;
    int sizeY = 900;
    Button exitGame = new Button("EXIT");
    Button heartButton = createHeartButton();
    Button moneyTracker = new Button("9969 $");

    Button Tower1 = createRoundButton("/dk/dtu/app/view/Images/ZdPH.gif");
    Button Tower2 = createRoundButton("/dk/dtu/app/view/Images/46.png");
    Button Tower3 = createRoundButton("/dk/dtu/app/view/Images/SYKT7E.gif");

    static MyButton placedTowerbButton;

    // Application layout
    BorderPane borderPane = new BorderPane();
    GridPane pane = new GridPane();

    static VBox leftVbox = new VBox();
    static VBox rightVbox = new VBox();

    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setTitle("Single Player Board");

        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(pane);

        //Visual of the sides 
        leftVbox.setPrefWidth(sizeX / 8);
        leftVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        rightVbox.setPrefWidth(sizeX / 8);
        rightVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        //tower design
        Tower1.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/ZdPH.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower1.setPrefSize(130, 130);

        Tower2.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/46.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower2.setPrefSize(130, 130);

        Tower3.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/SYKT7E.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        Tower3.setPrefSize(130, 130);

        moneyTracker.setStyle("-fx-background-size: cover; -fx-background-color: transparent; ");

        exitGame.setStyle("-fx-background-color: #5DADE2; -fx-text-fill: white; " 
                        + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; " 
                        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");

        //Left side bar 
        leftVbox.getChildren().addAll(heartButton, Tower1, Tower2, Tower3, moneyTracker);
        VBox.setMargin(Tower1, new Insets(10)); // Optional: Add margin around the button for better visibility
        leftVbox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(heartButton, new javafx.geometry.Insets(40, 0, 0, 0));
        VBox.setMargin(Tower1, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower2, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower3, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(moneyTracker, new javafx.geometry.Insets(45, 0, 0, 0));
        
        rightVbox.getChildren().addAll(exitGame);
        rightVbox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(exitGame, new javafx.geometry.Insets(0, 0, 80, 0));

        Board.createPlayerBoard(pane, 86, 14, 10, 0);
    }

    private Button createHeartButton() {
       // Create an SVG path representing a heart shape
       SVGPath heartShape = new SVGPath();
       heartShape.setContent("M 10,30 "
                           + "A 20,20 0,0,1 50,30 "
                           + "A 20,20 0,0,1 90,30 "
                           + "Q 90,60 50,90 "
                           + "Q 10,60 10,30 Z");
       heartShape.setStyle("-fx-fill: red; -fx-stroke: red; "); // Customize the color
    
       // Create a button and set the heart-shaped background
       Button heartButton = new Button();
       heartButton.setGraphic(heartShape);
       heartButton.setStyle("-fx-background-color: transparent;"); // Set transparent background
       heartButton.setMinSize(110, 110); // Set the size of the button

       String buttonText = "25"; // Replace this with your desired text
    javafx.scene.text.Text text = new javafx.scene.text.Text(buttonText);
    text.setStyle("-fx-fill: white; -fx-font-size: 20px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");

    // Create a StackPane to overlay the text over the heart shape
    StackPane stackPane = new StackPane();
    stackPane.getChildren().addAll(heartShape, text);

    // Set the StackPane as the graphic for the button
    heartButton.setGraphic(stackPane);

    return heartButton;
}

private Button createRoundButton(String imageUrl) {
   // Create a circle to be used as the button's shape
   Circle circle = new Circle(50);

   // Create a button and set the round shape as its graphic
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
       // Additional actions or changes when mouse enters the button area
   });

   roundButton.setOnMouseExited(e -> {
       roundButton.setStyle(buttonStyle);
       // Additional actions or changes when mouse exits the button area
   });

   return roundButton;
}

}