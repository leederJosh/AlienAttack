package com.mygdx.game.ai;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.levels.AbstractLevel;

import java.util.ArrayList;

/** Handles the ai behaviour each tick
 * @Author Josh Leeder
 * @Date 27/02/97
 */
public class AIHandler {

    private AI friendlyBehaviour;
    private AI enemyBehaviour;
    private AbstractLevel level;


    public AIHandler(AbstractLevel level){

        friendlyBehaviour = new FriendlyAI(level);
        enemyBehaviour = new EnemyAI(level);
        this.level = level;
    }

    public void makeEntityAct(Entity entity){

        for(Rectangle rectangle : level.getBoundaryObjects()){
            friendlyBehaviour.moveEntity(entity, rectangle);
            enemyBehaviour.moveEntity(entity, rectangle);
        }

        if(entity instanceof Enemy){
            enemyBehaviour.act(entity);
        }



    }
}
