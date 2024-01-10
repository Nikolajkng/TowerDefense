package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;

import dk.dtu.app.view.MultiplayerBoard;
import dk.dtu.backend.LocalAddressScript;

public class MyPlacement extends MultiplayerBoard implements Runnable {
    
    public MyPlacement(SequentialSpace space) {
        super(space);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object[] obj = space.get(new ActualField("placeTower"), new FormalField(String.class), new FormalField(Integer.class), new FormalField(Integer.class));
                if (obj != null){
                    String opponentIP = (String) obj[1];
                    if (opponentIP != LocalAddressScript.getLocalAddress()){
                        int x = (Integer) obj[2];
                        int y = (Integer) obj[3];
                        super.rightBoard[x][y].setValue(0);
                        super.rightBoard[x][y].setText("x");
                    } else {
                        space.put(obj);
                    }
                }
            } catch (InterruptedException e) {}
        }
    }
    
}
