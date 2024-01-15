package dk.dtu.Enemies;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.Space;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.EnemyMovement;
import dk.dtu.app.view.Figures.Enemy1_BunnyGUI;

public class Enemy_Bunny extends EnemyMovement {

    private int speed = 1;
    private int hp = 100;
    private Enemy1_BunnyGUI gui;

    public Enemy_Bunny(int x, int y, Space space, int me, MyPane board) {
        super(x, y, space, me, board);
        // gui = new Enemy1_BunnyGUI(new Circle(30));
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
