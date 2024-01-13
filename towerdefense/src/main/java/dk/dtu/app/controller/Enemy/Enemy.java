package dk.dtu.app.controller.Enemy;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
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
    private int health = -1;
    private int speed = -1;
    private int damage = -1;

    public Enemy(MyPane myPane, int health, int speed, int damage) {
        board = myPane;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
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
        int startX = 0;
        int startY = 375;
        enemyShape.setLayoutX(startX);
        enemyShape.setLayoutY(startY);
    }

    public void setPath() {
        System.out.println("Setting path...");
        // Spawns enemyShape on board
        //board.getChildren().add(enemyShape);

        // Create a path for the enemy to follow
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 300)); // Starting point
        path.getElements().add(new LineTo(600, 300)); // Go right...
        path.getElements().add(new LineTo(600, 0)); // Then up...

        PathTransition pathT = new PathTransition();
        pathT.setDuration(Duration.seconds(2));
        pathT.setPath(path);
        pathT.setNode(enemyShape);
        pathT.setCycleCount(PathTransition.INDEFINITE);
        pathT.play();
    }

}
