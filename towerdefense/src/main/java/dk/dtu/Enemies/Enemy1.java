package dk.dtu.Enemies;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.Enemy_movement;

public class Enemy1 extends Enemy_movement {

    private int speed = 5;
    private int hp = 100;

    public Enemy1(int x, int y, Space space, int me) {
        super(x, y, space, me);
    }

    public void takeDamage(int damage) {
        try {
            Object[] obj = super.space.get(new ActualField(super.me), new ActualField("Damage"),
                    new FormalField(Integer.class));
            if (obj != null) {
                hp -= (Integer) obj[2];
                if (hp <= 0) {
                    super.space.put(me, "Terminate");
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
