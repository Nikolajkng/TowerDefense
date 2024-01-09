package dk.dtu;

import java.net.SocketException;
import java.net.UnknownHostException;
import dk.dtu.app.view.MenuGUI.Menu;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) throws UnknownHostException, SocketException {

        // // Launch the Lobby Application (start window)
        System.out.println("Starting application...");
        Application.launch(Menu.class, args);
    

    }

}