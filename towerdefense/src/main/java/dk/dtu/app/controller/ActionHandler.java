package dk.dtu.app.controller;

import dk.dtu.app.controller.Action.ActionType;

public class ActionHandler {

    public static void selectAction(Object[] actioninfo, MyButton[][] board) {
        System.out.println("Selecting action: " + actioninfo[2]);
        int x = (int) actioninfo[0];
        int y = (int) actioninfo[1];
        Action.ActionType action = (ActionType) actioninfo[2];

        switch (action) {
            case NONE:
                break;
            case TOWER1:
                Tower.placeTower(x, y, board, action);
                break;
            case TOWER2:
                Tower.placeTower(x, y, board, action);
                break;
            case TOWER3:
                Tower.placeTower(x, y, board, action);
                break;
            case TOWER4:
                Tower.placeTower(x, y, board, action);
                break;
            case TOWER5:
                Tower.placeTower(x, y, board, action);
                break;
            case ENEMY1:
                break;
            case ENEMY2:
                break;
            case ENEMY3:
                break;
            case ENEMY4:
                break;
            case ENEMY5:
                break;
        }
    }
}
