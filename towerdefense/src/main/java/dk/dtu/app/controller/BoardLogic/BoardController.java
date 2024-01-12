package dk.dtu.app.controller.BoardLogic;

import java.util.Map;

import dk.dtu.app.controller.*;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.backend.PlayerConnection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardController {
    public static Action.ActionType type = ActionType.NONE;
    public static String callsign = PlayerConnection.callsign;
    public static int pathValue = -2;
    public static int illegalValue = -1;
    //public static Map<String, Integer> coordinatesValues = new HashMap<>();

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
        // Create a simple linear path with value -2
        int pathLength = Math.min(boardSizeX, boardSizeY); // Assuming square board
        for (int i = 0; i < pathLength; i++) {
            int x = i;
            int y = boardSizeY / 2; // Adjust the y-coordinate as needed
            String pixelCoordinate = String.format("%d,%d", x, y);
            board.getHashMap().put(pixelCoordinate, pathValue);
               
            // Add a rectangle to visually represent the path
            Rectangle pathRectangle = new Rectangle(x, y, 1, 1);
            pathRectangle.setFill(Color.RED); // Adjust the color as needed
            board.getChildren().add(pathRectangle);
        }
        
        // Set onclick for Panes
        board.setOnMouseClicked(event -> {
            int clickX = (int) event.getX();
            int clickY = (int) event.getY();
            getClickInfo(clickX, clickY, board);

        });

        return board;

    }

    private static void getClickInfo(int x, int y, MyPane board) {
        String pixelCoordinate = String.format("%d,%d", x, y);
        System.out.println("Clicked at coordinates: (" + x + ", " + y + ")");
        System.out.println("Value at coordinates (" + x + ", " + y + "): " + board.getHashMap().get(pixelCoordinate));
    }

}
