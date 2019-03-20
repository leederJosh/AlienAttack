package com.mygdx.game.pickups;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.pickups.AbstractPickup;
import com.mygdx.game.pickups.PickupHandler;
import com.mygdx.game.levels.AbstractLevel;
/**
 * Health pickups that are dropped randomly on an ememy dying
 * @author Josh Leeder
 * @date 14/02/19
 */

public class WeaponPickup extends AbstractPickup {
    protected PickupHandler pickupHandler;

    public WeaponPickup(float pickupX, float pickupY) {
        super(pickupX, pickupY);
        pickupValue = 1;
        pickupTex = AssetHandler.getAssetHandler().getTexture("Pistol.png");

    }

        public int WeaponChooser() {


                String chosenWeapon = "SMG.png";

                int weapOpt = ((gen.nextInt(2)));

                if (weapOpt == 0) {

                    chosenWeapon = "SMG.png";
                }
                if (weapOpt == 1) {

                    chosenWeapon = "AlienPistol.png";
                }
                if (weapOpt == 2) {

                    chosenWeapon = "AlienShotgun.png";
                }


                pickupTex = AssetHandler.getAssetHandler().getTexture(chosenWeapon);

            return weapOpt;
        }

        public int WeaponIntChooser(){
            int weapondecision = 1;
            if (pickupHandler.pickupToDrop() == 2 && pickupHandler.hasCollided && WeaponChooser() == 0){
                weapondecision = 1;
            }
            return weapondecision;
        }

    public void render (OrthographicCamera camera, SpriteBatch batch) {

    }
    @Override
    public void act() {
        EntityList.getEntityList().getPlayer().increaseHealth(pickupValue);
        //render

    }

}


