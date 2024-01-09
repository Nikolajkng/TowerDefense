package dk.dtu.app.controller;

import org.jspace.SequentialSpace;

import dk.dtu.Enemies.Enemy1;

public class BattleLogic {
    SequentialSpace space;
    int startingCoordinateX;
    int startingCoordinateY;

    public BattleLogic(SequentialSpace space, int x, int y) {
        this.space = space;
        startingCoordinateX = x;
        startingCoordinateY = y;
    }

    public void battle(int[] enemies) throws InterruptedException {
        int numOfDifferentEnemies = enemies.length;
        
        for (int i = 0; i < numOfDifferentEnemies; i++) {

            for (int j = 0; j < enemies[i]; j++) {
                new Thread(new Enemy1(startingCoordinateX, startingCoordinateY, space, j)).start();
                Thread.sleep(500);
            }
        }
        

    }

}
