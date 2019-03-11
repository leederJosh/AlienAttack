package com.mygdx.game.collisions;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Detects contact between the player Body and static map objects
 * @Author Josh Leeder
 * @Date 10/03/19
 */
public class MyContactListener implements ContactListener {


    private int count = 0;
    @Override
    public void beginContact(Contact contact) {

        Fixture firstFixture = contact.getFixtureA();
        Fixture secondFixture = contact.getFixtureB();

        //System.out.println("Fixture one" + firstFixture.getUserData().toString()); // This throws a null pointer

        System.out.println("Fixture two" + secondFixture.toString()); // This one works

        // Checks the fixtures are not null
        if(firstFixture == null || secondFixture == null){
            System.out.println("one");
            return;
        }
        if(firstFixture.getUserData() == null || secondFixture.getUserData() == null){
            System.out.println(count);
            count++;
            return;
        }

        System.out.println("A collision has happened");



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
