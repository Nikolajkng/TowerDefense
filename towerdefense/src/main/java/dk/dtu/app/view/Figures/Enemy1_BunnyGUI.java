package dk.dtu.app.view.Figures;

import dk.dtu.app.controller.MyButton;
import dk.dtu.app.controller.BoardLogic.BoardController;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Enemy1_BunnyGUI {
    public static void placeBunny(MyButton clickedButton) {
        Circle bunny = new Circle(5);
        bunny.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/bunny.gif');"
        + "-fx-background-repeat: repeat;"
        + "-fx-background-size: contain;");

        clickedButton.setGraphic(bunny);
    }

//public static void startPosBunny(){
//    bunny(BoardController.board())
//}
    
}
