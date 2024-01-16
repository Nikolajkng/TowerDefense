package dk.dtu.app.controller.Enemy;

import org.jspace.ActualField;
import org.jspace.FormalField;

import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.Server;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Enemy {
    protected MyPane board;
    protected Circle enemyShape;
    protected Color color;
    protected int me;

    // Main constructor
    public Enemy(MyPane myPane, Color color, int me) {
        this.board = myPane;
        this.enemyShape = new Circle(30);
        this.color = color;
        this.me = me;
        System.out.println("gives coordinates");
        try {
            Server.gameRoom.put(me, "Coordinates", 0.0, Double.parseDouble(Integer.toString(BoardController.interval0)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Construct the enemy appearance and adds to board
        constructEnemy();
        Platform.runLater(() -> {
            myPane.getChildren().add(enemyShape);
        });

        // Sets the path for the enemy to move on
        setPath();
        System.out.println("Hi i am a bunny");
    }

    public Circle getEnemyShape() {
        return enemyShape;
    }

    private void constructEnemy() {
        Image bunnyGif = new Image("/dk/dtu/app/view/Images/bunny.gif", false);
        enemyShape.setFill(new ImagePattern(bunnyGif));
        Platform.runLater(() -> {
            enemyShape.setFill(new ImagePattern(bunnyGif));
        });
        // Michelle TO DO:
        //enemyShape.setFill(color);

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
        path.getElements().add(new MoveTo(startX+15, interval0 - 10)); // Starting point
        path.getElements().add(new LineTo(interval1, interval0 - 10)); // Go right...
        path.getElements().add(new LineTo(interval1, interval2 - 10)); // Go down...
        path.getElements().add(new LineTo(interval3, interval2 - 10)); // Go right...
        path.getElements().add(new LineTo(interval3, interval4 - 10)); // Go up...
        path.getElements().add(new LineTo(interval5, interval4 - 10)); // Go right...
        path.getElements().add(new LineTo(interval5, interval6 - 10)); // Go down...
        path.getElements().add(new LineTo(interval7, interval6 - 10)); // Go right...

        PathTransition pathT = new PathTransition();
        int enemyMovementSpeed = 30;
        pathT.setDuration(Duration.seconds(enemyMovementSpeed));
        pathT.setPath(path);
        pathT.setNode(enemyShape);
        pathT.setCycleCount(1);
        pathT.setOnFinished(e -> {
            // Delete the rabit when it reaches the end of the path
            board.getChildren().remove(enemyShape);

            // Update health when rabbit reaches end of path
            int rabbitDamage = 1;
            int currentHealthP1 = Integer.parseInt(MultiplayerBoard.healthP1.getText());
            int currentHealthP2 = Integer.parseInt(MultiplayerBoard.healthP2.getText());
            if(currentHealthP1 != 0){
                Platform.runLater(() -> {
                    MultiplayerBoard.healthP1.setText(Integer.toString(currentHealthP1 - rabbitDamage));
                    MultiplayerBoard.healthP2.setText(Integer.toString(currentHealthP2 - rabbitDamage));
                });
            } else{
                System.out.println("Game over a player has lost");
            }
            

            try {
                Server.gameRoom.get(new ActualField(me), new ActualField("Coordinates"),
                            new FormalField(Double.class), new FormalField(Double.class));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        // Enemy movement listener:
        enemyShape.boundsInParentProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("gets coordinates");
            try {
                Server.gameRoom.get(new ActualField(me), new ActualField("Coordinates"),
                        new FormalField(Double.class), new FormalField(Double.class));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            double centerX = newValue.getMinX() + newValue.getWidth() / 2;
            double centerY = newValue.getMinY() + newValue.getHeight() / 2;
            try {
                //System.out.println("gives coordinates");
                Server.gameRoom.put(me, "Coordinates", centerX, centerY);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        pathT.play();
    }
}