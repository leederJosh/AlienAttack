package com.mygdx.game.entities;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.world.AbstractLevel;
import com.mygdx.game.world.AssetHandler;

public class Boss extends Entity  {

    private static final int speed = 60; //speed on x axis
    private static final int jumpVelocity = 5;
    private Texture image;
    private float maxMovement = 5;

    public Boss(float x, float y, AbstractLevel map) {
        super(x, y, EntityType.BOSS, map);
        image = AssetHandler.getAssetHandler().getTexture("BossLeftThree.png");


    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
        batch.end();

    }

}

