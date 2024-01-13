package dk.dtu.app.controller.Waves;

import dk.dtu.app.controller.BoardLogic.MyPane;
import dk.dtu.app.controller.Enemy.Enemy;
import javafx.scene.shape.Circle;

public class Wave {
    private MyPane board;
    private int numberOfEnemies;
    private int enemyType;


    public Wave(MyPane myPane,int numberOfEnemies, int enemyType) {
        this.board = myPane;
        this.numberOfEnemies = numberOfEnemies;
        this.enemyType = enemyType;
    }

    public static void spawnEnemy(MyPane leftBoard, int i, Circle circle) {
        int health = 100;
        int speed = 1;
        int damage = 5;
        Enemy enemy = new Enemy(leftBoard, health, speed, damage);
        leftBoard.getChildren().add(enemy.getEnemyShape());
             System.out.println("Spawning enemy...");
    }

}
