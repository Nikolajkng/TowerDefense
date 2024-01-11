package dk.dtu.app.view.Figures;

import dk.dtu.app.controller.MyButton;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Enemy1_BunnyGUI {
    public static void placeBunny(MyButton clickedButton) {
        Circle bunny = new Circle(25);

        Image bunnyGif = new Image("/dk/dtu/app/view/Images/bunny.gif",false);
        bunny.setFill(new ImagePattern(bunnyGif));

        clickedButton.setGraphic(bunny);
    }

//public static void startPosBunny(){
//    bunny(BoardController.board())
//}
    
}
