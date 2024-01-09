package dk.dtu.app.view;

import dk.dtu.app.controller.MyButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class SingleplayerBoard extends Application {

    static Stage boardStage = new Stage();
    int sizeX = 1600;
    int sizeY = 1000;
    Button btn3 = new Button("Tower");
    Button btn4 = new Button("EXIT");

    static Circle Tower;
    static MyButton placedTowerbButton;

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

        leftVbox.setPrefWidth(sizeX / 8);
        rightVbox.setPrefWidth(sizeX / 8);

        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        leftVbox.getChildren().addAll(btn3);

        Board.createPlayerBoard(pane, 86, 14, 10, 0);
    }
}