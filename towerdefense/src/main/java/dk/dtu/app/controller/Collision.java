package dk.dtu.app.controller;

import dk.dtu.app.controller.Enemy.Enemy;

public class Collision {
    protected static double projX;
    protected static double projY;
    protected static double enemyX;
    protected static double enemyY;

    public Collision(){
        projX = Projectile.getX;
        projY = Projectile.getY;
        enemyX = Enemy.getX;
        enemyY = Enemy.getY;
        startDetection();
    }

    // Start the collision detection
    public static void startDetection(){
        if (projX == enemyX && projY == enemyY){
            System.out.println("Collision detected");
        } else {
            System.out.println("nothing");
        }

    }


}

