package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.RemoteSpace;

public class PlayerInfoExchange {
    public static RemoteSpace P1P2room;
    public static RemoteSpace P2P1room;
    public static String callsign;

    // Connects to remoteSpace for message passing
    public static void start(String P1P2uri, String P2P1uri) throws UnknownHostException, IOException {
        P1P2room = new RemoteSpace(P1P2uri);
        P2P1room = new RemoteSpace(P2P1uri);
    }

    public static void sendAction(int x, int y, String action, String callsign) {
        if (callsign == "Host") {
        try {
            System.out.println("X: " + x + " Y: " + y + " - Selected following: " + action);
            P1P2room.put(x, y, action);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } else {
         try {
            System.out.println("X: " + x + " Y: " + y + " - Selected following: " + action);
            P2P1room.put(x, y, action);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }

        
}
