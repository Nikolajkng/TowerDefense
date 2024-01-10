package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;

import dk.dtu.app.view.MultiplayerBoard;
import dk.dtu.backend.LocalAddressScript;

public class EnemyPlacement extends MultiplayerBoard implements Runnable {
    
    public EnemyPlacement(SequentialSpace space) {
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

                        // Sætter modstanderens tårn
                        System.out.println("modstander tårn: x=" + x + " y=" + y);
                        super.rightBoard[x][y].setText("x");
                        
                    } else {
                        space.put(obj);
                        // 1 millisecond delay so the opponents listener can pick up this tuple
                        Thread.sleep(1);
                    }
                }
            } catch (InterruptedException e) {}
        }
    }
    
}
