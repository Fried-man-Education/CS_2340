package com.tower_defense.tower_defense;

import com.tower_defense.tower_defense.enemies.GhostEnemy;

import java.util.ArrayList;

public class EnemyController {
    private ArrayList<GhostEnemy> ghostEnemies = new ArrayList<>();

    public EnemyController(int numGhosts){
        sendEnemies(numGhosts);
    }

    public void sendEnemies(int numGhosts){
        while(numGhosts > 0){
            for(GhostEnemy ghost : ghostEnemies){

            }
            //iterate through ghostEnemies
            //find next square
            //move
            //wait
        }
    }

    public int[] nextPathSquare(int row, int col){

    }
}
