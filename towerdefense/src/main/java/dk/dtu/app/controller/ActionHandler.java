package dk.dtu.app.controller;

import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.controller.BoardLogic.MyPane;

public class ActionHandler {
    private static int towerCounter = 0;

    public static void selectAction(Object[] actioninfo, MyPane board) {
        System.out.println("Selecting action: " + actioninfo[2]);
        int x = (int) actioninfo[0];
        int y = (int) actioninfo[1];
        Action.ActionType action = (ActionType) actioninfo[2];

        switch (action) {
            case NONE:
                break;
            case TOWER1:
                Tower.placeTower(x, y, board, action, towerCounter);
                towerCounter++;
                break;
            case TOWER2:
                Tower.placeTower(x, y, board, action, towerCounter);
                towerCounter++;
                break;
            case TOWER3:
                Tower.placeTower(x, y, board, action, towerCounter);
                towerCounter++;
                break;
        }
    }
}
