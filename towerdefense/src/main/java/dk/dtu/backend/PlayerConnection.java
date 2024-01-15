package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;

import org.jspace.*;

import dk.dtu.app.controller.BattleLogic;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.app.view.MenuGUI.Menu;
import dk.dtu.app.view.MenuGUI.MultiplayerMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class PlayerConnection {
    // Global fields
    public static String inputIP = "";
    public static String callsign;

    // Local fields
    public static Thread hostActionListenerThread;
    public static Thread hostChatListenerThread;
    public static Thread clientActionListenerThread;
    public static Thread clientChatListenerThread;
    public static Thread clientBattleLogicThread;
    public static Thread hostBattleLogicThread;


    ///////////////////////////////////////////////// HOST /////////////////////////////////////////////////////////////////////
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
            showMultiPlayerBoard();
            hostBattleLogicThread = new Thread(new BattleLogic(Server.gameRoom, MultiplayerBoard.leftBoard));
            Server.gameRoom.put(callsign,"gameState","ONGOING");
            ActionSender.start(Server.P1P2_uri, Server.P2P1_uri);
            hostActionListenerThread = new Thread(new ActionReceiver(Server.P2P1room));
            hostChatListenerThread = new Thread(new ChatReceiver(callsign));
            hostActionListenerThread.start();
            hostChatListenerThread.start();
            hostBattleLogicThread.start();

        } catch (InterruptedException e) {
            System.out.println("Error: Player 2 did not join the room");
            e.printStackTrace();
        }

    }


    /////////////////////////////////////////////////////// CLIENT ///////////////////////////////////////////////////////////
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
            showMultiPlayerBoard();
            ActionSender.start(P1P2_uri, P2P1_uri);
            clientActionListenerThread = new Thread(new ActionReceiver(P1P2_uri, P1P2room));
            clientChatListenerThread = new Thread(new ChatReceiver(callsign));
            clientBattleLogicThread = new Thread(new BattleLogic(Server.gameRoom, MultiplayerBoard.leftBoard));
            clientActionListenerThread.start();
            clientChatListenerThread.start();
            clientBattleLogicThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 

///////////////////////////////////////////////////// Start game by opening multiplayer board for both player /////////////////////////////////////////////////////////////
    private static void showMultiPlayerBoard() throws UnknownHostException, IOException {
        System.out.println("Game has started!");
        // Close the current MainMenu stage
        MultiplayerBoard multiplayerBoard = new MultiplayerBoard(callsign);
        multiplayerBoard.start(MultiplayerBoard.boardStage);
        MultiplayerMenu.boardStage.close();
        MultiplayerBoard.boardStage.show();
        
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


    public static void closeClientThreads(){
        clientActionListenerThread.interrupt();
        clientChatListenerThread.interrupt();
        clientBattleLogicThread.interrupt();
    }
    
    public static void closeHostThreads(){
        hostActionListenerThread.interrupt();
        hostChatListenerThread.interrupt();
        hostBattleLogicThread.interrupt();
    }

}