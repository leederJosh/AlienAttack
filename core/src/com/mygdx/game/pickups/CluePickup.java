package com.mygdx.game.pickups;

import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.pickups.AbstractPickup;
import com.badlogic.gdx.graphics.Texture;
import static com.mygdx.game.pickups.AbstractPickup.gen;

public class CluePickup extends AbstractPickup {


    String chosenClue;

    public CluePickup(float pickupX, float pickupY){
        super(pickupX, pickupY);

        int TexOpt = ((gen.nextInt(4)+ 1));

        switch(TexOpt){
            case 1 : chosenClue = "TopLeft.png";
                break;

            case 2 : chosenClue ="TopRight.png";
                break;

            case 3 : chosenClue ="BottomLeft.png";
                break;

            case 4 : chosenClue ="BottomRight.png";
                break;
        }
        pickupTex = AssetHandler.getAssetHandler().getTexture(chosenClue);


    }


    @Override
    public void act() {
        EntityList.getEntityList().getPlayer().increaseHealth(pickupValue);
        //render

    }

}