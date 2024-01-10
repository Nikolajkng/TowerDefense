package dk.dtu.app.view.GameBoardsGUI;

import org.jspace.SequentialSpace;
import org.jspace.Space;

import dk.dtu.app.controller.*;
import dk.dtu.backend.LocalAddressScript;
import dk.dtu.backend.PlayerInfoExchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class Board {
    public static String action = "";
    protected SequentialSpace space;
    // Creating the player boards

    public Board(SequentialSpace space) {
        System.out.println("konstruere et board");
        this.space = space;
        new Thread(new EnemyPlacement(this.space)).start();;
    }

    public MyButton[][] createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY,
            int value) {
        MyButton[][] board = new MyButton[1400][900];
        int x;
        int y;

        int[] pathX = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10,
                10, 10, 10, 10, 11, 12, 13 };

        int[] pathY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8,
                7, 6, 5, 4, 4, 4, 4, 4, 4 };

        // Create the gameboard
        for (x = 0; x < numOfCellsX; x++) {
            for (y = 0; y < numOfCellsY; y++) {
                MyButton cell = new MyButton(value);
                cell.setPrefSize(cellSize, cellSize);

                // Creates a path where enemies will follow
                boolean isPath = false;
                for (int i = 0; i < pathX.length; i++) {
                    if (x == pathX[i] && y == pathY[i]) { // checks the coordinate of the path
                        isPath = true;
                        break;
                    }
                }
                if (isPath) {
                    cell.setValue(-1);
                    cell.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/sand_tile.png');"
                            + "-fx-background-repeat: repeat;"
                            + "-fx-background-size: cover;");
                } else {
                    cell.setStyle("-fx-background-image: url('/dk/dtu/app/view/Images/grass_tile_3.png');"
                            + "-fx-background-repeat: repeat;"
                            + "-fx-background-size: cover;");
                }

                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;

                // Add action each button on board
                final int finalX = x;
                final int finalY = y;
                board[x][y].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        // Check if player has clicked on a legal cell
                        if (board[finalX][finalY].getValue() != -1) {
                            // Action when a cell is clicked on
                            clickInfo(board, finalX, finalY);
                            if (true) {
                                Tower.placeTower(finalX, finalY, board, action);

                                // Sender modstander info om at der er blevet placeret et tårn
                                try {
                                    space.put("placeTower", LocalAddressScript.getLocalAddress(), finalX, finalY);
                                } catch (InterruptedException e1) {
                                }
                                // //PlayerInfoExchange.sendAction(finalX, finalY, action);
                            }
                        } else {
                            System.out.println("Clicked on illegal tile");
                        }

                    }
                });
            }

        }
        return board;
    }

    private static void clickInfo(MyButton[][] board, int x, int y) {
        System.out.println("Button clicked");
        System.out.println("X: " + x + " Y: " + y);
        System.out.println("Value: " + board[x][y].getValue());
    }
}
