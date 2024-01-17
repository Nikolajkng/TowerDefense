package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import dk.dtu.app.controller.BoardLogic.ChatController;
import dk.dtu.app.view.GameBoardsGUI.MultiplayerBoard;
import dk.dtu.app.view.MenuGUI.Menu;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ChatReceiver implements Runnable {
    private String callsign;
    private RemoteSpace chatRoom;
    

    public ChatReceiver(String callsign) throws UnknownHostException, IOException {
        this.callsign = callsign;
        this.chatRoom = new RemoteSpace("tcp://" + PlayerConnection.inputIP + ":55000/ChatRoom?keep");

    }

    @Override
    public void run() {
        System.out.println(callsign + ": ChatReceiver thread: run() has started");
        try {

            listenForMessages();
            Thread.sleep(100); // Adds a time delay in the while-loop of 100 ms

        } catch (InterruptedException e) {
            if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                System.out.println("virker");
            } else {
                e.printStackTrace();
            
        }

    }

    private void listenForMessages() {
        ///////////////////////////////////////////////////// HOST //////////////////////////////////////////////////////////////////
        if (callsign.equals("Host")) {
            try {
                while (true) {
                    // Listens to message
                    Object[] message = chatRoom.get(new ActualField("Client"), new FormalField(String.class));

                    // Check type of message: chatmessage or disconnect
                    if (((String) message[1]).startsWith(" ")) {
                        System.out.println("Received message from Client: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatController.updateChatBox("Client", (String) message[1]);
                        });
                    } else if (((String) message[1]).equals("disconnect")){
                        System.out.println("Received disconnect from Client");
                        PlayerConnection.closeHostThreads();
                        Platform.runLater(() -> {
                            MultiplayerBoard.boardStage.close();
                            Menu.mainMenuStage.show();
                            showEndGameResult(callsign);
                        });
                    } else{
                    }

                }
            } catch (InterruptedException e) {
                if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                    Platform.runLater(() -> {
                        MultiplayerBoard.boardStage.close();
                    });
                } else {
                    e.printStackTrace();
                }
            }
            ///////////////////////////////////////////////////// CLIENT //////////////////////////////////////////////////////////////////
        } else if (callsign.equals("Client")) {

            try {
                while (true) {
                    // Listens to message
                    Object[] message = chatRoom.get(new ActualField("Host"), new FormalField(String.class));

                    // Check type of message: chatmessage or disconnect
                    if (((String) message[1]).startsWith(" ")) {
                        System.out.println("Received message from Host: " + (String) message[1]);
                        Platform.runLater(() -> {
                            ChatController.updateChatBox("Host", (String) message[1]);
                        });
                    } else if (((String) message[1]).equals("disconnect")){
                        System.out.println("Received disconnect from Host");
                        PlayerConnection.closeClientThreads();
                        Platform.runLater(() -> {
                            MultiplayerBoard.boardStage.close();
                            Menu.mainMenuStage.show();
                            showEndGameResult(callsign);
                        });
                    } else{
                        System.out.println("hej host: " + message[1]);
                    }

                }
            } catch (InterruptedException e) {
                if (e.getMessage() != null && e.getMessage().contains("Gate is closed!")) {
                    Platform.runLater(() -> {
                        MultiplayerBoard.boardStage.close();
                    });
                    System.out.println("virker3");
                } else {
                    e.printStackTrace();
                }

            }
        }

    }
    private static void showEndGameResult(String callsign) {
        System.out.println("Game Result: " + callsign + " has won the game!");
    Platform.runLater(() -> {
        Alert hostDialog = new Alert(AlertType.INFORMATION);
        hostDialog.setTitle("Game Over");
        hostDialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice

        // Create a custom label with centered and bigger text
        Label contentLabel = new Label("A player has disconnected. "+callsign +" has Won!");
        contentLabel.setStyle("-fx-font-size: 16px;"); // Adjust the font size as needed
        StackPane.setAlignment(contentLabel, Pos.CENTER);

        // Create a custom dialog pane and set the content
        StackPane customPane = new StackPane(contentLabel);
        customPane.setStyle("-fx-padding: 20px;");
        hostDialog.getDialogPane().setContent(customPane);

        hostDialog.showAndWait();
    });
}
}
