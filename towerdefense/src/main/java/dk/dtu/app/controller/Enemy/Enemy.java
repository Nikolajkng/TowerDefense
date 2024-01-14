package dk.dtu.app.controller.Enemy;

import dk.dtu.app.controller.BoardLogic.MyPane;
import javafx.animation.PathTransition;
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
        int startY = 375;

        // Create a path for the enemy to follow
        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY)); // Starting point
        path.getElements().add(new LineTo(150, 375)); // Go right...

        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(3));
        pathT.setPath(path);
        pathT.setNode(enemyShape);
        pathT.setCycleCount(PathTransition.INDEFINITE);
        pathT.play();
    }

}
