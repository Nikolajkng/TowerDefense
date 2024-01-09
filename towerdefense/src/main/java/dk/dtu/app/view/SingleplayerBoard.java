package dk.dtu.app.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class SingleplayerBoard extends Application{
   
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

         /*pane.setAlignment(Pos.CENTER);
        pane.setStyle("-fx-background-color: #8B4513;");*/

        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        leftVbox.getChildren().addAll(btn3);

        Board.createPlayerBoard(pane,86,14,10);
            }  
        }       
