package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.RemoteSpace;

import dk.dtu.app.controller.Tower;

public class ActionExchange {
    public static RemoteSpace P1P2room;
    public static RemoteSpace P2P1room;
    public static String callsign;

    // Connects to remoteSpace for message passing
    public static void start(String P1P2uri, String P2P1uri) throws UnknownHostException, IOException {
        P1P2room = new RemoteSpace(P1P2uri);
        P2P1room = new RemoteSpace(P2P1uri);
    }

    public static void sendAction(int x, int y, Tower.ActionType type, String callsign) {
        if (callsign == "Host") {
        try {
            System.out.println(callsign + " has selected following: " + type);
            P1P2room.put(x, y, type);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } else {
         try {
           System.out.println(callsign + " has selected following: " + type);            
           P2P1room.put(x, y, type);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    }

        
}
