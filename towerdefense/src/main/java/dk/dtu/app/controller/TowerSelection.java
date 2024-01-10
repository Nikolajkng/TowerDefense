package dk.dtu.app.controller;
import dk.dtu.app.controller.BoardLogic.BoardController;
import dk.dtu.app.view.GameBoardsGUI.*;

public class TowerSelection {
    public static void selectTower(){
        // Buttons funtionality (To be moved to controller folder later)
        MultiplayerBoard.towerBtn1.setOnAction(e -> {
            System.out.println("selected tower1");
            BoardController.action = "tower1";
        });
        MultiplayerBoard.towerBtn2.setOnAction(e -> {
            BoardController.action = "tower2";
            System.out.println("selected tower2");
        });
        MultiplayerBoard.towerBtn3.setOnAction(e -> {
            BoardController.action = "tower3";
            System.out.println("selected tower3");
        });
    }
}
