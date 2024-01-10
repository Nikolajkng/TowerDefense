package dk.dtu.app.controller;


public class ActionHandler {

    public static void selectAction(Object[] actioninfo, MyButton[][] board) {
        System.out.println("I am inside ActionHandler");
        int x = (int) actioninfo[0];
        int y = (int) actioninfo[1];
        String action = (String) actioninfo[2];

        switch (action) {
            case "tower1":
                Tower.placeTower(x, y, board, action);
                break;
            case "tower2":
                Tower.placeTower(x, y, board, action);
                break;
            case "tower3":
                Tower.placeTower(x, y, board, action);
                break;
        }

    }
}
