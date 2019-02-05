package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AlienGame;

import world.GameMap;

public class Friendly extends Entity {
	
	private Texture image;

	public Friendly(float x, float y, GameMap map) {
		super(x, y, EntityType.FRIENDLY, map);
		String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.image = new Texture(path + "/civilianLeftFace.png");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
		//we scale the image so that it is the same size as we specified in entityType
		//current image is
	}

}
