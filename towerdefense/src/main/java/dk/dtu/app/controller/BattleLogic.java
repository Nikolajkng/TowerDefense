package dk.dtu.app.controller;

import java.util.ArrayList;

import org.jspace.ActualField;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import javafx.application.Platform;

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
    private String callSign;
    private double timeSinceEnemySpawn;
    private double spawnRate = 3.0;
    private boolean firstLoop = true;
    private boolean firstSpawn = true;
    int numOfEnemiesCreated;
    GameState gameState;

    public BattleLogic(Space space, String callSign) {
        this.callSign = callSign;
        this.space = space;
        numOfEnemiesCreated = 0;
        gameState = GameState.START;
    }

    @Override
    public void run() {
        System.out.println(callSign + ": Battle logic threads: run() has started");
        while (true) {
            switch (gameState) {
                case START: {

                    towers = new ArrayList<>();
                    gameState = GameState.ONGOING;
                    break;

                }
                case ONGOING: {
                    // Start the game logic only when both players are ready
                    synchronizePlayers();

                    // Start the wave after 7 seconds
                    setInitialEnemySpawnTime(7); // seconds
                    

                    // Player lost results in gamestate ends and stop. 
                    try {
                        Object[] obj = space.getp(new ActualField("Player lost"));
                        if (obj != null) {
                            gameState = GameState.END;
                        }
                        // Tracks time passed
                        long currentTime = System.currentTimeMillis();
                        elapsedTime = (currentTime - time) / 1000.0;
                        time = currentTime;

                        // Iterate through tower options and apply logic on them all.
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
                            numOfEnemiesCreated += 2;
                            timeSinceEnemySpawn = 0.0;
                        }
                        // Add a delay to avoid high CPU usage
                        Thread.sleep(400);
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

    private void setInitialEnemySpawnTime(long time) {
        if(firstSpawn){
            try {
                Thread.sleep(time*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            firstSpawn = false;
        }
    }

    private void synchronizePlayers() {
        if (callSign.equals("Host")) {
            try {
                space.put("Host", "ready", "startONGOING");
                space.get(new ActualField("Client"), new ActualField("ready"),
                        new ActualField("startONGOING"));
                if (firstLoop) {
                    time = System.currentTimeMillis();
                    firstLoop = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (callSign.equals("Client")) {
            try {
                space.put("Client", "ready", "startONGOING");
                space.get(new ActualField("Host"), new ActualField("ready"),
                        new ActualField("startONGOING"));
                if (firstLoop) {
                    time = System.currentTimeMillis();
                    firstLoop = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: callSign is not Host or Client");
        }
    }

}
