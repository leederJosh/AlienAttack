package com.mygdx.game.AI;

import com.mygdx.game.entities.Entity;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Standard Artificial Inteligence behaviour
 * Currently will apply movement to all entities
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI {


    /** Whether the entity will move right or not */
    protected boolean moveRight;
    /** Amount to move per tick */
    private float xPerTick = 1;


    /**
     * Moves the entity in a direction
     * @param entity
     */
    public void moveEntity(Entity entity){


        if(entity.getx() > entity.getxDestination()){
            moveRight = false;
        }
        else if(entity.getx() < entity.getxOrigin()){
            moveRight = true;
        }

        if(moveRight == true){
            entity.moveX(xPerTick);
        }
        else {
            entity.moveX(-xPerTick);
        }
    }

    /** Abstract method to make different types of entities act with differnt behaviour */
    public abstract void act(Entity entity);
}
