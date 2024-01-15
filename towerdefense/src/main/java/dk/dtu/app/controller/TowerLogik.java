package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import java.awt.geom.Point2D;

abstract public class TowerLogik {
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

    abstract public void tryToShoot(double time);

    public void shoot(int radius, int damage) {
        try {
            Object[] obj = space.queryp(new FormalField(Integer.class), new ActualField("Coordinates"),
                    new FormalField(Double.class), new FormalField(Double.class));
            if (obj != null) {
                System.out.println("saw some coordinates from " + (Integer) obj[0]);
                double distance = Point2D.distance(coordinateX, coordinateY, (Double) obj[2], (Double) obj[3]);
                System.out.println("Distance to target " + distance);
                if (distance <= radius) {
                    System.out.println("tower shoots at " + (Integer) obj[0]);
                    space.put((Integer) obj[0], "Damage", damage);
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
