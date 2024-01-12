package dk.dtu.app.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.jspace.Space;

import dk.dtu.Enemies.Enemy_Bunny;

public class EnemyMach implements Runnable {

    int[] enemies;
    Space space;
    int startingCoordinateX;
    int startingCoordinateY;

    public EnemyMach(Space space, int[] enemies, int x, int y) {
        this.space = space;
        this.enemies = enemies;
        System.out.println("Started enemy mach");
    }

    @Override
    public void run() {
        int numOfDifferentEnemies = enemies.length;

        for (int i = 0; i < numOfDifferentEnemies; i++) {

            for (int j = 0; j < enemies[i]; j++) {
                final int finalJ = j;
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    Enemy_Bunny enemy_Bunny = new Enemy_Bunny(startingCoordinateX, startingCoordinateY, space, finalJ);
                    try {
                        space.put(finalJ,"Coordinates",startingCoordinateX,startingCoordinateY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enemy_Bunny.run();
                });
                try {
                    future.wait(2000);
                } catch (Exception e) {
                }
            }
            System.out.println("created all of these enemies");
        }
    }
}
