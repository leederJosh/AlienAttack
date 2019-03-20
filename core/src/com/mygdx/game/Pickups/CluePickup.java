package com.mygdx.game.pickups;


import com.mygdx.game.entities.EntityList;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.pickups.AbstractPickup;

public class CluePickup extends AbstractPickup {

    public CluePickup(float pickupX, float pickupY){
        super(pickupX, pickupY);

        pickupTex = AssetHandler.getAssetHandler().getTexture("FullClue.png");
        pickupValue = 25;

    }

    @Override
    public void act() {
        EntityList.getEntityList().getPlayer().increaseHealth(pickupValue);
    }

}
