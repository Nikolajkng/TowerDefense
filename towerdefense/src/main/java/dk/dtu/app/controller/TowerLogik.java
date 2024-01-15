package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import java.awt.geom.Point2D;
import java.util.List;

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
            List<Object[]> obj = space.queryAll(new FormalField(Integer.class), new ActualField("Coordinates"),
                    new FormalField(Double.class), new FormalField(Double.class));
            if (obj != null) {
                for (int i = 0; i < obj.size(); i++) {
                    System.out.println("saw some coordinates from " + (Integer) obj.get(i)[0]);
                    double distance = Point2D.distance(coordinateX, coordinateY, (Double) obj.get(i)[2], (Double) obj.get(i)[3]);
                    System.out.println("Distance to target " + distance);
                    if (distance <= radius) {
                        System.out.println("tower shoots at " + (Integer) obj.get(1)[0]);
                        space.put((Integer) obj.get(1)[0], "Damage", damage);
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
