package com.mygdx.game.collisions;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Player;
import com.mygdx.game.game.AlienGame;


/**
 * Handles what to do when world objects collide with each other
 * @Author Josh Leeder
 * @Date 16/03/19
 */
public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {

            // When an entity object collides with a boundary object
            case MapObjectLayers.ENTITY_OBJECT | MapObjectLayers.BOUNDARY_OBJECT:
                if (fixA.getFilterData().categoryBits == MapObjectLayers.ENTITY_OBJECT)
                    ((Entity) fixA.getUserData()).reverseMovement();
                else
                    ((Entity) fixB.getUserData()).reverseMovement();
            break;

            // When an player object collides with a trap object
            case MapObjectLayers.PLAYER_OBJECT | MapObjectLayers.TRAP_OBJECT:
                System.out.println("contact with player and trap");
                if (fixA.getFilterData().categoryBits == MapObjectLayers.PLAYER_OBJECT) {
                    ((Player) fixA.getUserData()).reduceHealth(10);
                    if (fixB.getBody().getPosition().x < ((Player) fixA.getUserData()).getB2body().getPosition().x) {
                        ((Player) fixA.getUserData()).getB2body().setLinearVelocity(-3, 1.5f);
                    } else {
                        ((Player) fixA.getUserData()).getB2body().setLinearVelocity(3, 1.5f);
                    }
                }
                else{
                    ((Player) fixB.getUserData()).reduceHealth(10);
                    if(fixA.getBody().getPosition().x <  ((Player) fixB.getUserData()).getB2body().getPosition().x){
                        ((Player) fixB.getUserData()).getB2body().setLinearVelocity(-3, 1.5f);
                    }
                    else{
                        ((Player) fixB.getUserData()).getB2body().setLinearVelocity(3, 1.5f);
                    }
                }
                break;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
