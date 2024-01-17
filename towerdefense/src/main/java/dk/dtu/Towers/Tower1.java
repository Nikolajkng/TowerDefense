package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;
import dk.dtu.app.controller.BoardLogic.MyPane;

public class Tower1 extends TowerLogik {
    private double timeSinceFired;
    private double shootspeed = 2; // Attackspeed in seconds
    private int radius = 100;

    public Tower1(double x, double y, Space space, int me, MyPane board) {
        super(x, y, space, me, board);
        timeSinceFired = 0;
        System.out.println("Creates a tower");
    }

    public void tryToShoot(double time) {
        timeSinceFired += time;

        if (timeSinceFired > shootspeed) {
            //System.out.println("can shoot");
            super.shoot(radius, board);
            timeSinceFired = 0;
        }

    }
}
