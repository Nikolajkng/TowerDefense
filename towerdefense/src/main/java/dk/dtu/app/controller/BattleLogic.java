package dk.dtu.app.controller;

import java.util.ArrayList;

import org.jspace.ActualField;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;
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
    public static ArrayList<TowerLogik> towers;
    private int numOfEnemiesCreated;
    private double timeSinceEnemySpawn;
    private double spawnRate = 2.0;
    GameState gameState;

    public BattleLogic(Space space, MyPane myPane) {
        numOfEnemiesCreated = 0;
        this.space = space;
        this.board = myPane;
        gameState = GameState.START;
        System.out.println("Started battle logik");
    }

    @Override
    public void run() {
        System.out.println("Battle logic: run()");
        while (true) {
            switch (gameState) {
                case START: {
                    System.out.println("Battle logic: switch(Start)");
                    time = System.currentTimeMillis();

                    towers = new ArrayList<>();

                    gameState = GameState.ONGOING;
                    break;
                }
                case ONGOING: {
                    try {
                        Object[] obj = space.getp(new ActualField("Player lost"));
                        if (obj != null) {
                            gameState = GameState.END;
                        }
                        long currentTime = System.currentTimeMillis();
                        elapsedTime = (currentTime - time) / 1000.0;
                        time = currentTime;

                        for (TowerLogik t : towers) {
                            t.tryToShoot(elapsedTime);
                        }

                        timeSinceEnemySpawn += elapsedTime;
                        if (timeSinceEnemySpawn > spawnRate) {
                            System.out.println("spawns enemy");
                            Platform.runLater(() -> {
                                MultiplayerBoard.startSpawnEnemy();
                            });
                            timeSinceEnemySpawn = 0.0;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                case END: {
                    System.out.println("Battle logic: switch(End)");
                    break;
                }
                default:
                    System.out.println("An error has occrured in gameState");
                    break;
            }
        }
    }

}
