package dk.dtu.app.controller;

import java.util.ArrayList;

import org.jspace.ActualField;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.PlayerConnection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;
import dk.dtu.app.controller.BoardLogic.MyPane;

enum GameState {
    START,
    ONGOING,
    END
}

public class BattleLogic implements Runnable {
    public static ArrayList<TowerLogik> towers;
    private Space space;
    private long time;
    private double elapsedTime;
    private int numOfEnemiesCreated;
    private double timeSinceEnemySpawn;
    private double spawnRate = 2.0;
    GameState gameState;

    public BattleLogic(Space space) {
        numOfEnemiesCreated = 0;
        this.space = space;
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

                    synchronizeStart();
                    break;

                }
                case ONGOING: {
                    try {
                        Object[] obj = space.getp(new ActualField("Player lost"));
                        if (obj != null) {
                            gameState = GameState.END;
                        }
                        // Tracks time passed
                        long currentTime = System.currentTimeMillis();
                        elapsedTime = (currentTime - time) / 1000.0;
                        time = currentTime;

                        for (TowerLogik t : towers) {
                            t.tryToShoot(elapsedTime);
                        }
                        timeSinceEnemySpawn += elapsedTime;
                        // Spawns enemies in a time interval of "spawnRate" seconds:
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

    private void synchronizeStart() {
        if (PlayerConnection.callsign.equals("Client")) {
            try {
                System.out.println("ready to get client");
                Object[] obj = space.get(new ActualField("Client"), new ActualField("gameState"),
                        new ActualField("ONGOING"));
                System.out.println("got client?");
                if (obj != null) {
                    System.out.println("Client: gameState = ONGOING");
                    // Hvis Host er foran, gå ned
                    // Hvis Host er bagud, gå op
                    timeSinceEnemySpawn = -1;   // 1 - 1.5 | 2.17515 - 2.17525
                    gameState = GameState.ONGOING;
                    space.put("Host", "gameState", "ONGOING");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("ready to get host");
                Object[] obj = space.get(new ActualField("Host"), new ActualField("gameState"),
                        new ActualField("ONGOING"));
                System.out.println("got host?");
                if (obj != null) {
                    System.out.println("Host: gameState = ONGOING");
                    gameState = GameState.ONGOING;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
