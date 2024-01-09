package dk.dtu.Towers;

import org.jspace.Space;

import dk.dtu.TowerLogik;

public class Tower1 extends TowerLogik {
    private int damage;
    private int shootspeed;
    private int radius;

    public Tower1(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

}
