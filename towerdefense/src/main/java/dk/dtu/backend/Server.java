package dk.dtu.backend;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    private static String ip;
    public static void main(String[] args) throws UnknownHostException, SocketException {
        // Local IPv4 address
        ip = LocalAddressScript.getLocalAddress(); 

        // Gate URI 
        int port = 55000;
        String gate_uri = "tcp://"+ ip + ":" + port + "/?keep";
        System.out.println("Gate URI:"+gate_uri);
        
        SpaceRepository server = new SpaceRepository();
        server.addGate(gate_uri);

        String spaceName = "Room";
        server.add(spaceName, new SequentialSpace());
        String room_uri = "tcp://"+ ip + ":" + port + "/" + spaceName + "?keep";
        System.out.println("Room URI"+room_uri);

        // Creating a new thread to handle player joining
                        
    }
}
