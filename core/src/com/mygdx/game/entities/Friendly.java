package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.AlienGame;

import com.mygdx.game.world.AssetHandler;
import com.mygdx.game.world.GameMap;
import world.DialogNode;
//import world.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class Friendly extends Entity {


    private Texture image;
    //Happy dialog tree.
    private ArrayList<DialogNode<CharSequence>> happyDialog;
    //Dialog tree for when a friendly is hit.
    private ArrayList<DialogNode<CharSequence>> isHitDialog;
    private int dialogIndex;


    public Friendly(float x, float y, GameMap map) {
        super(x, y, EntityType.FRIENDLY, map);
        String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
        //this.image = new Texture(path + "/civilianLeftFace.png");
        this.image = AssetHandler.getAssetHandler().getTexture("civilianLeftFace.png");
        //Give the friendly lower health (testing).
        //TODO: Change health back to normal amount.
        this.health = 20;
        //TODO: Add a dialog tree traverser.
        dialogIndex = 0;

        //Initialise and assign nodes to the happy dialog tree.
        happyDialog = new ArrayList<DialogNode<CharSequence>>();
        happyDialog.add(new DialogNode<CharSequence>("Hello!"));
        happyDialog.add(new DialogNode<CharSequence>("Have a good day!"));
        happyDialog.get(0).addChild(happyDialog.get(1));
        happyDialog.add(new DialogNode<CharSequence>("Oh..."));
        happyDialog.get(0).addChild(happyDialog.get(2));

        //Initialise and assign nodes to the isHit dialog tree.
        isHitDialog = new ArrayList<DialogNode<CharSequence>>();
        isHitDialog.add(new DialogNode<CharSequence>("Don't shoot!"));
        isHitDialog.add(new DialogNode<CharSequence>("Why did you shoot me?"));
        isHitDialog.add(new DialogNode<CharSequence>("Goodbye cruel world"));
        isHitDialog.get(0).addChild(isHitDialog.get(1));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
        //we scale the image so that it is the same size as we specified in entityType
        //current image is
    }

    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount);
        if (dialogIndex < isHitDialog.size() - 1) {
            dialogIndex++;
        }
    }

    /**
     * Returns the current node in the 'is hit' dialog tree.
     * @return
     */
    public DialogNode getHitDialog() {
        DialogNode output = isHitDialog.get(dialogIndex);
        return output;
    }
}