package dk.dtu.app.controller;

import java.util.ArrayList;

import org.jspace.ActualField;
import org.jspace.Space;

import dk.dtu.app.controller.Enemy.EnemyMovement;
import dk.dtu.app.controller.Waves.Wave;
import dk.dtu.Enemies.Enemy_Bunny;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.BoardLogic.MyPane;

enum GameState {
    START,
    ONGOING,
    END
}

public class BattleLogic implements Runnable {
    private MyPane board;
    private Space space;
    private long time;
    private double elapsedTime;
    public static ArrayList<EnemyMovement> enemies;
    public static ArrayList<TowerLogik> towers;
    private int numOfEnemiesCreated;
    GameState gameState;

    public BattleLogic(Space space, MyPane myPane) {
        numOfEnemiesCreated = 0;
        this.space = space;
        this.board = myPane;
        gameState = GameState.START;
        System.out.println("Started battle logik");
    }

    public void waves(int[] enemies) {
        // new Thread( new EnemyMach(space, enemies, startingCoordinateX,
        // startingCoordinateY)).start();
    }

    @Override
    public void run() {
        while (true) {
            switch (gameState) {
                case START: {
                    time = System.currentTimeMillis();

                    enemies = new ArrayList<>();
                    towers = new ArrayList<>();

                    gameState = GameState.ONGOING;
                }
                case ONGOING: {
                    try {
                        Object[] obj = space.query(new ActualField("Player lost"));
                        if (obj != null) {
                            gameState = GameState.END;
                        }
                        long currentTime = System.currentTimeMillis();
                        elapsedTime = (currentTime - time) / 1000.0;
                        time = currentTime;

                        for (TowerLogik t: towers) {
                            t.tryToShoot(elapsedTime);
                        }

                        for (EnemyMovement e: enemies) {
                            // Make the enemies move
                        }

                        this.enemyWave();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                case END: {

                }
                default:
                    System.out.println("An error has occrured in gameState");
            }
        }
    }

    private void enemyWave() {
        if (time % 500 == 0) {
            numOfEnemiesCreated ++;
            enemies.add(new Enemy_Bunny(0, BoardController.boardSizeY, space, numOfEnemiesCreated, board));
            Wave.spawnEnemy(board);
        }
    }

}
