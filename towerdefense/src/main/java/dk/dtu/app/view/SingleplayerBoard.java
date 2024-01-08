package dk.dtu.app.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SingleplayerBoard extends Application{

    int sizeX = 800;
    int sizeY = 600;
    static int gridSize = 10;
    static int windowSize = 600;
    static int btnSize = windowSize / gridSize;
    static MyButton clickedButton;
	static MyButton[][] buttons2D = new MyButton[gridSize][gridSize];
    static Stage boardStage = new Stage();
    static GridPane root = new GridPane();
    static Pane pane = new Pane();
    Scene singlePlayerScene = new Scene(pane, 1200, 625);
    Button btn = new Button("hej");


    public SingleplayerBoard() throws Exception {
        for(int row = 0; row < gridSize; row++){
                for(int column = 0; column < gridSize; column++){
                    MyButton myButton = new MyButton(0);
                    myButton.setPrefSize(btnSize, btnSize);

                    root.add(myButton, row, column);
                    buttons2D[row][column] = myButton;
                }
            }
    }

    @Override
    public void start(Stage stage) {
        boardStage = stage;

        // Application layout
        boardStage.setTitle("Singleplayer Board");
        GridPane board = new GridPane();
        board.getChildren().addAll(
            btn
        );

        //create new stage 
       boardStage.setTitle("Single Player");

       //set the scene for the stage
       boardStage.setScene(singlePlayerScene);
       boardStage.setResizable(false);
       boardStage.show();

    }



    
}
