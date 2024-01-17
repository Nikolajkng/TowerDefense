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
    int numOfEnemiesCreated;
    private double timeSinceEnemySpawn;
    private double spawnRate = 2.0;
    private boolean firstLoop = true;
    private boolean firstEnemySpawned = false;
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
                    // Start the wave only when both players are ready
                    synchronizePlayers();
                    // ... other class members ...
                    if (!firstEnemySpawned) {
                        try {
                            int countdownTime = 7; // Countdown time in seconds
                            for (int i = countdownTime; i >= 0; i--) {
                                if(i == 0){
                                    Platform.runLater(() -> MultiplayerBoard.countdownLabel.setText(""));
                                } else {
                                    // Update the countdown label (must be done in the JavaFX thread
                                int finalI = i;
                                Platform.runLater(() -> MultiplayerBoard.countdownLabel.setText("" + finalI));
                                Thread.sleep(1000);
                                }
                            }
                            System.out.println("Countdown finished, starting next action...");
                    
                            // Next action here, e.g., spawn the first enemy
                            // spawnFirstEnemy(); // Replace this with your actual spawning method
                            firstEnemySpawned = true; // Set the flag to true after spawning the first enemy
                    
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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
                        Thread.sleep(200);
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

    private void synchronizePlayers() {
        if (callSign.equals("Host")) {
            try {
                // System.out.println("Host: ready to start");
                space.put("Host", "ready", "startONGOING");
                space.get(new ActualField("Client"), new ActualField("ready"),
                        new ActualField("startONGOING"));
                if (firstLoop) {
                    time = System.currentTimeMillis();
                    firstLoop = false;
                }
                // System.out.println("Host: got client");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (callSign.equals("Client")) {
            try {
                // System.out.println("Client: ready to start");
                space.put("Client", "ready", "startONGOING");
                space.get(new ActualField("Host"), new ActualField("ready"),
                        new ActualField("startONGOING"));
                if (firstLoop) {
                    time = System.currentTimeMillis();
                    firstLoop = false;
                }
                // System.out.println("Client: got host");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: callSign is not Host or Client");
        }
    }

}
