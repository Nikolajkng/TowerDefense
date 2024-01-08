package dk.dtu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Lobby extends Application{

    private int counter = 0;
    private int sizeX = 800;
    private int sizeY = 600;
    private Button button = new Button();
    private Button button2 = new Button();

    @Override
    public void start(Stage primaryStage) {

        // Application layout
        primaryStage.setTitle("Hello World!");
        Pane rootPane = new Pane();
        rootPane.getChildren().addAll(
            button,
            button2);

        Scene scene = new Scene(rootPane, sizeX, sizeY);
        primaryStage.setScene(scene);
        primaryStage.show();


        // Buttons
        button.setText("Im a counter! Click ME!!!");
        button.setOnAction(this::decrementAction);
        button.setLayoutX((sizeX/2 - button.getWidth()) - 100);
        button.setLayoutY(100);


        button2.setText("Im button2! Click ME!!!");
        button2.setOnAction(this::incrementAction);
        button2.setLayoutX((sizeX/2 - button.getWidth()) + 100);
        button2.setLayoutY(100);


    }

    // Button interaction and functions
    private void incrementAction (ActionEvent event) {
        counter++;
        button.setText("" + this.counter);
        System.out.println("You have clicked "+counter+" times!");
    }

    private void decrementAction (ActionEvent event) {
        counter--;
        button.setText("" + this.counter);
        System.out.println("You have clicked "+counter+" times!");
    }
    
}
