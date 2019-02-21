<<<<<<< HEAD
package com.mygdx.game.entities;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AlienGame;

import world.GameMap;

public class Enemy extends Entity {

	private static final int speed = 60;
	//speed on x axis
	private static final int jumpVelocity = 5;
	Texture image;
	private float maxXMovement = 5;
	
	public Enemy(float x, float y, GameMap map) {
		super(x, y, EntityType.ENEMY, map);
		//we can pass the entity type in directly since we know it is going to be a player
		String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		image = new Texture(path + "/AlienLeftFace.png");
	}

	//control space provides us with a list of all the methods we have access to
		@Override
		public void render(SpriteBatch batch) {
			batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
			//we scale the image so that it is the same size as we specified in entityType
			//current image is
		}
		

}
=======
package com.mygdx.game.entities;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AlienGame;

import world.GameMap;

public class Enemy extends Entity {

	private static final int speed = 60;
	//speed on x axis
	private static final int jumpVelocity = 5;
	Texture image;
	private float maxXMovement = 5;
	
	public Enemy(float x, float y, GameMap map) {
		super(x, y, EntityType.ENEMY, map);
		//we can pass the entity type in directly since we know it is going to be a player
		String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		image = new Texture(path + "/AlienLeftFace.png");
	}

	//control space provides us with a list of all the methods we have access to
		@Override
		public void render(SpriteBatch batch) {
			batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
			//we scale the image so that it is the same size as we specified in entityType
			//current image is
		}
		

}
>>>>>>> dd976ff4a39baa7a1f78997f93df7140588716c3
