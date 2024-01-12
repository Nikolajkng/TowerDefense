package dk.dtu.app.controller;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.controller.Action.ActionType;
import dk.dtu.app.view.GameBoardsGUI.*;

public class TowerSelection {
    public static void selectTower(){
        // Buttons funtionality (To be moved to controller folder later)
        MultiplayerBoard.towerBtn1.setOnAction(e -> {
            System.out.println("selected tower1");
            BoardController.type = ActionType.TOWER1;
        });
        MultiplayerBoard.towerBtn2.setOnAction(e -> {
            BoardController.type = ActionType.TOWER2;
            System.out.println("selected tower2");
        });
        MultiplayerBoard.towerBtn3.setOnAction(e -> {
            BoardController.type = ActionType.TOWER3;
            System.out.println("selected tower3");
        });
        MultiplayerBoard.towerBtn4.setOnAction(e -> {
            BoardController.type = ActionType.TOWER4;
            System.out.println("selected tower4");
        });
        MultiplayerBoard.towerBtn5.setOnAction(e -> {
            BoardController.type = ActionType.TOWER5;
            System.out.println("selected tower5");
        });
    }
}
