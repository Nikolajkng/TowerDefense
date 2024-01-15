package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.backend.Server;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Projectile {

    public Projectile(double startX, double startY, double endX, double endY, MyPane board) {
        double maxLength = 10; // maximum length of the line

        // Calculate the distance between A and B
        double distance = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

        double newEndX;
        double newEndY;
        // If the distance is greater than the maximum length, adjust the endpoint
        
            double ratio = maxLength / distance;
            newEndX = startX + (endX - startX) * ratio;
            newEndY = startY + (endY - startY) * ratio;

        Line projectileShape = new Line(startX, startY, newEndX, newEndY);
        // Michelle to do:
        projectileShape.setStroke(Color.RED);

        //try later
        // Image carrotImage = new Image(getClass().getResource("/dk/dtu/app/view/Images/carrotarrow.png").toExternalForm())
        //ImagePattern carroImagePattern = new ImagePattern(carrotImage);

        Platform.runLater(() -> {
            board.getChildren().add(projectileShape);
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY)); // Starting point
        path.getElements().add(new LineTo(endX, endY)); // Go right...

        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(0.5));
        pathT.setPath(path);
        pathT.setNode(projectileShape);
        pathT.setCycleCount(1);
        pathT.setOnFinished(e -> {
            board.getChildren().remove(projectileShape);
        });
        pathT.play();
    }

}
