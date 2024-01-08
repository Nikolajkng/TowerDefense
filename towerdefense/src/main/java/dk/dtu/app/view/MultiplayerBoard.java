package dk.dtu.app.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application{

    int sizeX = 800;
    int sizeY = 600;
    static Stage boardStage = new Stage();
    Button btn = new Button("hello");


    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setTitle("Multiplayer Board");
        
        
        // Application layout
        BorderPane borderPane = new BorderPane();
        GridPane boardLeft = new GridPane();
        GridPane boardRight = new GridPane();
        borderPane.setLeft(boardLeft);
        borderPane.setRight(boardRight);
        
        
        GridPane board = new GridPane();
        board.getChildren().addAll(
            btn
        );

        Scene scene = new Scene(board, sizeX, sizeY);
        boardStage.setScene(scene);



    }



    
}
