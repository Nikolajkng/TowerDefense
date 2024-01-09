package dk.dtu;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Enemy_movement {
    private int coordinateX;
    private int coordinateY;
    protected Space space;
    protected int me;

    public Enemy_movement(int x, int y, Space space, int me) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.space = space;
        this.me = me;
    }

    public void walkDown() {
        try {
            Object[] obj = space.get(new ActualField(me), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                coordinateX = (Integer) obj[2];
                coordinateY = (Integer) obj[3] + 1;
                space.put(me, "Coordinates", coordinateX, coordinateY);
            }
        } catch (InterruptedException e) {
        }
    }

    public void walkUp() {
        try {
            Object[] obj = space.get(new ActualField(me), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                coordinateX = (Integer) obj[2];
                coordinateY = (Integer) obj[3] - 1;
                space.put(me, "Coordinates", coordinateX, coordinateY);
            }
        } catch (InterruptedException e) {
        }
    }

    public void walkLeft() {
        try {
            Object[] obj = space.get(new ActualField(me), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                coordinateX = (Integer) obj[2] - 1;
                coordinateY = (Integer) obj[3];
                space.put(me, "Coordinates", coordinateX, coordinateY);
            }
        } catch (InterruptedException e) {
        }
    }

    public void walkRight() {
        try {
            Object[] obj = space.get(new ActualField(me), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                coordinateX = (Integer) obj[2] + 1;
                coordinateY = (Integer) obj[3];
                space.put(me, "Coordinates", coordinateX, coordinateY);
            }
        } catch (InterruptedException e) {
        }
    }
}