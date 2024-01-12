package dk.dtu.app.controller.BoardLogic;

import java.util.Map;

import dk.dtu.app.controller.*;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.backend.ActionSender;
import dk.dtu.backend.PlayerConnection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardController {
    public static Action.ActionType type = ActionType.NONE;
    public static String callsign = PlayerConnection.callsign;
    public static int pathValue = -2;
    public static int illegalValue = -1;
    
    // public static Map<String, Integer> coordinatesValues = new HashMap<>();

    // Creating the player boards
    public static MyPane createPlayerBoardValues(MyPane board, int boardSizeX, int boardSizeY, int value) {
        board.setPrefSize(boardSizeX, boardSizeY);
        board.setStyle("-fx-background-color: #FFFFFF");

        // Assign each pixel a value from paramter
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                String pixelCoordinate = String.format("%d,%d", x, y);
                board.getHashMap().put(pixelCoordinate, value);
            }
        }


        boolean[][] pathArray = new boolean[boardSizeX][boardSizeY];
        int pathLength = Math.min(boardSizeX, boardSizeY); // Assuming square board
        int pathThickness = 20;
        for (int row = 0; row < pathLength; row++) {
            int x = row;
            int y = boardSizeY / 2; // Adjust the y-coordinate as needed
        
            // Set the path pixels in the boolean array
            for (int col = 0; col < pathThickness; col++) {
                int adjustedY = y - pathThickness / 2 + col;
                if (adjustedY >= 0 && adjustedY < boardSizeY) {
                    pathArray[x][adjustedY] = true;
                }
            }
        }
        
        // Set the path pixels on MyPane
        for (int x = 0; x < boardSizeX; x++) {
            for (int y = 0; y < boardSizeY; y++) {
                if (pathArray[x][y]) {
                    String pixelCoordinate = String.format("%d,%d", x, y);
                    board.getHashMap().put(pixelCoordinate, pathValue);
        
                    // Add a rectangle to visually represent the path
                    Rectangle pathRectangle = new Rectangle(x, y, 1, 1);
                    pathRectangle.setFill(Color.RED); // Adjust the color as needed
                    board.getChildren().add(pathRectangle);
                }
            }
        }
        // Set onclick for Panes
        board.setOnMouseClicked(event -> {
            int clickX = (int) event.getX();
            int clickY = (int) event.getY();
            getClickInfo(clickX, clickY, board);
            ActionSender.sendAction(clickX, clickY, type, callsign);
        });

        return board;

    }

    private static void getClickInfo(int x, int y, MyPane board) {
        String pixelCoordinate = String.format("%d,%d", x, y);
        System.out.println("Clicked at coordinates: (" + x + ", " + y + ")");
        System.out.println("Value at coordinates (" + x + ", " + y + "): " + board.getHashMap().get(pixelCoordinate));
    }

}
