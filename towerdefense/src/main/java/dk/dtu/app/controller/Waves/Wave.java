package dk.dtu.app.controller.Waves;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy;
import javafx.scene.shape.Circle;

public class Wave {
    protected MyPane board;
    protected int numberOfEnemies;
    protected int enemyType;


    public Wave(MyPane myPane,int numberOfEnemies, int enemyType) {
        this.board = myPane;
        this.numberOfEnemies = numberOfEnemies;
        this.enemyType = enemyType;
    }

    public static void spawnEnemy(MyPane leftBoard, Circle circle) {
        Enemy enemy = new Enemy(leftBoard);
        leftBoard.getChildren().add(enemy.getEnemyShape());
             System.out.println("Spawning enemy...");
    }

}
