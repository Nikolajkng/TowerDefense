package dk.dtu.app.controller;

import org.jspace.SequentialSpace;

public class BattleLogic {
    SequentialSpace space;
    private int startingCoordinateX;
    private int startingCoordinateY;

    public int getStartingCoordinateX() {
        return startingCoordinateX;
    }

    public int getStartingCoordinateY() {
        return startingCoordinateY;
    }

    public BattleLogic(SequentialSpace space, int x, int y) {
        this.space = space;
        startingCoordinateX = x;
        startingCoordinateY = y;
        new Thread( new TowerPlacer(space, startingCoordinateX, startingCoordinateY));
    }

    public void wave1(int[] enemies) {
        new Thread( new EnemyMach(space, startingCoordinateX, startingCoordinateY, enemies)).start();
    }

}
