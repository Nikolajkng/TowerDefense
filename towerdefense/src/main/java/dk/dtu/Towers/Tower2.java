package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower2 extends TowerLogik {
    private int damage = 15;
    private int shootspeed = 5;
    private int radius = 30;

    public Tower2(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void start() {
        System.out.println("tower 2 logik created");
        while (true) {
            super.shoot(radius, damage);
            try {
                Thread.sleep(1500/shootspeed);
            } catch (InterruptedException e) {}
        }
    }

}
