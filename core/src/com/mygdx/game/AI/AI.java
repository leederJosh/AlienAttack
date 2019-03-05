package com.mygdx.game.AI;

import com.mygdx.game.entities.Entity;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Standard Artificial Inteligence behaviour
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI {

    //TODO
    // Want to have all entities to move from left to right
    // Variables I need:
    //  Origin X
    //  Some kind of maximum movement distance
    //  Boolean to move right or left
    // Methods I will need:
    //  Move left
    //  Move right
    //  Calculate decision

    /** Whether the entity will move right or not */
    protected boolean moveRight;
    /** The original X position of the entity */
    protected float xOrigin;
    /** How much the entity will travel in the X plane */
    protected float xToTravel = 10;
    /** The current X position of the Entity */
    protected float xCurrent;
    /** Amount to move per tick */
    private float xPerTick = 1;


    /**
     * Moves the entity in a direction
     * @param entity
     */
    public void moveEntity(Entity entity){

//        xOrigin = entity.getx();
//
//        if(entity.getx() >= xOrigin + xToTravel){
//            moveRight = false;
//        }
//        else if (entity.getx() <= xOrigin){
//            moveRight = true;
//        }

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

    protected void checkMoveDirection(Entity entity){

        //Needs to check if the current entity X is GREATER than the xDestination
        // IF it is set moveRight to false
        // IF the entity x is less than the X origin the set moveRight to true

        if(entity.getx() > entity.getxDestination()){
            moveRight = false;
        }
        else if(entity.getx() < entity.getxOrigin()){
            moveRight = true;
        }
    }

    public abstract void act(Entity entity);
}
