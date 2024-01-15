package dk.dtu.app.controller.Enemy;

import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy {
    protected MyPane board;
    protected Circle enemyShape;
    protected Color color;

    // Main constructor
    public Enemy(MyPane myPane, Color color) {
        this.board = myPane;
        this.enemyShape = new Circle(30);
        this.color = color;

        // Construct the enemy appearance and adds to board
        constructEnemy();
        Platform.runLater(() -> {
            board.getChildren().add(enemyShape);
        });

        // Sets the path for the enemy to move on
        setPath();
        System.out.println("Hi i am a bunny");
    }

    public Circle getEnemyShape() {
        return enemyShape;
    }

    private void constructEnemy() {

        // Michelle TO DO:
        enemyShape.setFill(color);

    }

    private void setPath() {
        // Board positions
        int startX = 0;
        int interval0 = BoardController.interval0;
        int interval1 = BoardController.interval1;
        int interval2 = BoardController.interval2;
        int interval3 = BoardController.interval3;
        int interval4 = BoardController.interval4;
        int interval5 = BoardController.interval5;
        int interval6 = BoardController.interval6;
        int interval7 = BoardController.interval7;

        // Create a path for the enemy to follow
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, interval0)); // Starting point
        path.getElements().add(new LineTo(interval1, interval0)); // Go right...
        path.getElements().add(new LineTo(interval1, interval2)); // Go down...
        path.getElements().add(new LineTo(interval3, interval2)); // Go right...
        path.getElements().add(new LineTo(interval3, interval4)); // Go up...
        path.getElements().add(new LineTo(interval5, interval4)); // Go right...
        path.getElements().add(new LineTo(interval5, interval6)); // Go down...
        path.getElements().add(new LineTo(interval7, interval6)); // Go right...

        PathTransition pathT = new PathTransition();
        int enemyMovementSpeed = 15;
        pathT.setDuration(Duration.seconds(enemyMovementSpeed));
        pathT.setPath(path);
        pathT.setNode(enemyShape);
        pathT.setCycleCount(1);
        pathT.setOnFinished(e -> {
            Platform.runLater(() -> {
                board.getChildren().remove(enemyShape);
            });
        });

        // Enemy movement listener:
        enemyShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            double centerX = newValue.getMinX() + newValue.getWidth() / 2;
            double centerY = newValue.getMinY() + newValue.getHeight() / 2;
            // System.out.println("CenterX: " + centerX);
            // System.out.println("CenterY: " + centerY);

        });

        pathT.play();
    }
}