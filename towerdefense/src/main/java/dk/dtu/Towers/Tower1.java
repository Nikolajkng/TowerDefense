package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;
import dk.dtu.app.controller.BoardLogic.MyPane;

public class Tower1 extends TowerLogik {
    private double timeSinceFired;
    private int damage = 1;
    private double shootspeed = 1; // Attackspeed in seconds
    private int radius = 200;

    public Tower1(double x, double y, Space space, int me, MyPane board) {
        super(x, y, space, me, board);
        timeSinceFired = 0;
        System.out.println("Creates a tower");
    }

    public void tryToShoot(double time) {
        timeSinceFired += time;

        if (timeSinceFired > shootspeed) {
            //System.out.println("can shoot");
            super.shoot(radius, damage, board);
            timeSinceFired = 0;
        }

    }
}
