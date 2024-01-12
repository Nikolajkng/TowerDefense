package dk.dtu.app.view.GameBoardsGUI;

import dk.dtu.app.controller.TowerSelection;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;
import dk.dtu.app.view.MenuGUI.Menu;
import dk.dtu.backend.PlayerInfo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class SingleplayerBoard extends Application {

    public static Stage boardStage = new Stage();
    int sizeX = 1400;
    int sizeY = 900;
    Button exitGame = new Button("EXIT");
    Button heartButton = new Button();
    Button tower1bButton = Tower1GUI.tower1;
    Button tower2bButton = Tower2GUI.tower2;
    Button tower3bButton = Tower3GUI.tower3;
    MyPane board = new MyPane();

    // Application layout
    BorderPane borderPane = new BorderPane();
    GridPane pane = new GridPane();

    static VBox leftVbox = new VBox();
    static VBox rightVbox = new VBox();

    @Override
    public void start(Stage stage) {
        boardStage = stage;
        boardStage.setTitle("Single Player");

        borderPane.setLeft(leftVbox);
        borderPane.setRight(rightVbox);
        borderPane.setCenter(pane);
        
        Scene scene = new Scene(borderPane, sizeX, sizeY);
        boardStage.setScene(scene);

        //Visual of the sides 
        leftVbox.setPrefWidth(sizeX / 8);
        leftVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");
        rightVbox.setPrefWidth(sizeX / 8);
        rightVbox.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");

        tower1bButton.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower1.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        tower1bButton.setPrefSize(130, 130);

        tower2bButton.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        tower2bButton.setPrefSize(130, 130);

        tower3bButton.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower3.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        tower3bButton.setPrefSize(130, 130);

        //Other buttons design
        heartButton.setText("" + PlayerInfo.getLife());
        heartButton.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/heart.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; "
        + "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        heartButton.setPrefSize(140, 140);

        Image coin = new Image("/dk/dtu/app/view/Images/coin.png");
        ImageView imageView = new ImageView(coin);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        Button coinButton = new Button("" + PlayerInfo.getMoney(), imageView);
        coinButton.setStyle("-fx-background-size: cover; -fx-background-color: transparent; "
        + "-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Commic Sans MS'; -fx-font-weight: bold;");
        coinButton.setPrefSize(140, 140);

        //Effect for the buttons
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
        + "-fx-font-size: 1.5em; -fx-min-width: 150px; -fx-min-height: 25px; "
        + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; 

        exitGame.setStyle(buttonStyle);
        exitGame.setOnMouseEntered(e -> exitGame.setStyle(buttonStyle + hoverStyle));
        exitGame.setOnMouseExited(e -> exitGame.setStyle(buttonStyle));
        exitGame.setOnAction(this::exitGame);

        //Left side bar 
        leftVbox.getChildren().addAll(heartButton, tower1bButton, tower2bButton, tower3bButton, coinButton);
        leftVbox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(heartButton, new javafx.geometry.Insets(20, 0, 0, 0));
        VBox.setMargin(tower1bButton, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(tower2bButton, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(tower3bButton, new javafx.geometry.Insets(45, 0, 0, 0));
        VBox.setMargin(coinButton, new javafx.geometry.Insets(45, 0, 0, 0));
        
        //Right side bar
        rightVbox.getChildren().addAll(exitGame);
        rightVbox.setAlignment(Pos.BOTTOM_CENTER);
        VBox.setMargin(exitGame, new javafx.geometry.Insets(0, 0, 80, 0));
        
        BoardController.createPlayerBoard(board, 0);
    
        TowerSelection.selectTower();
    }
    
    private void exitGame(ActionEvent event) {
        Menu.mainMenuStage.show();
        boardStage.close();
    }
}