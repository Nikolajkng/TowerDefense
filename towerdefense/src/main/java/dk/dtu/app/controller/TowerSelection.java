package dk.dtu.app.controller;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.Figures.Tower1GUI;
import dk.dtu.app.view.Figures.Tower2GUI;
import dk.dtu.app.view.Figures.Tower3GUI;
import dk.dtu.backend.PlayerInfo;

public class TowerSelection {
    public static void selectTower(PlayerInfo myInfo){
        // Buttons funtionality (To be moved to controller folder later)

        // disable buttons based on the amount of monet
        Tower1GUI.tower1.setDisable(myInfo.getMoney() < 50);
        Tower2GUI.tower2.setDisable(myInfo.getMoney() < 150);
        Tower3GUI.tower3.setDisable(myInfo.getMoney() < 200);

        Tower1GUI.tower1.setOnAction(e -> {
            System.out.println("selected tower1");
            BoardController.type = ActionType.TOWER1;
        });
       Tower2GUI.tower2.setOnAction(e -> {
            System.out.println("selected tower2");
            BoardController.type = ActionType.TOWER2;
        });
        Tower3GUI.tower3.setOnAction(e -> {
            System.out.println("selected tower3");
            BoardController.type = ActionType.TOWER3;
        });
    }

}
