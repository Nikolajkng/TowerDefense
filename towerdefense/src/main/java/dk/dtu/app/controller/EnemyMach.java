package dk.dtu.app.controller;

import org.jspace.SequentialSpace;

import dk.dtu.Enemies.Enemy1;

public class EnemyMach extends BattleLogic implements Runnable {

    int[] enemies;

    public EnemyMach(SequentialSpace space, int[] enemies) {
        super(space);
        this.enemies = enemies;
    }
    

    @Override
    public void run() {
        int numOfDifferentEnemies = enemies.length;
        
        for (int i = 0; i < numOfDifferentEnemies; i++) {

            for (int j = 0; j < enemies[i]; j++) {
                new Thread(new Enemy1(super.getStartingCoordinateX(), super.getStartingCoordinateY(), space, j)).start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        }
    }
}
