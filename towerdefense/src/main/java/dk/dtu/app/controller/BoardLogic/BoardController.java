package dk.dtu.app.controller.BoardLogic;

import dk.dtu.app.controller.*;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.backend.PlayerConnection;
import dk.dtu.backend.ActionSender;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class BoardController {

    // Global fields
    public static Action.ActionType type = ActionType.NONE;
    public static String callsign = PlayerConnection.callsign;
    public static int pathValue = -2;
    public static int illegalValue = -1;

    // Local fields
    private static boolean[][] pathArray;
    // private static boolean[][] pathArray2;
    // private static boolean[][] pathArray3;
    // private static boolean[][] pathArray4;
    // private static boolean[][] pathArray5;
    // private static boolean[][] pathArray6;
    // private static boolean[][] pathArray7;

    // Creating the player boards
    public static MyPane createPlayerBoard(MyPane board, int value) {

        Screen primaryScreen = Screen.getPrimary();

        // Get the visual bounds of the primary screen
        Rectangle2D bounds = primaryScreen.getVisualBounds();

        // Print the screen resolution
        System.out.println("Screen Resolution: " + bounds.getWidth() + "x" + bounds.getHeight());

        int boardSizeX = MultiplayerBoard.sizeX / 3;
        int boardSizeY = MultiplayerBoard.sizeY * 2 / 3;
        board.setPrefSize(boardSizeX, boardSizeY);
        board.setStyle("-fx-background-color: #FFFFFF");

        // Assign each pixel a value from paramter
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                String pixelCoordinate = String.format("%d,%d", x, y);
                board.getHashMap().put(pixelCoordinate, value);
            }
        }

        constructPath2(boardSizeX, boardSizeY,board);

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

    private static void getClickInfo(int x, int y, MyPane board) {
        String pixelCoordinate = String.format("%d,%d", x, y);
        System.out.println("Clicked at coordinates: (" + x + ", " + y + ")");
        System.out.println("Value at coordinates (" + x + ", " + y + "): " + board.getHashMap().get(pixelCoordinate));
    }

  /*  private static void constructPath(int boardSizeX, int boardSizeY, MyPane board) {

        int interval1 = boardSizeX / 6; // Right
        int interval2 = boardSizeY / 2 + boardSizeY / 3; // down
        int interval3 = interval1 + boardSizeX / 3; // Right
        int interval4 = interval2 - boardSizeY * 2 / 3; // Up
        int interval5 = interval3 + boardSizeX / 3; // Right
        int interval6 = interval4 + boardSizeY / 3; // Down
        int interval7 = interval5 + boardSizeX / 6; // Right

        int pathThickness = 50;

        // Path 1: Start Right
        pathArray1 = new boolean[boardSizeX][boardSizeY];
        for (int row = 0; row < interval1; row++) {
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
        for (int row = interval1; row < interval1 + pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 0; col < interval2 * 2; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray2[x][adjustedY] = true;
                }
            }
        }

        // Path 3: Right
        pathArray3 = new boolean[boardSizeX][boardSizeY];
        for (int row = interval1; row < interval2; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = interval2; col < interval2 + pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray3[x][adjustedY] = true;
                }
            }
        }

        // Path 4: Up
        pathArray4 = new boolean[boardSizeX][boardSizeY];
        for (int row = interval2; row < interval2 + pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -interval2; col < interval2; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray4[x][adjustedY] = true;
                }
            }
        }

        // Path 5: Right
        pathArray5 = new boolean[boardSizeX][boardSizeY];
        for (int row = interval2; row < interval3; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -interval2; col < -interval2 + pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray5[x][adjustedY] = true;
                }
            }
        }

        // Path 6: Down
        pathArray6 = new boolean[boardSizeX][boardSizeY];
        for (int row = interval3; row < interval3 + +pathThickness; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = -interval2; col < 20; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray6[x][adjustedY] = true;
                }
            }
        }

        // Path 7: End Right
        pathArray7 = new boolean[boardSizeX][boardSizeY];
        for (int row = interval4; row < interval5; row++) {
            int x = row;
            int y = boardSizeY / 2;
            for (int col = 0; col < pathThickness; col++) {
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
        
    } */

    private static void constructPath2(int boardSizeX, int boardSizeY, MyPane board) {
        int pathThickness = 50;
        int interval0 = boardSizeY / 2;
        int interval1 = boardSizeX / 6; // Right
        int interval2 = interval0 + boardSizeY / 4; // down
        int interval3 = interval1 + boardSizeX / 3; // Right
        int interval4 = interval2 - boardSizeY * 2 / 3; // Up
        int interval5 = interval3 + boardSizeX / 3; // Right
        int interval6 = interval4 + boardSizeY / 3; // Down
        int interval7 = interval5 + boardSizeX / 6; // Right

        // Path 1: Start Right
        for (int row = 0; row < interval1; row++) {
            for (int col = interval0 - pathThickness / 2; col < interval0 + pathThickness / 2; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }

        // down
        for (int row = interval1 - pathThickness / 2; row < interval1 + pathThickness / 2; row++) {
            for (int col = interval0 - pathThickness / 2; col < interval2; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }

        // right
        for (int row = interval1 - pathThickness / 2; row < interval3; row++) {
            for (int col = interval2 - pathThickness / 2; col < interval2 + pathThickness / 2; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }

        // up
        for (int row = interval3 - pathThickness / 2; row < interval3 + pathThickness / 2; row++) {
            for (int col = interval2 + pathThickness / 2 - 1; col > interval4; col--) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }

        // right
        for (int row = interval3 - pathThickness / 2; row < interval5; row++) {
            for (int col = interval4 - pathThickness / 2; col < interval4 + pathThickness / 2; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }

        // down
        for (int row = interval5 - pathThickness / 2; row < interval5 + pathThickness / 2; row++) {
            for (int col = interval4 - pathThickness / 2; col < interval6; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }


        // right
        for (int row = interval5 - pathThickness / 2; row < interval7 + 2; row++) {
            for (int col = interval6 - pathThickness / 2; col < interval6 + pathThickness / 2; col++) {
                if (col >= 0 && col < boardSizeY && row >= 0 && row < boardSizeX) {
                    String pixelCoordinate = String.format("%d,%d", row, col);
                    board.getHashMap().put(pixelCoordinate, pathValue);

                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(row, col, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }
    }
}