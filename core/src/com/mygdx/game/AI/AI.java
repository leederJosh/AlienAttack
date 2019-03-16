package com.mygdx.game.ai;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.levels.AbstractLevel;

/**
 * Standard Artificial Intelligence behaviour
 * Currently will apply movement to all entities
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI{

    /** Whether the entity will move right or not */
    private boolean moveRight;
    /** The size of a tile */
    private final float TILE_SIZE = 16 / AlienGame.ppm;
    /** The level the entity ill move in */
    private AbstractLevel level;



    public AI(AbstractLevel level){
        this.level = level;
    }

    /**
     * Moves the entity in a direction
     * @param entity
     */
    public void moveEntity(Entity entity){
        boolean isEntityMovingRight = entity.getMoveRight();

        if(isEntityMovingRight == true){
            entity.setMoveRight(false);
//                moveRight = false;
//                entity.moveRight(false);
        }
        else{
            entity.setMoveRight(true);
//                moveRight = true;
//                entity.moveRight(true);
        }

    }



    /** Abstract method to make different types of entities act with different behaviour */
    public abstract void act(Entity entity);

    /**
     * Returns true when the hit box of a given bullet overlaps the hitbox of a given entity
     * @param entity
     * @param rectangle
     * @return boolean
     */
    private boolean entityHasReachedBoundary(Entity entity, Rectangle rectangle) {

        boolean hasCollided = false;

        //Bullet variables to use
        float rectangleWidth = rectangle.getWidth();
        float rectangleHeight = rectangle.getHeight();
        float rectangleX = rectangle.getX();
        float rectangleY = rectangle.getY();

        //Entity variables to use
        float entityWidth = entity.getWidth();
        float entityHeight = entity.getHeight();
        float entityX = entity.getx();
        float entityY = entity.gety();

        //Hit boxes (mostly to make the IFs easier to read
        float entityXHitBox = entityX + entityWidth;
        float entityYHitBox = entityY + entityHeight;
        float rectangleXHitBox = rectangleX + rectangleWidth;
        float rectangleYHitBox = rectangleY + rectangleHeight;

        // Moving to the right
        if(entityXHitBox >= rectangleX && entityX < rectangleX){

            //Moving up into rectangle
            if(entityYHitBox > rectangleY && entityYHitBox < rectangleYHitBox){
                hasCollided = true;
            }
            // Moving down into the box
            else if(entityY < rectangleYHitBox && entityY > rectangleY){
                hasCollided = true;
            }
        }
        // Moving to the left
        else if(entityX <= rectangleXHitBox && entityX > rectangleX){

            //Moving up into rectangle
            if(entityYHitBox > rectangleY && entityYHitBox < rectangleYHitBox){
                hasCollided = true;
            }
            // Moving down into the box
            else if(entityY < rectangleYHitBox && entityY > rectangleY){
                hasCollided = true;
            }
        }

//        // If the given entity is within the given rectangle when moving to the right
//        if (entityYHitBox < rectangleYHitBox && entityYHitBox > rectangleY) {
//            hasCollided = checkCollision(entityX, entityXHitBox, rectangleX, rectangleXHitBox);
//        }
//        else if (entityY < rectangleYHitBox && entityY > rectangleYHitBox) {
//            hasCollided = checkCollision(entityX, entityXHitBox, rectangleX, rectangleXHitBox);
//        }
        return hasCollided;
    }

    private boolean checkCollision(float entityX, float entityMax, float hitX, float hitMax) {
        System.out.println(moveRight);
        if (moveRight) {
            System.out.println("move right is not the problem");
            if(entityX <= hitX && entityMax <= hitMax) {
                System.out.println("Making move to false");
                return true;
            }
        }
        //Otherwise the entity is moving to the left
        else {
            System.out.println("Not moveright but before second if");
            if (entityX <= hitMax && entityMax >= hitMax) {
                System.out.println("Making move to true");
                return true;
            }
        }

        return false;
    }

}


//if(entity.getB2body().getPosition().x > entity.getxDestination()){
//        moveRight = false;
//
//        }
//        else if(entity.getB2body().getPosition().x < entity.getxDestination()){
//        moveRight = true;
//        }
//
//        if(moveRight == true){
//        entity.moveRight(true, entity);
//        }
//        else {
//        entity.moveRight(false, entity);
//        }


/*
        // Shoot to the right
        if (rectangleXHitBox > entityX && rectangleXHitBox < entityXHitBox) {

            // Shoot up
            if (rectangleYHitBox > entityY && rectangleYHitBox < entityYHitBox) {

                hasCollided = true;
            }
            // Shoot down
            else if (rectangleY < entityYHitBox && rectangleY > entityY) {

                hasCollided = true;
            }

        }*/