package dk.dtu.app.controller;

import dk.dtu.backend.*;

public class ActionHandler {
    public static Object[] actionInfo;

    public ActionHandler(Object[] actionInfo) {
        ActionHandler.actionInfo = actionInfo;
    }

    public void selectAction(String actioninfo) {
        switch ((String) actionInfo[2]) {
            case "tower1":
                break;
            case "tower2":
                break;
            case "tower3":
                break;
        }

    }
}
