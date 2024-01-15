package dk.dtu.app.view.Figures;

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
}
