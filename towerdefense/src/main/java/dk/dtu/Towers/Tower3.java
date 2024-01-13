package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower3 extends TowerLogik {
    private int damage = 50;
    private int shootspeed = 1;
    private int radius = 15;

    public Tower3(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void start() {
        System.out.println("tower 3 logik created");
        while (true) {
            super.shoot(radius, damage);
            try {
                Thread.sleep(1500/shootspeed);
            } catch (InterruptedException e) {}
        }
    }

}
