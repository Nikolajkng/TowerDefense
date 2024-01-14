package dk.dtu.app.controller.Enemy;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy_movement {
    protected int coordinateX;
    protected int coordinateY;
    protected Space space;
    protected int me;
    protected MyPane board;
    protected Circle enemyShape;
    int endCoordinateX;
    int endCoordinateY;

    // Main constructor
    public Enemy_movement(int x, int y, Space space, int me, MyPane myPane) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.space = space;
        this.me = me;
        this.board = myPane;
        this.enemyShape = new Circle(30);

        // Construct the enemy appearance and adds to board
        constructEnemy();
        myPane.getChildren().add(enemyShape);

        // Sets the path for the enemy to move on
        setPath();
        System.out.println("Hi i am a bunny");
    }

    // Extra constructors
    public Enemy_movement(MyPane myPane) {
        this.board = myPane;
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

        // Michelle TO DO:
        enemyShape.setFill(Color.BLACK);

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
        pathT.setCycleCount(PathTransition.INDEFINITE);
        pathT.setOnFinished(e -> {
            board.getChildren().remove(enemyShape);
        });

        // Enemy movement listener:
        enemyShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {

            try {
                Object[] obj = space.get(new FormalField(Integer.class), new ActualField("Coordinates"),
                        new FormalField(Double.class), new FormalField(Double.class));
                if (obj != null) {
                    double centerX = newValue.getMinX() + newValue.getWidth() / 2;
                    double centerY = newValue.getMinY() + newValue.getHeight() / 2;
                    System.out.println("CenterX: " + centerX);
                    System.out.println("CenterY: " + centerY);
                    space.put(me, "Coordinates", centerX, centerY);
                }
            } catch (Exception e) {

            }

        });

        pathT.play();
    }


}
