package dk.dtu.app.controller;

import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;

public class ActionHandler {
    public static Object[] actionInfo;
    public static MyButton[][] board;

    public ActionHandler(Object[] actionInfo) {
        ActionHandler.actionInfo = actionInfo;
        ActionHandler.board = MultiplayerBoard.rightBoard;
    }

    public static void selectAction(Object[] actioninfo) {
        int x = (int) actionInfo[0];
        int y = (int) actionInfo[1];
        switch ((String) actionInfo[2]) {
            case "tower1":
                board[x][y].setText("X");
                board[x][y].setValue(-1);
                break;
            case "tower2":
                board[x][y].setText("0");
                board[x][y].setValue(-1);
                break;
            case "tower3":
                board[x][y].setText("±");
                board[x][y].setValue(-1);
                break;
        }

    }
}
