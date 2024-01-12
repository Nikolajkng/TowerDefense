package dk.dtu.app.view.Figures;

import dk.dtu.app.controller.MyButton;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Tower3GUI extends Application {

    public static Button tower3 = createRoundButton("/dk/dtu/app/view/Images/carrot-tower3-game.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

         //tower design
        tower3.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/carrot-tower3-game.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        tower3.setPrefSize(130, 130);
    }

     // Create a circle to be used as the button's shape
    public static Button createRoundButton(String imageUrl) {
        Circle circle = new Circle(50);
        
        Button roundButton = new Button();
        roundButton.setShape(circle);
        roundButton.setMinSize(100, 100); // Set the size of the button

        // Set hover effects
        String buttonStyle = "-fx-background-image: url('"+ imageUrl +"');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent;";
        String hoverStyle = "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"; // Make the button 10% larger in both x and y directions
        
        roundButton.setOnMouseEntered(e -> {
            roundButton.setStyle(buttonStyle + hoverStyle);
        });
        
        roundButton.setOnMouseExited(e -> {
            roundButton.setStyle(buttonStyle);
        });
        
        return roundButton;
    }
    
    public static void placeTower3(MyButton clickedButton) {
         Rectangle tower3 = new Rectangle(45, 45);

        Image tower3Image = new Image("/dk/dtu/app/view/Images/carrot-tower3-game.png",false);
        tower3.setFill(new ImagePattern(tower3Image));

        clickedButton.setGraphic(tower3);
    }
}
