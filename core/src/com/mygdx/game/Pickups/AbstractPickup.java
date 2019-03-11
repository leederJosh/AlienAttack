package com.mygdx.game.pickups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.game.AlienGame;

import java.util.Random;

/**
 * Defines the general behaviour that pickups have
 * @author Josh Leeder
 * @date 13/02/19
 */

public abstract class AbstractPickup {

    /** The random generator for pickups */
    protected static Random gen = new Random();

    /** The texture for the pickup, this will need to be changed */
    protected Texture pickupTex;

    /** The dimensions for the pickups */
    private final float WIDTH = 15 / AlienGame.ppm;
    private final float HEIGHT = 15 / AlienGame.ppm;

    /** The x coordinates of the pickup */
    private float pickupX;

    /** The y coordinates of the pickup */
    private float pickupY;

    /** The value the pickup will add to the stats of the player (be it health or humanity) */
    protected int pickupValue;



    public AbstractPickup(float pickupX, float pickupY){
        this.pickupX = pickupX;
        this.pickupY = pickupY;
    }

    /**
     * Draws the pickup to the screen
     * @param batch
     * @param posX
     * @param posY
     */
    public void render(SpriteBatch batch, float posX, float posY){
        batch.begin();
        batch.draw(pickupTex, posX, posY, WIDTH, HEIGHT);
        batch.end();
    }

    /** The effect the pickup will have on the player */
    public abstract void act();

    /**
     * Return the texture a given pickup is using
     * @return pickupTex
     */
    public Texture getPickupTex(){
        return pickupTex;
    }

    public float getWIDTH(){
        return WIDTH;
    }

    public float getHEIGHT(){
        return HEIGHT;
    }

    public float getPickupX(){
        return pickupX;
    }

    public float getPickupY(){
        return pickupY;
    }

    public void setPickupValue(int newValue){
        pickupValue = newValue;
    }
}
