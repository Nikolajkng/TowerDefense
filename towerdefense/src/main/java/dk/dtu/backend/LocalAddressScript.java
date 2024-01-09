package dk.dtu.backend;

import java.net.DatagramSocket;
import java.net.InetAddress;

// IPv4 address retrieval code by Mr.Wang from Next Door 
// Link to source: https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
public class LocalAddressScript {
    public static String getLocalAddress(){
    String ip = "";
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
}
