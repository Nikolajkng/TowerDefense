package dk.dtu.app.view;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class SingleplayerBoard extends Application{
   
   static int gridSize = 10;
    static int windowSize = 600;
    static int btnSize = windowSize / gridSize;
    static MyButton clickedButton;
	static MyButton[][] buttons2D = new MyButton[gridSize][gridSize];

    static Stage boardStage = new Stage();
    int sizeX = 1400;
    int sizeY = 900;
    Button btn3 = new Button("Tower");
    Button btn4 = new Button("EXIT");

     // Application layout
        BorderPane borderPane = new BorderPane();
        GridPane pane = new GridPane();

        VBox leftVbox = new VBox();
        VBox rightVbox = new VBox(); 

    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setMaximized(true);
        boardStage.setTitle("Single Player Board");


        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(pane);

        leftVbox.setPrefWidth(sizeX/8);
        rightVbox.setPrefWidth(sizeX/8);

        pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #8B4513;");

        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        leftVbox.getChildren().addAll(btn3);
        
        for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				MyButton myButton = new MyButton(0);
				myButton.setPrefSize(btnSize, btnSize); // Size of one cell

				// Background
				if (column % 2 == 0 && row % 2 == 0) {
					myButton.setStyle("-fx-base: #8B4513"); // Button color 1
				} else {
					myButton.setStyle("-fx-base: #D2B48C;"); // Button color 2
				}
				if (column % 2 != 0 && row % 2 != 0) {
					myButton.setStyle("-fx-base: #8B4513"); // Button color 1
				}

				pane.add(myButton, row, column);
				buttons2D[row][column] = myButton; // Add coordinates and accessibility to all buttons.

                /*ScaleTransition exitButten = new ScaleTransition(Duration.millis(300), btn4);
                exitButten.setFromX(1);
                exitButten.setFromY(1);
                exitButten.setToX(1.1);
                exitButten.setToY(1.1);
        
                ScaleTransition exitShrink = new ScaleTransition(Duration.millis(200), btn4);
                exitShrink.setFromX(1.1);
                exitShrink.setFromY(1.1);
                exitShrink.setToX(1);
                exitShrink.setToY(1);

                btn4.setOnMouseEntered(e -> exitButten.playFromStart());
                btn4.setOnMouseExited(e -> exitShrink.playFromStart());*/

		    /*rightVbox.getChildren().addAll(btn4);
		    btn4.setLayoutX(rightVbox.getWidth() / 2);
		    btn4.setLayoutY(rightVbox.getHeight() / 2);
		    btn4.setStyle("-fx-background-color: #33CC66;"
				+ " -fx-background-radius: 50;"
				+ " -fx-border-color: Black;"
				+ " -fx-border-radius: 50;"
				+ " -fx-text-fill : black;"
				+ " -fx-border-width: 2;"
				+ " -fx-font-size : 20px");*/
            }  
        }       
    }
}
