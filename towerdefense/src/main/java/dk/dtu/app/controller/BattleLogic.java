package dk.dtu.app.controller;

import java.util.ArrayList;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.app.view.MenuGUI.Menu;
import dk.dtu.backend.PlayerConnection;
import dk.dtu.backend.PlayerInfo;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    private static String callSign;
    private double timeSinceEnemySpawn;
    private double spawnRate = 3.0;
    private boolean firstLoop = true;
    private boolean firstEnemySpawned = false;
    public static PlayerInfo myInfo;
    public static PlayerInfo opponentInfo;
    private boolean gameHasEnded = false;
    private static Object[] obj;
    int numOfEnemiesCreated;

    GameState gameState;

    public BattleLogic(Space space, String callSign, PlayerInfo mInfo, PlayerInfo oppInfo) {
        this.callSign = callSign;
        this.space = space;
        myInfo = mInfo;
        opponentInfo = oppInfo;
        numOfEnemiesCreated = 0;
        gameState = GameState.START;
    }

    @Override
    public void run() {
        System.out.println(callSign + ": Battle logic threads: run() has started");
        while (!gameHasEnded) {
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
                    setInitialEnemySpawnTime(10); // seconds

                    // Player lost results in gamestate ends and stop.
                    checkForPlayerLost();

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
                        spawnRate = spawnRate * 0.98;
                        Platform.runLater(() -> {
                            MultiplayerBoard.startSpawnEnemy();
                        });
                        numOfEnemiesCreated += 2;
                        timeSinceEnemySpawn = 0.0;
                    }
                    // Add a delay to avoid high CPU usage

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                case END: {
                    gameHasEnded = true;
                    System.out.println("Battle logic: switch(End)");
                    System.out.println((String) obj[0] + " has lost. Gamestate END");
                    showGameOver();
                    break;
                }
                default:
                    System.out.println("An error has occrured in gameState");
                    break;
            }
        }
    }

    private static void showGameOver() {
        Platform.runLater(() -> {
            Alert hostDialog = new Alert(AlertType.INFORMATION);
            hostDialog.setTitle("Game Over");
            hostDialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
            hostDialog.setContentText((String) obj[0] + " has lost the game");
            hostDialog.showAndWait();
           
        });
    }

    private void checkForPlayerLost() {
        try {
            obj = space.getp(new FormalField(String.class), new ActualField("lost"));
            if (obj != null) {
                System.out.println((String) obj[0] + " has lost. Gamestate END");
                gameState = GameState.END;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setInitialEnemySpawnTime(long time) {
        if (!firstEnemySpawned) {
            try {
                int countdownTime = (int) time; // Countdown time in seconds
                for (int i = countdownTime; i >= 0; i--) {
                    if (i == 0) {
                        Platform.runLater(() -> MultiplayerBoard.countdownLabel.setText(""));
                    } else {
                        // Update the countdown label (must be done in the JavaFX thread
                        int finalI = i;
                        Platform.runLater(() -> MultiplayerBoard.countdownLabel.setText("" + finalI));
                        Thread.sleep(1000);
                    }
                }
                System.out.println("Countdown finished, starting next action...");
                firstEnemySpawned = true; // Set the flag to true after spawning the first enemy

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
