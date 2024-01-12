package dk.dtu.app.controller;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;

public class TowerSelection {
    public static void selectTower(){
        // Buttons funtionality (To be moved to controller folder later)
        Tower1GUI.tower1.setOnAction(e -> {
            System.out.println("selected tower1");
            BoardController.type = ActionType.TOWER1;
        });
       Tower2GUI.tower2.setOnAction(e -> {
            BoardController.type = ActionType.TOWER2;
            System.out.println("selected tower2");
        });
        Tower3GUI.tower3.setOnAction(e -> {
            BoardController.type = ActionType.TOWER3;
            System.out.println("selected tower3");
        });
    }
}
