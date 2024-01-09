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
    Button btn4 = new Button("EXIT");
    Button heartButton = createHeartButton();
    Button Tower1 = createRoundButton();
    Button Tower2 = createRoundButton();
    Button Tower3 = createRoundButton();

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
        leftVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/billeder/grass_tile_1.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        rightVbox.setPrefWidth(sizeX / 8);
        rightVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/billeder/grass_tile_1.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        //tower design
        Tower1.setStyle("-fx-background-image: url('/dk/dtu/app/view/billeder/46.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover;");
        Tower1.setPrefSize(130, 130);

        Tower2.setStyle("-fx-background-image: url('/dk/dtu/app/view/billeder/46.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover;");
        Tower2.setPrefSize(130, 130);

        Tower3.setStyle("-fx-background-image: url('/dk/dtu/app/view/billeder/46.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover;");
        Tower3.setPrefSize(130, 130);

        //Left side bar 
        leftVbox.getChildren().addAll(heartButton, Tower1, Tower2, Tower3);
        VBox.setMargin(Tower1, new Insets(10)); // Optional: Add margin around the button for better visibility
        leftVbox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(heartButton, new javafx.geometry.Insets(40, 0, 0, 0));
        VBox.setMargin(Tower1, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower2, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(Tower3, new javafx.geometry.Insets(45, 0, 0, 0));
        

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

       return heartButton;
}

private Button createRoundButton() {
    // Create a circle to be used as the button's shape
    Circle circle = new Circle(50);
    circle.setStyle("-fx-fill: #ffffff; -fx-stroke: #000000;"); // Customize fill and stroke color

    // Create a button and set the round shape and heart-shaped icon as its graphic
    Button roundButton = new Button();
    roundButton.setShape(circle);
    roundButton.setStyle("-fx-background-color: transparent;"); // Set transparent background
    roundButton.setMinSize(100, 100); // Set the size of the button

    return roundButton;
}


}