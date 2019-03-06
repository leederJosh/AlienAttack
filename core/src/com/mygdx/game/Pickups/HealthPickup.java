package com.mygdx.game.Pickups;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Pickups.AbstractPickup;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;
import com.mygdx.game.world.AssetHandler;

/**
 * Health pickups that are dropped randomly on an ememy dying
 * @author Josh Leeder
 * @date 14/02/19
 */

public class HealthPickup extends AbstractPickup {



    public HealthPickup(float pickupX, float pickupY){
        super(pickupX, pickupY);
        //pickupTex = new Texture("assets/Healthico.png");
        pickupTex = AssetHandler.getAssetHandler().getTexture("Healthico.png");
        pickupValue = 25;
    }

    @Override
    public void act() {
        EntityList.getEntityList().getPlayer().increaseHealth(pickupValue);
    }

}
