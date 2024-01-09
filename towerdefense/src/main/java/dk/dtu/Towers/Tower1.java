package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.app.controller.TowerLogik;

public class Tower1 extends TowerLogik implements Runnable {
    private int damage = 30;
    private int shootspeed = 2;
    private int radius = 10;

    public Tower1(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    @Override
    public void run() {
        while (true) {
            super.shoot(radius, damage);
            try {
                Thread.sleep(1500/shootspeed);
            } catch (InterruptedException e) {}
        }
    }

}
