package dk.dtu.backend;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    private static String ip;
    public static SequentialSpace gameRoom = new SequentialSpace();
    public static SequentialSpace P1P2room = new SequentialSpace();  // P1 sends, P2 receives
    public static SequentialSpace P2P1room = new SequentialSpace();  // P2 sends, P1 receives
    public static SequentialSpace chatRoom = new SequentialSpace();
    
    public static String gameRoom_uri = "";
    public static String P1P2_uri = "";
    public static String P2P1_uri = "";
    public static String chatRoom_uri = "";


    public static void hostNewGame(){
        // Local IPv4 address
        ip = LocalAddressScript.getLocalAddress(); 

        // Gate URI into spaceRepository
        int port = 55000;
        String gate_uri = "tcp://"+ ip + ":" + port + "/?keep";
        System.out.println("Gate URI:"+gate_uri);
        
        //Creating a new spaceRepository
        SpaceRepository server = new SpaceRepository();
        server.addGate(gate_uri);

        // Creating a room inside the spacerepository;
        server.add("GameRoom", gameRoom);
        server.add("P1P2room", P1P2room);
        server.add("P2P1room", P2P1room);
        server.add("Chatroom", chatRoom);

        // Creating gateways into the all the room in the SpaceRepository (Server)
        gameRoom_uri = "tcp://"+ ip + ":" + port + "/GameRoom?keep";
        P1P2_uri = "tcp://"+ ip + ":" + port + "/P1P2room?keep";
        P2P1_uri = "tcp://"+ ip + ":" + port + "/P2P1room?keep";
        chatRoom_uri =  "tcp://"+ ip + ":" + port + "/chatRoom?keep";
        System.out.println("gameRoom URI: " + gameRoom_uri);
        System.out.println("P1P2room URI: " + P1P2_uri);
        System.out.println("P2P1room URI: " + P2P1_uri);

    }

}
