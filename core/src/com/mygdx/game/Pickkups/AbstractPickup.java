package com.mygdx.game.Pickkups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Shooting.AbstractBullet;
import com.mygdx.game.entities.Player;

import java.util.Random;

/**
 * Defines the general behaviour that pickups have
 * @author Josh Leeder
 * @date 13/02/19
 */

public abstract class AbstractPickup {

    /** Whether a pickup should drop or not */
    protected boolean toDrop;

    /** The random generator for pickups */
    protected static Random gen = new Random();

    /** The texture for the pickup, this will need to be changed */
    protected Texture pickupTex;

    /** The dimensions for the pickups */
    private final float WIDTH = 15;
    private final float HEIGHT = 15;


    public AbstractPickup(){
        toDrop = false;
    }


    /** The random chance that the pickup will drop */
    public boolean dropItem(){
        // 1 in 4 chance to drop the pickup
        // The next int is between 0 and n where n is exclusive
        if(gen.nextInt(4) == 3){
            toDrop = true;
        }
        return toDrop;
    }


    /** The effect the pickup will have on the player */
    public void applyPickup(Player player){
    }


    /** Rendering the pickup */
    public void render(SpriteBatch batch, Texture tex, float xpos, float ypos){
        batch.draw(tex, xpos, ypos, WIDTH, HEIGHT);
    }

    //To do here
    // Random chance for dropping an item
    // IF drop then render the item at the relevant coordinated
    // This will need to be where the enemy entity is killed, will need to use the entity list


    //How am i going to make it so when an entity dies they call this
    // Use the entity list, When entity dies call the drop item.
    // IF drop item is true, get the coords of the entity
    // Spawn the pickup at that location useing the render method

    //Then i'll worry about the collison.
    // May need to make another list to check all of the pickups for collision

}
