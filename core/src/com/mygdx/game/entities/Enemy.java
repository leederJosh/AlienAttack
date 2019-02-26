package com.mygdx.game.entities;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game.AlienGame;

import com.mygdx.game.world.AssetHandler;
import com.mygdx.game.world.GameMap;
import world.DialogNode;
//import world.GameMap;

public class Enemy extends Entity {

    private static final int speed = 60;
    //speed on x axis
    private static final int jumpVelocity = 5;
    private float maxXMovement = 5;
    //Happy dialog tree.
    private ArrayList<DialogNode<CharSequence>> dialog;
    private int dialogIndex;

    public Enemy(float x, float y, GameMap map) {
        super(x, y, EntityType.ENEMY, map);
        //we can pass the entity type in directly since we know it is going to be a player
        String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
        //image = new Texture(path + "/AlienLeftFace.png");
        image = AssetHandler.getAssetHandler().getTexture("AlienLeftFace.png");
        dialog = new ArrayList<DialogNode<CharSequence>>();
        dialog.add(new DialogNode<CharSequence>("Blorg"));
        dialog.add(new DialogNode<CharSequence>("Reeeee"));
        dialog.add(new DialogNode<CharSequence>("Yeet"));
        dialog.get(0).addChild(dialog.get(1));
        dialog.get(1).addChild(dialog.get(2));
        dialogIndex = 0;
    }

    //control space provides us with a list of all the methods we have access to
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
        //we scale the image so that it is the same size as we specified in entityType
        //current image is
    }

    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount);
        if (dialogIndex < dialog.size() - 1) {
            dialogIndex++;
        }
    }

    /**
     * Returns the current node in the 'is hit' dialog tree.
     * @return
     */
    public DialogNode getDialog() {
        DialogNode output = dialog.get(dialogIndex);
        return output;
    }
}