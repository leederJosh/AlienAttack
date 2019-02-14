package com.mygdx.game.Pickkups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Player;

/**
 * Health pickups that are dropped randomly on an ememy dying
 * @author Josh Leeder
 * @date 14/02/19
 */

public class HealthPickup extends AbstractPickup{

    private int healthValue;


    public HealthPickup(float pickupX, float pickupY){
        super(pickupX, pickupY);
        pickupTex = new Texture("assets/Healthico.png");
        healthValue = 25;
    }

    //Does this need an override?
    // Not sure this is going to work
    public void applyPickup(Player player){
        player.increaseHealth(healthValue);
    }
}
