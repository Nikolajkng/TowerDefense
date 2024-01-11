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

    public BattleLogic(SequentialSpace space) {
        this.space = space;
        new Thread( new TowerPlacer(space));
    }

    public void waves(int[] enemies) {
        new Thread( new EnemyMach(space, enemies)).start();
    }

}
