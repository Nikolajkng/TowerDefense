package dk.dtu.app.controller;

import java.util.Iterator;

import dk.dtu.app.controller.Enemy.Enemy;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;
import javafx.scene.shape.Path;

public class Collision {

    // Invoked under Projectile "boundsInParentProperty"
    public static void checkForCollision(Path carrotShape) {
        Iterator<Enemy> iterator = MultiplayerBoard.enemyList.iterator();
        while (iterator.hasNext()) {
            Enemy e = iterator.next();
            if (carrotShape.getBoundsInParent().intersects(e.getEnemyShape().getBoundsInParent())) {
                System.out.println("Collision detected");
                // Deletes the bunny from Arraylist
                iterator.remove(); // Use iterator's remove method

                // Stop bunny path to avoid tower conflict
                e.removeCoordinates();
                e.pathT.stop();

                // Update the correct board (doesnt work yet)
                if (e.me % 2 == 0) {
                    Platform.runLater(() -> {
                        MultiplayerBoard.leftBoard.getChildren().remove(e.getEnemyShape());
                    });
                } else {
                    Platform.runLater(() -> {
                        MultiplayerBoard.rightBoard.getChildren().remove(e.getEnemyShape());
                    });
                }
            } else {
                return;
            }
        }

    }

}
