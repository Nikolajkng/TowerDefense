package dk.dtu.backend;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.RemoteSpace;

public class PlayerInfoExchange {
    public static RemoteSpace playerInfoExchange;

    // Connects to remoteSpace for message passing
    public static void startPlayerInfoExchange(String roomURI) throws UnknownHostException, IOException {
        System.out.println("PlayerInfoExchange connected");
        playerInfoExchange = new RemoteSpace(roomURI);
    }


    public static void playerSendMessage(int x, int y, String action) {
        try {
            playerInfoExchange.put(x,y, action);
            

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
}
