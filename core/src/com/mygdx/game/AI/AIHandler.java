package com.mygdx.game.ai;

import com.mygdx.game.entities.Entity;

/** Handles the ai behaviour each tick
 * @Author Josh Leeder
 * @Date 27/02/97
 */
public class AIHandler {

    private AI friendlyBehaviour;
    private AI enemyBehaviour;


    public AIHandler(){

        friendlyBehaviour = new FriendlyAI();
        enemyBehaviour = new EnemyAI();
    }

    public void makeEntityAct(Entity entity){
        friendlyBehaviour.moveEntity(entity);
       // enemyBehaviour.moveEntity(entity);
        enemyBehaviour.act(entity);

    }
}
