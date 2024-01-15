package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower1 extends TowerLogik {
    private double timeSinceFired;
    private int damage = 1;
    private double shootspeed = 1; // Attackspeed in seconds
    private int radius = 100;

    public Tower1(int x, int y, Space space, int me) {
        super(x, y, space, me);
        timeSinceFired = 0;
        System.out.println("Creates a tower");
    }

    public void tryToShoot(double time) {
        timeSinceFired += time;

        if (timeSinceFired > shootspeed) {
            System.out.println("can shoot");
            super.shoot(radius, damage);
            timeSinceFired = 0;
        }

    }
}
