package com.mygdx.game.collisions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;

public class MyRayCaster implements RayCastCallback {

    private boolean hasHitSomething;

    private Vector2 collisionPoint;
    private Vector2 normalPoint;



    public MyRayCaster(){
        // How far the ray will extend?
        collisionPoint = new Vector2();
        // The normal of the ray cast
        normalPoint = new Vector2();
    }



    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
        collisionPoint.set(point);
        normalPoint.set(normal);

        System.out.println("I am running");
        if(fraction == 1){
            hasHitSomething = true;
            System.out.println("Ray caster has hit something");
        }
        return 0;
    }

    public boolean isHasHitSomething(){
        return hasHitSomething;
    }
}
