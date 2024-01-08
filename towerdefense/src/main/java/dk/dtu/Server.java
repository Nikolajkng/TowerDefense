package dk.dtu;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    public static void main(String[] args) {

        String ip = "192.168.0.167"; // Tror der skal vælges en computer der fast er serveren
        int port = 55000;
        String gate_uri = "tcp://"+ ip + ":" + port + "/?keep";
        
        
        SpaceRepository server = new SpaceRepository();

        String spaceName = "Room";
        server.add(spaceName, new SequentialSpace());
        String remote_uri_room = "tcp://"+ ip + ":" + port + "/" + spaceName + "?keep";

        
        server.addGate(gate_uri);
    }
}
