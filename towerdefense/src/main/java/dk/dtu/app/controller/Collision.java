package dk.dtu.app.controller;

import java.util.Iterator;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.PlayerInfo;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.shape.Path;

public class Collision {

    // Invoked under Projectile "boundsInParentProperty"
    public static void checkForCollision(Path carrotShape, MyPane board, PathTransition pathT) {
        Iterator<Enemy> iterator;
        if (board == MultiplayerBoard.leftBoard) {
            iterator = MultiplayerBoard.leftEnemyList.iterator();
        } else {
            iterator = MultiplayerBoard.rightEnemyList.iterator();
        }

        while (iterator.hasNext()) {
            Enemy e = iterator.next();
            if (carrotShape.getBoundsInParent().intersects(e.getEnemyShape().getBoundsInParent())) {
                System.out.println("Collision detected");
                System.out.println("-------------------");

                // Deletes the bunny from Arraylist
                iterator.remove();

                // Stop bunny path to avoid tower conflict
                e.removeCoordinates();
                e.pathT.stop();

                // Update the correct board
                Platform.runLater(() -> {
                    board.getChildren().remove(e.getEnemyShape());
                });

                MultiplayerBoard.changeMoney(10);

                pathT.stop();
                Platform.runLater(() -> {
                    board.getChildren().remove(carrotShape);
                });

                break;

            } else {
                //System.out.println("No collision detected");
            }
        }

    }

}
