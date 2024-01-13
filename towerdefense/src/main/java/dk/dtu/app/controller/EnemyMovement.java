package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

public class EnemyMovement {
    protected int coordinateX;
    protected int coordinateY;
    private int endCoordinatesX = 13;
    private int endCoordinatesY = 4;
    protected Space space;
    protected int me;
    int endCoordinateX;
    int endCoordinateY;


    public EnemyMovement(int x, int y, Space space, int me) {
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
        boolean directionchosen = false;



        // Border control
        if (coordinateX - 1 < 0) {
            leftOK = false;
        }
        if (coordinateX + 1 > 13) {
            rightOK = false;
        }
        if (coordinateY - 1 < 0) {
            upOK = false;
        }
        if (coordinateY + 1 > 9) {
            downOK = false;
        }
        if (downOK && !directionchosen) {
            if (board[coordinateX][coordinateY + 1] == pathTile) {
                walkDown();
                board[coordinateX][coordinateY] = walkedTile;
                System.out.println("path chosen: Down");
                directionchosen = true;
            }
        }

        if (upOK && !directionchosen) {
            if (board[coordinateX][coordinateY - 1] == pathTile) {
                walkUp();
                board[coordinateX][coordinateY] = walkedTile;
                System.out.println("path chosen: Up");
                directionchosen = true;
            }
        }

        if (leftOK && !directionchosen) {
            if (board[coordinateX - 1][coordinateY] == pathTile) {
                walkLeft();
                board[coordinateX][coordinateY] = walkedTile;
                System.out.println("path chosen: Left");
                directionchosen = true;
            }
        }
        if (coordinateX == endCoordinateX && coordinateY == endCoordinateY){
            space.put("finish");
        }

        if (rightOK && !directionchosen) {
            if (board[coordinateX + 1][coordinateY] == pathTile) {
                walkRight();
                board[coordinateX][coordinateY] = walkedTile;
                System.out.println("path chosen: Right");
                directionchosen = true;
            }
        } 

        if (!directionchosen) {
            throw new Exception("Rabbit can't find a path");
        }

        if (coordinateX == endCoordinatesX && coordinateY == endCoordinatesY){
            // code til at miste liv

            System.out.println(me + " reached the end");
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
