package dk.dtu.app.controller.Enemy;

import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy {
    protected MyPane board;
    protected Circle enemyShape;
    protected int health = 100;
    protected int speed = 5;
    protected int damage = 10;

    public Enemy(MyPane myPane) {
        this.board = myPane;
        this.enemyShape = new Circle(30);

        // Construct the enemy appearance and adds to board
        constructEnemy();
        myPane.getChildren().add(enemyShape);

        // Sets the path for the enemy to move on
        setPath();
        System.out.println("Hi i am a bunny");
    }

    public Circle getEnemyShape() {
        return enemyShape;
    }

    private void constructEnemy() {
        System.out.println("Constructing enemy...");
        enemyShape.setFill(Color.BLACK);

    }

    private void setPath() {
        System.out.println("Setting path for enemy to move on...");

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
        pathT.setCycleCount(PathTransition.INDEFINITE);


        enemyShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
                // Your existing code inside the listener
                double centerX = newValue.getMinX() + newValue.getWidth() / 2;
                double centerY = newValue.getMinY() + newValue.getHeight() / 2;
                System.out.println("CenterX: " + centerX);
                System.out.println("CenterY: " + centerY);
            });

       

        pathT.play();
    }

}
