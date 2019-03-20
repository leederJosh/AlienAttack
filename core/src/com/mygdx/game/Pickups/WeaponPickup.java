package com.mygdx.game.pickups;


import com.mygdx.game.entities.EntityList;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.pickups.AbstractPickup;
/**
 * Health pickups that are dropped randomly on an ememy dying
 * @author Josh Leeder
 * @date 14/02/19
 */

public class WeaponPickup extends AbstractPickup {


    public WeaponPickup(float pickupX, float pickupY) {
        super(pickupX, pickupY);
        pickupValue = 1;
        pickupTex = AssetHandler.getAssetHandler().getTexture("Pistol.png");

    }

        public int WeaponChooser() {
            String chosenWeapon = "Pistol.png";

            int weapOpt = ((gen.nextInt(3)+1));

            if (weapOpt == 1) {

                chosenWeapon = "SMG.png";
            }
            if (weapOpt == 2) {

                chosenWeapon = "SMG.png";
            }
            if (weapOpt == 3) {

                chosenWeapon = "SMG.png";
            }


            pickupTex = AssetHandler.getAssetHandler().getTexture(chosenWeapon);
            return weapOpt;
        }


    @Override
    public void act() {
        EntityList.getEntityList().getPlayer().increaseHealth(pickupValue);
        //render

    }

}
