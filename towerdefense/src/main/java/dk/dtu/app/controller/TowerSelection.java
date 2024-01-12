package dk.dtu.app.controller;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower_HunterGUI;
import dk.dtu.app.view.Figures.Tower_KillerPlant;
import dk.dtu.app.view.GameBoardsGUI.*;

public class TowerSelection {
    public static void selectTower(){
        // Buttons funtionality (To be moved to controller folder later)
        Tower1GUI.plant.setOnAction(e -> {
            System.out.println("selected tower1");
            BoardController.type = ActionType.TOWER1;
        });
       Tower_HunterGUI.hunter.setOnAction(e -> {
            BoardController.type = ActionType.TOWER2;
            System.out.println("selected tower2");
        });
        Tower_KillerPlant.killerPlant.setOnAction(e -> {
            BoardController.type = ActionType.TOWER3;
            System.out.println("selected tower3");
        });
    }
}
