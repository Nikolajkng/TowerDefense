package dk.dtu.app.controller.Waves;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy_movement;

public class Wave {
    protected MyPane board;
    protected int numberOfEnemies;
    protected int enemyType;


    public static void spawnEnemy(MyPane leftBoard) {
        Enemy_movement enemy = new Enemy_movement(leftBoard);
        //leftBoard.getChildren().add(enemy.getEnemyShape());
             System.out.println("Spawning enemy...");
    }

}
