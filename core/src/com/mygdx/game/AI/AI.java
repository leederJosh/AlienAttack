package com.mygdx.game.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.mygdx.game.collisions.MyRayCaster;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.game.AlienGame;

/**
 * Standard Artificial Intelligence behaviour
 * Currently will apply movement to all entities
 * @Author Josh Leeder
 * @Date 27/02/19
 */
public abstract class AI{

    /** Whether the entity will move right or not */
    private boolean moveRight;
    /** Raycast for entity movement */
    private RayCastCallback rayCastCallback;
    /** The size of a tile */
    private final float TILE_SIZE = 16 / AlienGame.ppm;

    /**
     * Moves the entity in a direction
     * @param entity
     */
    public void moveEntity(Entity entity){

        rayCastCallback = new MyRayCaster();

       // rayCastCallback.reportRayFixture();

        ((MyRayCaster) rayCastCallback).isHasHitSomething();
        if(moveRight == true){

            // Point 1 is on the top right corner of the entity
            Vector2 bottomRightPoint = new Vector2(entity.getx() + entity.getWidth(), entity.gety() + entity.getHeight());
            // Point 2 is where the ray cast "ends", where we will detect until
            Vector2 rayExtension = new Vector2(entity.getx() + entity.getWidth(), entity.gety() - TILE_SIZE / 4);

            //rayCastCallback

            // Actually casts the ray from the entity
            //world.rayCast(rayCastCallback, bottomRightPoint, rayExtension);

//            if(rayCastCallback.)


        }
    }

    /** Abstract method to make different types of entities act with different behaviour */
    public abstract void act(Entity entity);

    //TODO
    // RAYCASTING FOR ENTITY MOVEMENT
    // IF MOVE RIGHT IS TRUE
    // THEN RAYCAST DOWNWARDS FROM THE PLAYER X PLUS WIDTH (CAST DOWN HALF A TILE SIZE)
    // WHEN THE RAY HITS NOTHING THEN SET MOVERIGHT TO FALSE
    // ALSO SET THE NEW RAY CAST TO PLAYER X


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