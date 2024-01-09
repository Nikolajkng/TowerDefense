package dk.dtu.app.view;

import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MultiplayerMenu extends Application {

    private int sizeX = 700;
    private int sizeY = 700;
    private Button backButton = new Button("Back to Menu");
    private Button joinButton = new Button("Join Game");
    private Button hostButton = new Button("Host Game");
    public static Stage boardStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        boardStage = primaryStage;
        // Application layout
        boardStage.setTitle("Multiplayer Menu");
        StackPane stackPane = new StackPane();
        StackPane.setAlignment(joinButton, Pos.TOP_CENTER);
        StackPane.setAlignment(hostButton, Pos.TOP_CENTER);
        StackPane.setAlignment(backButton, Pos.TOP_CENTER);
        StackPane.setMargin(joinButton, new javafx.geometry.Insets(200, 0, 0, 0));
        StackPane.setMargin(hostButton, new javafx.geometry.Insets(260, 0, 0, 0));
        StackPane.setMargin(backButton, new javafx.geometry.Insets(320, 0, 0, 0));

        // Style of buttons
        String buttonStyle = "-fx-background-color: #5DADE2; -fx-text-fill: white; "
                + "-fx-font-size: 2em; -fx-min-width: 100px; -fx-min-height: 25px; "
                + "-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;";
        joinButton.setStyle(buttonStyle);
        hostButton.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle);

        // Scene setup
        Scene scene = new Scene(stackPane, sizeX, sizeY);
        boardStage.setScene(scene);

        // Background image
        Image image = new Image(
                getClass().getResource("/dk/dtu/app/view/billeder/TowerDefensev3.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
        imageView.setPreserveRatio(true);

        // Layering background and buttons on pane
        stackPane.getChildren().addAll(imageView, joinButton, hostButton, backButton); // Knap oven på billedet

        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Gør knappen 10% større i både x- og y-retningen

        joinButton.setOnMouseEntered(e -> joinButton.setStyle(buttonStyle + hoverStyle));
        joinButton.setOnMouseExited(e -> joinButton.setStyle(buttonStyle));
        hostButton.setOnMouseEntered(e -> hostButton.setStyle(buttonStyle + hoverStyle));
        hostButton.setOnMouseExited(e -> hostButton.setStyle(buttonStyle));
        backButton.setOnMouseEntered(e -> backButton.setStyle(buttonStyle + hoverStyle));
        backButton.setOnMouseExited(e -> backButton.setStyle(buttonStyle));

        // Buttons
        joinButton.setOnAction(this::joinGame);

        hostButton.setOnAction(this::hostGame);

        backButton.setOnAction(this::backToMenu);

        // Show the stage
        boardStage.show();

    }

    ////////////////////////////////////// Button interaction and functions //////////////////////////////////////
    private void joinGame(ActionEvent event) {
        System.out.println("Joining game...");

        // Code here:
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Joining Game");
        dialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
        dialog.setContentText("Enter Ipv4 address:");

        // Show the dialog and wait for the user's response
        dialog.showAndWait().ifPresent(userIP -> {
            System.out.println("Entered IP is: " + userIP);
            // Process the userIP here:

        });

    }

    private void hostGame(ActionEvent event) {
        System.out.println("Hosting game...");
        // Code here:

    }

    private void backToMenu(ActionEvent event) {
        System.out.println("Back to Menu...");

        // Close the current MainMenu stage
        Menu.mainMenuStage.show();
        boardStage.close();
    }

}
