package dk.dtu.app.controller.Waves;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy;

public class Wave {
    protected MyPane board;
    protected int numberOfEnemies;
    protected int enemyType;


    public static void spawnEnemy(MyPane leftBoard) {
        Enemy enemy = new Enemy(leftBoard);
        //leftBoard.getChildren().add(enemy.getEnemyShape());
             System.out.println("Spawning enemy...");
    }

}
