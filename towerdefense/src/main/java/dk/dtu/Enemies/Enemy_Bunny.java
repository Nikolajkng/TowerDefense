package dk.dtu.Enemies;

import java.util.concurrent.CompletableFuture;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.controller.MyButton;
import dk.dtu.app.controller.Enemy.Enemy_movement;
import dk.dtu.app.view.Figures.Enemy1_BunnyGUI;

public class Enemy_Bunny extends Enemy_movement {

    private int speed = 1;
    private int hp = 100;
    private Enemy1_BunnyGUI gui;

    public Enemy_Bunny(int x, int y, Space space, int me) {
        super(x, y, space, me);
        gui = new Enemy1_BunnyGUI();
        //System.out.println("Created bunny");
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
        //System.out.println("trying to run bunny");
        int numOfCellsX = 14;
        int numOfCellsY = 10;
        Object[] obj = null;
        final Object[] objtest;
        int[][] board = new int[numOfCellsX][numOfCellsY];
        try {
            obj = space.query(new ActualField("MyBoard"), new FormalField(MyButton[][].class));
            if (obj != null) {
                for (int i = 0; i < numOfCellsX; i++) {
                    for (int j = 0; j < numOfCellsY; j++) {
                        board[i][j] = ((MyButton[][]) obj[1])[i][j].getValue();
                    }
                }
                System.out.println("har lavet et board");
            } 
        } catch (InterruptedException e) {
        }
        objtest = obj;

        while (true) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("trying to choose a path");
                        gui.removeBunny(((MyButton[][]) objtest[1])[coordinateX][coordinateY]);
                        super.choosePath(board);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("trying to place a bunny at x:" + coordinateX + " y:" + coordinateY);
                    gui.placeBunny(((MyButton[][]) objtest[1])[coordinateX][coordinateY]);
                });
                try {
                    future.wait(2000/speed);
                } catch (Exception e) {
                }

        //     if (obj != null) {
        //         System.out.println("placing bunny at (" + coordinateX + "," + coordinateY + ")");
        //         gui.placeBunny(((MyButton[][]) obj[1])[coordinateX][coordinateY]);
        //     try {
        //         Thread.sleep(2000 / speed);
        //         super.choosePath(board);
        //     } catch (Exception e) {
        //     }
        // }
        }
    }
}
