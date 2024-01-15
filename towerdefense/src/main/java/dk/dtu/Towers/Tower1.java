package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower1 extends TowerLogik{
    private int timeSinceFired;
    private int damage = 30;
    private double shootspeed = 1; // Attackspeed in seconds
    private int radius = 30;

    public Tower1(int x, int y, Space space, int me) {
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
