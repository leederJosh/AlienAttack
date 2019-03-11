package com.mygdx.game.ai;

import com.mygdx.game.entities.Entity;

/**
 * Standard Artificial Intelligence behaviour
 * Currently will apply movement to all entities
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI {

    /** Whether the entity will move right or not */
    private boolean moveRight;

    /**
     * Moves the entity in a direction
     * @param entity
     */
    public void moveEntity(Entity entity){

        if(entity.getB2body().getPosition().x > entity.getxDestination()){
            moveRight = false;
            
        }
        else if(entity.getB2body().getPosition().x < entity.getxDestination()){
            moveRight = true;
        }

        if(moveRight == true){
            entity.moveRight(true, entity);
        }
        else {
            entity.moveRight(false, entity);
        }
    }

    /** Abstract method to make different types of entities act with different behaviour */
    public abstract void act(Entity entity);
}
