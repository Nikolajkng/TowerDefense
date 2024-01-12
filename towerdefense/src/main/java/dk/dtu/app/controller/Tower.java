package dk.dtu.app.controller;

import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;

public class Tower {
    static final int illegalValue = -1;

    public static void placeTower(int x, int y, MyPane board, Action.ActionType type) {
        System.out.println("Placing tower at: (" + x + ", " + y + ") with action: " + type);
        String pixelCoordinate = String.format("%d,%d", x, y);
        if (type == ActionType.TOWER1) {
            Tower1GUI.placeTower1(x, y, board);
            // Change the coordinates value to -1
            if (board.getHashMap().containsKey(pixelCoordinate)) {
                board.getHashMap().remove(pixelCoordinate);
                board.getHashMap().put(pixelCoordinate, illegalValue);
                // Check if it really has been changed
                System.out.println(board.getHashMap().get(pixelCoordinate)); // Should be -1
            } else {
                System.out.println("Key not found in HashMap");
            }
        } else if (type == ActionType.TOWER2) {
            Tower2GUI.placeTower2(x, y, board);
            // Change the coordinates value to -1
            if (board.getHashMap().containsKey(pixelCoordinate)) {
                board.getHashMap().remove(pixelCoordinate);
                board.getHashMap().put(pixelCoordinate, illegalValue);
                // Check if it really has been changed
                System.out.println(board.getHashMap().get(pixelCoordinate)); // Should be -1

            } else {
                System.out.println("Key not found in HashMap");
            }
        } else if (type == ActionType.TOWER3) {
            Tower3GUI.placeTower3(x, y, board);
            // Change the coordinates value to -1
            if (board.getHashMap().containsKey(pixelCoordinate)) {
                board.getHashMap().remove(pixelCoordinate);
                board.getHashMap().put(pixelCoordinate, illegalValue);
                // Check if it really has been changed
                System.out.println(board.getHashMap().get(pixelCoordinate)); // Should be -1

            } else {
                System.out.println("Key not found in HashMap");
            }
        } else if (type == ActionType.TOWER4) {
            Tower1GUI.placeTower1(x, y, board);
            // Change the coordinates value to -1
            if (board.getHashMap().containsKey(pixelCoordinate)) {
                board.getHashMap().remove(pixelCoordinate);
                board.getHashMap().put(pixelCoordinate, illegalValue);
                // Check if it really has been changed
                System.out.println(board.getHashMap().get(pixelCoordinate)); // Should be -1

            } else {
                System.out.println("Key not found in HashMap");
            }
        } else if (type == ActionType.TOWER5) {
            Tower1GUI.placeTower1(x, y, board);
            // Change the coordinates value to -1
            if (board.getHashMap().containsKey(pixelCoordinate)) {
                board.getHashMap().remove(pixelCoordinate);
                board.getHashMap().put(pixelCoordinate, illegalValue);
                // Check if it really has been changed
                System.out.println(board.getHashMap().get(pixelCoordinate)); // Should be -1

            } else {
                System.out.println("Key not found in HashMap");
            }
        } else {
            System.out.println("No tower selected");
        }

    }
}