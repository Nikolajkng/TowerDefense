package dk.dtu.app.controller;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Collision {
    private static Circle enemyShape;
    
    Collision(Circle enemyShape){
        this.enemyShape = enemyShape;
    }

    // public static boolean checkCollision(){
    //     Rectangle projectile = new Rectangle();

    //     if (projectile.getBoundsInParent().intersects(enemyShape.getBoundsInParent())) {
    //         System.out.println("Collision detected");
    //         return true;
    //     } else {
    //         System.out.println("No collision");
    //         return false;
    //     }
    // }
    
}
