package dk.dtu;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    private static String ip;
    public static void main(String[] args) throws UnknownHostException, SocketException {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
            
        }

        int port = 55000;
        String gate_uri = "tcp://"+ ip + ":" + port + "/?keep";
        
        
        SpaceRepository server = new SpaceRepository();

        String spaceName = "Room";
        server.add(spaceName, new SequentialSpace());
        String remote_uri_room = "tcp://"+ ip + ":" + port + "/" + spaceName + "?keep";

        
        server.addGate(gate_uri);
    }
}
