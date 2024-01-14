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
    private MyPane board;
    private Circle enemyShape;
    private int health = 100;
    private int speed = 5;
    private int damage = 10;

    public Enemy(MyPane myPane) {
        this.board = myPane;
        this.enemyShape = new Circle(30);
        constructEnemy();
        setPath();
        System.out.println("Hi i am a bunny");
    }

    public Circle getEnemyShape() {
        return enemyShape;
    }

    private void constructEnemy() {
        System.out.println("Constructing enemy...");
        // Enemy GUI appearance
        enemyShape.setFill(Color.BLACK);

        // Set the initial position of the rectangle to the leftmost edge
        int startX = 100;
        int startY = 375;
        enemyShape.setLayoutX(startX);
        enemyShape.setLayoutY(startY);
    }

    public void setPath() {
        System.out.println("Setting path...");
    
        //Create a path for the enemy to follow
        // Path path = new Path();
        // path.getElements().add(new MoveTo(100, 375)); // Starting point
        // path.getElements().add(new LineTo(200, 375)); // Go right...

        // PathTransition pathT = new PathTransition();
        // pathT.setDuration(Duration.seconds(4));
        // pathT.setPath(path);
        // pathT.setNode(enemyShape);
        // pathT.setCycleCount(PathTransition.INDEFINITE);
        // pathT.play();
    }

}
