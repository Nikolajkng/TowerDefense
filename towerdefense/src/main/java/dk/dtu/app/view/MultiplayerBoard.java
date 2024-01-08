package dk.dtu.app.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MultiplayerBoard extends Application{

    int sizeX = 800;
    int sizeY = 600;
    static Stage boardStage = new Stage();
    Button btn = new Button("hej");


    @Override
    public void start(Stage stage) {
        boardStage = stage;

        // Application layout
        boardStage.setTitle("Singleplayer Board");
        GridPane board = new GridPane();
        board.getChildren().addAll(
            btn
        );

        Scene scene = new Scene(board, sizeX, sizeY);
        boardStage.setScene(scene);



    }



    
}
