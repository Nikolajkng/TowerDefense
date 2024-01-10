package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

public class PlayerInfoExchange {
    public static RemoteSpace playerInfoExchange;

    // Connects to remoteSpace for message passing
    public static void start(String roomURI) throws UnknownHostException, IOException {
        System.out.println("PlayerInfoExchange connected");
        playerInfoExchange = new RemoteSpace(roomURI);
    }

    public static void sendAction(int x, int y, String action) {
        try {
            System.out.println("X: " + x + " Y: " + y + " - Selected following: " + action);
            playerInfoExchange.put(x, y, action);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

        
}
