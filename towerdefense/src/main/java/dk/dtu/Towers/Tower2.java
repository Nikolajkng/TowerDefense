package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower2 extends TowerLogik {
    private int timeSinceFired;
    private int damage = 15;
    private double shootspeed = 0.5; // Attackspeed in seconds
    private int radius = 45;

    public Tower2(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void tryToShoot(double time) {
        timeSinceFired += time;

        if (timeSinceFired < shootspeed) {
            return;
        }
        super.shoot(radius, damage);
        timeSinceFired = 0;       
    }

}
