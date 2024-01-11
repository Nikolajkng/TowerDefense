package dk.dtu.Enemies;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.controller.Enemy_movement;
import dk.dtu.app.controller.MyButton;
import dk.dtu.app.view.Figures.Enemy1_BunnyGUI;

public class Enemy_Bunny extends Enemy_movement {

    private int speed = 5;
    private int hp = 100;
    private Enemy1_BunnyGUI gui;

    public Enemy_Bunny(int x, int y, Space space, int me) {
        super(x, y, space, me);
        gui = new Enemy1_BunnyGUI();
        System.out.println("Created bunny");
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

    public void run() {
        int numOfCellsX = 14;
        int numOfCellsY = 10;
        Object[] obj = null;
        int[][] board = new int[numOfCellsX][numOfCellsY];
        try {
            obj = space.query(new ActualField("Board"), new FormalField(MyButton[][].class));
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
            if (obj != null) {
                gui.placeBunny(((MyButton[][]) obj[1])[coordinateX][coordinateY]);
            try {
                Thread.sleep(2000 / speed);
                super.choosePath(board);
            } catch (Exception e) {
            }
        }
        }
    }
}
