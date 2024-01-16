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
    private static boolean projectileEnd = false;
    public static double projX;
    public static double projY;

    public Projectile(double startX, double startY, double endX, double endY, MyPane board) {
        double maxLength = 30; // maximum length of the line

        // Calculate the distance between A and B
        double distance = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

        double newEndX;
        double newEndY;
        // If the distance is greater than the maximum length, adjust the endpoint
        
            double ratio = maxLength / distance;
            newEndX = startX + (endX - startX) * ratio;
            newEndY = startY + (endY - startY) * ratio;

        //Line projectileShape = new Line(startX, startY, newEndX, newEndY);
        // Michelle to do:
        //projectileShape.setStroke(Color.RED);

        Path carrotShape = carrotShape(startX, startY, newEndX, newEndY);
        Platform.runLater(() -> {
            board.getChildren().add(carrotShape); ////
        });

        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY)); // Starting point
        path.getElements().add(new LineTo(endX, endY)); // Go right...

        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(0.5));
        pathT.setPath(path);
        pathT.setNode(carrotShape); 
        pathT.setCycleCount(1);
        pathT.setOnFinished(e -> {
            board.getChildren().remove(carrotShape); 
            System.out.println("-------------------");
          
        });
        pathT.play();



        carrotShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
                projX = newValue.getMinX() + newValue.getWidth() / 2;
                projY = newValue.getMinY() + newValue.getHeight() / 2;
                System.out.println("Projectile coordinates: " + projX);
                System.out.println("Projectile coordinates: " + projY);
          
                    
        });
    }

    private Path carrotShape(double startX, double startY, double endX, double endY){
        
        Path carrotShape = new Path();
        carrotShape.getElements().add(new MoveTo(startX,startY));
        carrotShape.getElements().add(new LineTo(endX,endY));
        carrotShape.setStrokeType(StrokeType.CENTERED);
        carrotShape.setStrokeLineCap(StrokeLineCap.ROUND);
        carrotShape.setStrokeLineJoin(StrokeLineJoin.ROUND);
        carrotShape.setStrokeWidth(4);
        carrotShape.setStroke(Paint.valueOf("orange"));
        
        return carrotShape;
    }
}
