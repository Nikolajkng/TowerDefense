package dk.dtu.backend;

import dk.dtu.app.controller.BoardLogic.ChatController;
import dk.dtu.app.view.MenuGUI.Menu;
import javafx.stage.Stage;

public class DisconnectionHandler {
    // Close the current MultiplayerBoard stage
    public static void handleClosedApplication(Stage boardStage, String callsign) {
        boardStage.setOnCloseRequest(event -> {
            Menu.mainMenuStage.show();

            if (callsign == "Host") {
                try {
                    System.out.println(callsign + " lost connection");
                    ChatController.chatRoom.put(callsign, "disconnect");
                    //showEndGameResult(callsign);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PlayerConnection.closeHostThreads();
            } else if (callsign == "Client") {
                try {
                    System.out.println(callsign + " lost connection");
                    ChatController.chatRoom.put(callsign, "disconnect");
                    //showEndGameResult(callsign);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PlayerConnection.closeClientThreads();
            } else {
                System.out.println("Something went wrong in DisconnectionHandler.java");
            }

        });
    }

}
