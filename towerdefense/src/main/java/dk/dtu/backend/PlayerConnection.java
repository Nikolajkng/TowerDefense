package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.*;

import dk.dtu.app.controller.BattleLogic;
import dk.dtu.app.controller.Waves.Wave1;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.app.view.MenuGUI.MultiplayerMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class PlayerConnection {
    public static String inputIP = "";
    public static String callsign;

    ///////////////////////////////////////////////// Server /////////////////////////////////////////////////////////////////////
    public static void hostGame(ActionEvent event) throws UnknownHostException, IOException {
        Alert hostDialog = new Alert(AlertType.INFORMATION);
        inputIP = LocalAddressScript.getLocalAddress();
        callsign = "Host";
        System.out.println("Hosting game...");
        Server.hostNewGame();
        hostDialog.setTitle("Hosting Game");
        hostDialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
        hostDialog.setContentText("Hosting a game on IP address: " + LocalAddressScript.getLocalAddress());
        hostDialog.showAndWait();
        try {

            Server.gameRoom.put("join", "player1");
            System.out.println("Player 1 has joined the room");

            // Waiting on player 2 to join room
            Server.gameRoom.get(new ActualField("join"), new ActualField("player2"));
            System.out.println("Player 2 has joined the room");

            // Start game - if player 2 has joined
            gameStart();
            ActionSender.start(Server.P1P2_uri, Server.P2P1_uri);
            new Thread(new ActionReceiver(Server.P2P1room)).start();
            new Thread(new ChatServer(callsign)).start();

        } catch (InterruptedException e) {
            System.out.println("Error: Player 2 did not join the room");
            e.printStackTrace();
        }

    }
    
/////////////////////////////////////////////////////// Client ///////////////////////////////////////////////////////////
    public static void joinGame(ActionEvent event) throws InterruptedException {
        System.out.println("Joining game...");
        callsign = "Client";

        // Retrieves the IP address of the host:
        showTextInputDialog();
        String gameRoom_uri = "tcp://" + inputIP + ":55000/GameRoom?keep";
        String P1P2_uri = "tcp://" + inputIP + ":55000/P1P2room?keep";
        String P2P1_uri = "tcp://" + inputIP + ":55000/P2P1room?keep";

        // Connect to the room
        try {
            System.out.println("Connecting to room at: " + gameRoom_uri);
            RemoteSpace gameRoom = new RemoteSpace(gameRoom_uri);
            gameRoom.put("join", "player2");

            // Create two remoteSpaces for message passing between player 1 and player 2
            RemoteSpace P1P2room = new RemoteSpace(P1P2_uri);
            //RemoteSpace P2P1room = new RemoteSpace(P2P1_uri);

            // Start game
            gameStart();
            ActionSender.start(P1P2_uri, P2P1_uri);
            new Thread(new ActionReceiver(P1P2_uri, P1P2room)).start();
            new Thread(new ChatServer(callsign)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

///////////////////////////////////////////////////// Start game by opening multiplayer board for both player /////////////////////////////////////////////////////////////
    private static void gameStart() throws UnknownHostException, IOException {
        System.out.println("Game has started!");
        // Close the current MainMenu stage
        MultiplayerBoard multiplayerBoard = new MultiplayerBoard();
        multiplayerBoard.start(MultiplayerBoard.boardStage);
        MultiplayerMenu.boardStage.close();
        MultiplayerBoard.boardStage.show();
        BattleLogic battleLogic = new BattleLogic(Server.gameRoom);
        battleLogic.waves(new Wave1().enemies);
    }

///////////////////////////////////////////////////// Function that retrieves clients inputIP (host ip) /////////////////////////////////////////////////////////////
    private static void showTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Joining Game");
        dialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
        dialog.setContentText("Enter Ipv4 address of Host:");

        // Show the dialog and wait for the user's response
        dialog.showAndWait().ifPresent(input -> {
            inputIP = input;
        });

    }
}