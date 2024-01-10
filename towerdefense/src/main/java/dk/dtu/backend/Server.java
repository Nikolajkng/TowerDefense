package dk.dtu.backend;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    private static String ip;
    public static SequentialSpace room = new SequentialSpace();
    public static String room_uri = "";


    public static void hostNewGame(){
        // Local IPv4 address
        ip = LocalAddressScript.getLocalAddress(); 

        // Gate URI into spaceRepository
        int port = 55000;
        String gate_uri = "http://"+ ip + ":" + port + "/?keep";
        System.out.println("Gate URI:"+gate_uri);
        
        //Creating a new spaceRepository
        SpaceRepository server = new SpaceRepository();
        server.addGate(gate_uri);

        // Creating a room inside the spacerepository
        String spaceName = "Room";
        server.add(spaceName, room);

        // Creating a gateway into the room via new URI
        room_uri = "http://"+ ip + ":" + port + "/" + spaceName + "?keep";
        System.out.println("Room URI"+room_uri);

    }

}
