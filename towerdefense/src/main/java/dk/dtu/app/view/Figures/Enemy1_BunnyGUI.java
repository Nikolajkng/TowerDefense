package dk.dtu.app.view.Figures;

import dk.dtu.app.controller.MyButton;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Enemy1_BunnyGUI {

    public static Circle enemy1;

    public Enemy1_BunnyGUI(Circle circle) {
        enemy1 = circle;
        Image bunnyGif = new Image("/dk/dtu/app/view/Images/bunny.gif", false);
        enemy1.setFill(new ImagePattern(bunnyGif));
        Platform.runLater(() -> {
            enemy1.setFill(new ImagePattern(bunnyGif));
        });

    }

    // // Create a circle to be used as the button's shape
    // public static Button createRoundButton(String imageUrl) {
    // Circle circle = new Circle(50);
    // // tower design
    // enemy1.setStyle("-fx-background-image:
    // url('/dk/dtu/app/view/Images/bunny.gif');"
    // + "-fx-background-repeat: repeat;"
    // + "-fx-background-size: cover; -fx-background-color: transparent; ");
    // enemy1.setPrefSize(130, 130);

    // Button roundButton = new Button();
    // roundButton.setShape(circle);
    // roundButton.setMinSize(100, 100); // Set the size of the button

    // // Set hover effects
    // String buttonStyle = "-fx-background-image: url('" + imageUrl + "');"
    // + "-fx-background-repeat: repeat;"
    // + "-fx-background-size: cover; -fx-background-color: transparent;";
    // String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Make the button
    // 10% larger in both x and y
    // // directions

    // roundButton.setOnMouseEntered(e -> {
    // roundButton.setStyle(buttonStyle + hoverStyle);
    // });

    // roundButton.setOnMouseExited(e -> {
    // roundButton.setStyle(buttonStyle);
    // });

    // return roundButton;
    // }

    public void removeBunny(MyButton clickedButton) {
        Platform.runLater(() -> {
            clickedButton.setGraphic(null);
        });
    }

}
