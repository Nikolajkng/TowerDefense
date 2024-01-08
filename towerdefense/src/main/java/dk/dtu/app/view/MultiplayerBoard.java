package dk.dtu.app.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application{

    static Stage boardStage = new Stage();
    int sizeX = 1200;
    int sizeY = 800;
    Button btn = new Button("hello");
    Button btn2 = new Button("byebye");
    Button btn3 = new Button("333");
    Button btn4 = new Button("4444");


    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setTitle("Multiplayer Board");
        
        
        // Application layout
        BorderPane borderPane = new BorderPane();
        GridPane leftPane = new GridPane();
        GridPane rightPane = new GridPane();
        SplitPane splitPane = new SplitPane(leftPane, rightPane);
        HBox toolbar = new HBox();
        borderPane.setCenter(splitPane);
        borderPane.setBottom(toolbar);
        leftPane.setAlignment(Pos.CENTER);
        rightPane.setAlignment(Pos.CENTER);
        leftPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));


        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        // Buttons
        leftPane.getChildren().addAll(btn);
        rightPane.getChildren().addAll(btn2);
        



    }



    
}
