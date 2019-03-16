package com.mygdx.game.collisions;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entities.Entity;


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

            case MapObjectLayers.ENTITY_OBJECT | MapObjectLayers.BOUNDARY_OBJECT:
                if (fixA.getFilterData().categoryBits == MapObjectLayers.ENTITY_OBJECT)
                    ((Entity) fixA.getUserData()).reverseMovement();
                else
                    ((Entity) fixB.getUserData()).reverseMovement();
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
