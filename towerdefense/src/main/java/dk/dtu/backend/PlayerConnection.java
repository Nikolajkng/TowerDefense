package dk.dtu.backend;

import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;

public class PlayerConnection{


    public static void joinGame(ActionEvent event) {
        System.out.println("Joining game...");

        // Code here:
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Joining Game");
        dialog.setHeaderText(null); // Must be null, otherwise the header text will be displayed twice
        dialog.setContentText("Enter Ipv4 address of Host:");

        // Show the dialog and wait for the user's response
        dialog.showAndWait().ifPresent(userIP -> {
            System.out.println("Entered IP is: " + userIP);
            // Process the userIP here:

        });

    }

    public static void hostGame(ActionEvent event) {
        System.out.println("Hosting game...");
        // Code here:

    }
}