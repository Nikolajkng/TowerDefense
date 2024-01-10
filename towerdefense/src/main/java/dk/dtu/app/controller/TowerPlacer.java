package dk.dtu.app.controller;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;

import dk.dtu.Towers.Tower1;fa

public class TowerPlacer extends BattleLogic implements Runnable {

    public TowerPlacer(SequentialSpace space) {
        super(space);
    }

    @Override
    public void run() {
        int towerCounter = 0;
        while (true) {
            try {
                Object[] obj = space.get(new ActualField("Place tower"), new FormalField(Integer.class),
                        new FormalField(Integer.class));
                if (obj != null) {
                    new Thread(new Tower1((Integer) obj[1], (Integer) obj[2], space, towerCounter));
                    towerCounter++;
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
