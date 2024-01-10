package dk.dtu.backend;

import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;

public class GameUpdate implements Runnable {
    String uri = "";
    RemoteSpace room;
    SequentialSpace room2;

    public GameUpdate(String uri, RemoteSpace room) {
        this.uri = uri;
        this.room = room;
    }

    public GameUpdate(String uri, SequentialSpace room) {
        this.uri = uri;
        this.room2 = room;
    }

    @Override
    public void run() {
        if (room != null) {
            try {
                while (true) {
                    Object[] info = room.queryp(new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(String.class));
                    if (info != null) {
                        Object[] action = room.get(new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(String.class));
                        System.out.println("Received action: " + (String) action[2]);

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                while (true) {
                    Object[] info = room2.queryp(new FormalField(Integer.class),
                            new FormalField(Integer.class),
                            new FormalField(String.class));
                    if (info != null) {
                        Object[] action = room2.get(new FormalField(Integer.class),
                                new FormalField(Integer.class),
                                new FormalField(String.class));
                        System.out.println("Received action: " + (String) action[2]);

                    }
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
