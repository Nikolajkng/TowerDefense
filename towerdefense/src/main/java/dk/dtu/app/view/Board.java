package dk.dtu.app.view;

import javafx.scene.layout.GridPane;

public class Board {
    public static int numOfCellsX;
    public static int numOfCellsY;
    public static MyButton[][] board;

    // Creating the player boards
    public static void createPlayerBoard(GridPane myBoard, int cellSize, int numOfCellsX, int numOfCellsY) {
        
        MyButton[][] board = new MyButton[1400][900];

        // path 1 
        int[] pathX = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10, 10, 10, 10, 10, 11, 12, 13};
        int[] pathY = {0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 7, 6, 5, 4, 4, 4, 4, 4, 4};

        for(int x = 0; x < numOfCellsX; x++){
            for(int y = 0; y < numOfCellsY; y++){
                // Create a new button which represents each cell on the board
                MyButton cell = new MyButton(0);
                cell.setPrefSize(cellSize, cellSize);

                // Background
				/*if (numOfCellsX % 2 == 0 && numOfCellsY % 2 == 0) {
					cell.setStyle("-fx-background-color: linear-gradient(to bottom, green, #33CC66);"
                    + "-fx-border-color: black;"
                    + " -fx-border-width: 1;"
                   ); // Button color 1
				} else {
					cell.setStyle("-fx-base: #A52A2A;"); // Button color 2
				}
				if (numOfCellsX % 2 != 0 && numOfCellsY % 2 != 0) {
					cell.setStyle("-fx-base: #A52A2A"); // Button color 1
				}*/

                boolean isPath = false;
                for (int i = 0; i < pathX.length; i++){
                    if ( x == pathX[i] && y == pathY[i]){ //checks the coordinate of the path 
                        isPath = true;
                        break;
                    }
                }

                if(isPath){
                    cell.setStyle("-fx-background-color: #DEB887;" // Path color
                    + "-fx-border-color: black;"
                    + " -fx-border-width: 1;");
                } else {
                    cell.setStyle("-fx-background-color: linear-gradient(to bottom, green, #33CC66);"
                    + "-fx-border-color: black;"
                    + " -fx-border-width: 1;");
                }

                // Add the button to the grid and a corresponding coordinate position
                myBoard.add(cell, x, y);
                board[x][y] = cell;
            }
        }
    }
}
