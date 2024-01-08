package dk.dtu.app.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application{

    static Stage boardStage = new Stage();
    GridPane leftPane = new GridPane();
    GridPane rightPane = new GridPane();
    int sizeX = 1400;
    int sizeY = 900;    //MyButton cell = new MyButton(0);
    Button btn = new Button("hello");
    Button btn2 = new Button("byebye");
    Button btn3 = new Button("333");
    Button btn4 = new Button("4444");


    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setMaximized(true);
        boardStage.setTitle("Multiplayer Board");
        
        
        // Application layout
        BorderPane borderPane = new BorderPane();
        SplitPane splitPane = new SplitPane(leftPane, rightPane);
        VBox leftVbox = new VBox();
        VBox rightVbox = new VBox();

        // Positions of panes
        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(splitPane);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPrefSize(sizeX/4, sizeY/4);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPrefSize(sizeX/4, sizeY/4);

        // Size of panes
        leftVbox.setPrefWidth(sizeX/8);
        rightVbox.setPrefWidth(sizeX/8);

        // Fix the divider in the splitPane
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));

        // Scene setup
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);
        

        // Buttons
        leftPane.getChildren().addAll(btn);
        rightPane.getChildren().addAll(btn2);
        leftVbox.getChildren().addAll(btn3);
        rightVbox.getChildren().addAll(btn4);

        // Creating boards for the two players
        Board.createPlayerBoard(leftPane);
        Board.createPlayerBoard(rightPane);
        

    }







    
}
