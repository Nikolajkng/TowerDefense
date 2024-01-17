package dk.dtu.app.controller;

import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Projectile {
    public static double getX;
    public static double getY;
    public Path carrotShape;

    public Projectile(double startX, double startY, double endX, double endY, MyPane board) {
        double maxLength = 20; // maximum length of the line

        // Calculate the distance between A and B
        double distance = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

        double newEndX;
        double newEndY;
        // If the distance is greater than the maximum length, adjust the endpoint

        double ratio = maxLength / distance;
        newEndX = startX + (endX - startX) * ratio;
        newEndY = startY + (endY - startY) * ratio;

        // Projectile shape
        carrotShape = carrotShape(startX, startY, newEndX, newEndY);
        Platform.runLater(() -> {
            board.getChildren().add(carrotShape); ////
        });

        // Create a linear path for projectile
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));

        // Set the path for projectile
        double projectileSpeed = 0.25;
        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(projectileSpeed));
        pathT.setPath(path);
        pathT.setNode(carrotShape);
        pathT.setCycleCount(1);
        pathT.setOnFinished(e -> {
            board.getChildren().remove(carrotShape);

        });
        pathT.play();

        // Detect the projectiles movement each millisec
        carrotShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
          
            // Check for collision with bunny during movement traversal
            Collision.checkForCollision(carrotShape, board);
  
        });
    }


    private Path carrotShape(double startX, double startY, double endX, double endY) {

        Path carrotShape = new Path();
        carrotShape.getElements().add(new MoveTo(startX, startY));
        carrotShape.getElements().add(new LineTo(endX, endY));
        carrotShape.setStrokeType(StrokeType.CENTERED);
        carrotShape.setStrokeLineCap(StrokeLineCap.ROUND);
        carrotShape.setStrokeLineJoin(StrokeLineJoin.MITER);
        carrotShape.setStrokeWidth(5);
        carrotShape.setStroke(Paint.valueOf("orange"));

        return carrotShape;
    }

}
