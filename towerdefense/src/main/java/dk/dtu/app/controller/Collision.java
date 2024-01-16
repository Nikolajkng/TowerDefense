package dk.dtu.app.controller;

import dk.dtu.app.controller.Enemy.Enemy;
import javafx.scene.shape.Shape;

public class Collision {
    public static boolean checkCollision(Enemy enemy, Projectile projectile) {
        Shape enemyShape = enemy.getEnemyShape();
        Shape projectileShape = projectile.getProjectileShape();

        // Check if the bounds of the enemy and the projectile intersect
        if (enemyShape.getBoundsInParent().intersects(projectileShape.getBoundsInParent())) {
            System.out.println("Collision detected");
            return true;
        } else {
            System.out.println("No collision");
            return false;
        }
    }
}
