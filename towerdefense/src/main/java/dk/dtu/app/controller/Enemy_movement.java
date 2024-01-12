package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class Enemy_movement {
    protected int coordinateX;
    protected int coordinateY;
    protected Space space;
    protected int me;

    public Enemy_movement(int x, int y, Space space, int me) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.space = space;
        this.me = me;
    }

    public void choosePath(int[][] board) throws Exception {
        int pathTile = -2;
        int walkedTile = 2; // To prevent going back
        boolean leftOK = true;
        boolean rightOK = true;
        boolean upOK = true;
        boolean downOK = true;

        // Border control
        if (coordinateX - 1 < 0) {
            leftOK = false;
        }
        if (coordinateX + 1 > 14) {
            rightOK = false;
        }
        if (coordinateY - 1 < 0) {
            upOK = false;
        }
        if (coordinateY + 1 > 10) {
            downOK = false;
        }

        if (rightOK && board[coordinateX][coordinateY + 1] == pathTile) {
            walkDown();
            board[coordinateX][coordinateY + 1] = walkedTile;
            System.out.println("path chosen: Down");
        }

        else if (leftOK && board[coordinateX][coordinateY - 1] == pathTile) {
            walkUp();
            board[coordinateX][coordinateY - 1] = walkedTile;
            System.out.println("path chosen: Up");
        }

        else if (upOK && board[coordinateX - 1][coordinateY] == pathTile) {
            walkLeft();
            board[coordinateX - 1][coordinateY] = walkedTile;
            System.out.println("path chosen: Left");
        }

        else if (downOK && board[coordinateX + 1][coordinateY] == pathTile) {
            walkRight();
            board[coordinateX + 1][coordinateY] = walkedTile;
            System.out.println("path chosen: Right");
        }
        else {
            throw new Exception("Rabbit can't find a path");
        }
    }

    public void walkDown() {
        try {
            Object[] obj = space.get(new ActualField(me), new ActualField("Coordinates"),
                    new FormalField(Integer.class), new FormalField(Integer.class));
            if (obj != null) {
                coordinateX = (Integer) obj[2];
                coordinateY = (Integer) obj[3] + 1;
                space.put(me, "Coordinates", coordinateX, coordinateY);
                System.out.println("Bunny" + me + " new coordinates x:" + coordinateX + " y:" + coordinateY);
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
                System.out.println("Bunny" + me + " new coordinates x:" + coordinateX + " y:" + coordinateY);
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
                System.out.println("Bunny" + me + " new coordinates x:" + coordinateX + " y:" + coordinateY);
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
                System.out.println("Bunny" + me + " new coordinates x:" + coordinateX + " y:" + coordinateY);
            }
        } catch (InterruptedException e) {
        }
    }
}
