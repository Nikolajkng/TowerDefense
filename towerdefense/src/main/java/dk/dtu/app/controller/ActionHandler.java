package dk.dtu.app.controller;

import dk.dtu.backend.*;

public class ActionHandler implements Runnable {
    public static Object[] actionInfo;

    public ActionHandler(Object[] actionInfo) {
        this.actionInfo = actionInfo;
    }

    @Override
    public void run() {

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
