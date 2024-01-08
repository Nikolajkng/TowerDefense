package dk.dtu.backend;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    private static String ip;
    public static void main(String[] args) throws UnknownHostException, SocketException {
        
        // IPv4 address retrieval code by Mr.Wang from Next Door 
        // Link to source: https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            
        }

        // Gate URI 
        int port = 55000;
        String gate_uri = "tcp://"+ ip + ":" + port + "/?keep";
        System.out.println(gate_uri);
        
        SpaceRepository server = new SpaceRepository();
        server.addGate(gate_uri);

        String spaceName = "Room";
        server.add(spaceName, new SequentialSpace());
        String remote_uri_room = "tcp://"+ ip + ":" + port + "/" + spaceName + "?keep";

        
    }
}
