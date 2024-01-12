package dk.dtu.app.controller.BoardLogic;

import java.util.Map;

import dk.dtu.app.controller.*;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.backend.ActionSender;
import dk.dtu.backend.PlayerConnection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardController {

    // Global fields
    public static Action.ActionType type = ActionType.NONE;
    public static String callsign = PlayerConnection.callsign;
    public static int pathValue = -2;
    public static int illegalValue = -1;

    // Local fields
    private static boolean[][] pathArray1;
    private static boolean[][] pathArray2;
    private static boolean[][] pathArray3;
    private static boolean[][] pathArray4;
    private static boolean[][] pathArray5;
    private static boolean[][] pathArray6;
    private static boolean[][] pathArray7;

    // Creating the player boards
    public static MyPane createPlayerBoardValues(MyPane board, int value) {
        int boardSizeX = 760;
        int boardSizeY = 800;
        board.setPrefSize(boardSizeX, boardSizeY);
        board.setStyle("-fx-background-color: #FFFFFF");

        // Assign each pixel a value from paramter
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                String pixelCoordinate = String.format("%d,%d", x, y);
                board.getHashMap().put(pixelCoordinate, value);
            }
        }

        constructPath(boardSizeX, boardSizeY, board);

        // Set onclick for Panes
        board.setOnMouseClicked(event -> {
            int clickX = (int) event.getX();
            int clickY = (int) event.getY();
            getClickInfo(clickX, clickY, board);
            if (board.getHashMap().get(String.format("%d,%d", clickX, clickY)) != -1
                    && board.getHashMap().get(String.format("%d,%d", clickX, clickY)) != -2) {
                Tower.placeTower(clickX, clickY, board, type);
                ActionSender.sendAction(clickX, clickY, type, callsign);
            } else {
                System.out.println("Illegal placement");
            }
        });

        return board;
    }

    private static void constructPath(int boardSizeX, int boardSizeY, MyPane board) {

        int pathThickness = 60;

        // Path 1: Start Right
        pathArray1 = new boolean[boardSizeX][boardSizeY];
        for (int row = 0; row < 160; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 0; col < pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray1[x][adjustedY] = true;
                }
            }
        }
        // Path 2: Down
        pathArray2 = new boolean[boardSizeX][boardSizeY];
        for (int row = 160; row < 160+pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 0; col < 320; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray2[x][adjustedY] = true;
                }
            }
        }
        // Path 3: Right
        pathArray3 = new boolean[boardSizeX][boardSizeY];
        for (int row = 160; row < 380; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 300; col < 300 + pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray3[x][adjustedY] = true;
                }
            }
        }

        // Path 4: Up
        pathArray4 = new boolean[boardSizeX][boardSizeY];
        for (int row = 320; row < 320 + pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -300; col < 320; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray4[x][adjustedY] = true;
                }
            }
        }

        // Path 5: Right
        pathArray5 = new boolean[boardSizeX][boardSizeY];
        for (int row = 320; row < 500; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -300; col < -300 + pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray5[x][adjustedY] = true;
                }
            }
        }

        // Path 6: Down
        pathArray6 = new boolean[boardSizeX][boardSizeY];
        for (int row = 500; row < 500 + + pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -300; col < 20; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray6[x][adjustedY] = true;
                }
            }
        }

        // Path 7: End Right
        pathArray7 = new boolean[boardSizeX][boardSizeY];
        for (int row = 500; row < 760; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 0 ; col < pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray7[x][adjustedY] = true;
                }
            }
        }

        // Build the path from the setup
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                if (pathArray1[x][y] || pathArray2[x][y] || pathArray3[x][y] || pathArray4[x][y] || pathArray5[x][y]
                        || pathArray6[x][y] || pathArray7[x][y]) {
                    String pixelCoordinate = String.format("%d,%d", x, y);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(x, y, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }
    }

    private static void getClickInfo(int x, int y, MyPane board) {
        String pixelCoordinate = String.format("%d,%d", x, y);
        System.out.println("Clicked at coordinates: (" + x + ", " + y + ")");
        System.out.println("Value at coordinates (" + x + ", " + y + "): " + board.getHashMap().get(pixelCoordinate));
    }

}
