package dk.dtu.Enemies;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.controller.Enemy_movement;
import dk.dtu.app.controller.MyButton;

public class Enemy_Bunny extends Enemy_movement implements Runnable {

    private int speed = 5;
    private int hp = 100;

    public Enemy_Bunny(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void takeDamage(int damage) {
        try {
            Object[] obj = super.space.get(new ActualField(super.me), new ActualField("Damage"),
                    new FormalField(Integer.class));
            if (obj != null) {
                hp -= (Integer) obj[2];
                if (hp <= 0) {
                    super.space.put(me, "Terminate");
                }
            }
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        int numOfCellsX = 14;
        int numOfCellsY = 10;
        int[][] board = new int[numOfCellsX][numOfCellsY];
        try {
            Object[] obj = space.query(new ActualField("Board"), new FormalField(MyButton[][].class));
            if (obj != null) {
                for (int i = 0; i < numOfCellsX; i++) {
                    for (int j = 0; j < numOfCellsY; j++) {
                        board[i][j] = ((MyButton[][]) obj[1])[i][j].getValue();
                    }
                }
            }
        } catch (InterruptedException e) {
        }

        while (true) {
            try {
                Thread.sleep(2000 / speed);
                super.choosePath(board);
            } catch (Exception e) {
            }

        }
    }
}
