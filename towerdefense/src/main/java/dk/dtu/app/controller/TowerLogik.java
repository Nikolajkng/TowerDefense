package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import java.awt.geom.Point2D;

public class TowerLogik {
    private int coordinateX;
    private int coordinateY;
    protected Space space;
    protected int me;

    public TowerLogik(int x, int y, Space space, int me) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.space = space;
        this.me = me;
    }

    public void shoot(int radius, int damage) {
        try {
            Object[] obj = space.queryp(new FormalField(Integer.class), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                double distance = Point2D.distance(coordinateX, coordinateY, (Integer) obj[2], (Integer) obj[3]);
                if (distance <= radius) {
                    space.put((Integer) obj[0], "Damage", damage);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
