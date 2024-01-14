package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower3 extends TowerLogik {
    private int timeSinceFired;
    private int damage = 100;
    private double shootspeed = 2; // Attackspeed in seconds
    private int radius = 20;

    public Tower3(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void tryToShoot(double time) {
        timeSinceFired += time;

        if (timeSinceFired < shootspeed) {
            return;
        }
        super.shoot(radius, damage);    
    }

}
