package com.mygdx.game.pickups;


import com.mygdx.game.entities.EntityList;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.pickups.AbstractPickup;
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
