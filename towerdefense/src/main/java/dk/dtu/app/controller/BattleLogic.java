package dk.dtu.app.controller;

import org.jspace.Space;

public class BattleLogic {
    private Space space;
    private int startingCoordinateX;
    private int startingCoordinateY;

    public BattleLogic(Space space) {
        this.space = space;
        new Thread( new TowerPlacer(space));
        System.out.println("Started battle logik");
    }

    public void waves(int[] enemies) {
        new Thread( new EnemyMach(space, enemies, startingCoordinateX, startingCoordinateY)).start();
    }

}
