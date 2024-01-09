package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.*;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.app.view.MenuGUI.MultiplayerMenu;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class PlayerConnection {
    public static String inputIP = "";

    public static void hostGame(ActionEvent event) throws UnknownHostException, IOException {
        Alert hostDialog = new Alert(AlertType.INFORMATION);
        System.out.println("Hosting game...");
        Server.hostNewGame();
        hostDialog.setTitle("Hosting Game");
        hostDialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
        hostDialog.setContentText("Hosting a game on IP address: " + LocalAddressScript.getLocalAddress());
        hostDialog.showAndWait();
        try {

            Server.room.put("join", "player1");
            System.out.println("Player 1 has joined the room");

             // Waiting on player 2 to join room
            Server.room.get(new ActualField ("join"), new ActualField("player2"));
            System.out.println("Player 2 has now joined the room");

            // Start game - if player 2 has joined
            gameStart();
            PlayerInfoExchange.start(Server.room_uri);


        } catch (InterruptedException e) {
            System.out.println("Error: Player 2 did not join the room");
            e.printStackTrace();
        }

    }

    
    public static void joinGame(ActionEvent event) throws InterruptedException {
        System.out.println("Joining game...");

        // Code here:
        showTextInputDialog();
        String URIformat = "tcp://" + inputIP + ":55000/Room?keep";

        // Connect to the room
        try {
            System.out.println("Connecting to room at: " + URIformat);
            RemoteSpace myRoom = new RemoteSpace(URIformat);
            myRoom.put("join", "player2");

            // Start game
            gameStart();
            PlayerInfoExchange.start(URIformat);



            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void gameStart(){
        System.out.println("Game has started!");
        // Close the current MainMenu stage
        MultiplayerBoard multiplayerBoard = new MultiplayerBoard();
        multiplayerBoard.start(MultiplayerBoard.boardStage);
        MultiplayerMenu.boardStage.close();
        MultiplayerBoard.boardStage.show();
    }

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