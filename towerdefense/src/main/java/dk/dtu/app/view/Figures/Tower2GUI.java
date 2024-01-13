package dk.dtu.app.view.Figures;

import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Tower2GUI extends Application {

    public static Button tower2 = createRoundButton("/dk/dtu/app/view/Images/tower2.png");

    @Override
    public void start(Stage primaryStage) throws Exception {

         //tower design
        tower2.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/tower2.png');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: cover; -fx-background-color: transparent; ");
        tower2.setPrefSize(130, 130);
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
    
    public static void placeTower2(int x, int y, MyPane board) {
        Circle Tower2 = new Circle(50);
        Image tower2Image = new Image("/dk/dtu/app/view/Images/tower2.png",false);
        Tower2.setLayoutX(x);
        Tower2.setLayoutY(y);
        Tower2.setFill(new ImagePattern(tower2Image));
        board.getChildren().add(Tower2);
    }
}
