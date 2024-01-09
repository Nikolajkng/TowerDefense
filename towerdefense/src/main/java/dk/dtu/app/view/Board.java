package dk.dtu.app.view;

import dk.dtu.app.controller.*;
import dk.dtu.backend.PlayerInfoExchange;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class Board {
    public static String action = "";
    // Creating the player boards
    public static MyButton[][] createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY,
            int value) {
        MyButton[][] board = new MyButton[1400][900];
        int x = 0;
        int y = 0;

        // Create the gameboard
        for (x = 0; x < numOfCellsX; x++) {
            for (y = 0; y < numOfCellsY; y++) {
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(value);
                cell.setPrefSize(cellSize, cellSize);

                // Set the path to be a different color
                setPath(cell, x, y);

                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;
                final int finalX = x;
                final int finalY = y;
                board[x][y].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        clickInfo(board, finalX, finalY);
                        if(true){Tower.placeTower(finalX, finalY);}
                        PlayerInfoExchange.playerSendMessage(finalX, finalY, action);
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

    public static void setPath(MyButton cell, int x, int y) {

        int[] pathX = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10,
                10, 10, 10, 10, 11, 12, 13 };
                
        int[] pathY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8,
                7, 6, 5, 4, 4, 4, 4, 4, 4 };

        boolean isPath = false;
        for (int i = 0; i > pathX.length; i++) {
            if (x == pathX[i] && y == pathY[i]) {
                isPath = true;
                break;
            }
        }
        if (isPath) {
            cell.setStyle(
                    "-fx-background-color: #DEB887;"
                            + "-fx-border-color: black;"
                            + " -fx-border-width: 1;");
        } else {
            cell.setStyle(
                    "-fx-background-color: linear-gradient(to bottom, green, #33CC66);"
                            + "-fx-border-color: black;"
                            + " -fx-border-width: 1;");
        }

    }

}
