package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.collisions.MapObjectLayers;


public class Boss extends Entity  {

    //speed on x axis
    private static final int speed = 60;
    private AnimationHandler animationHandler;

    public Boss(float x, float y, World world) {
        super(x, y, EntityType.BOSS, world);
        image = AssetHandler.getAssetHandler().getTexture("BossLeftThree.png");
        animationHandler = new AnimationHandler();

        health = 300;

        defineEntityBox2D(x,y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());

        if (moveRight == true) {
            batch.draw(animationHandler.getAiAnimation("right", this), pos.x, pos.y, getWidth(), getHeight());
        }
        if (moveRight == false) {
            batch.draw(animationHandler.getAiAnimation("left", this), pos.x, pos.y, getWidth(), getHeight());
        }
        animationHandler.update(Gdx.graphics.getDeltaTime());

        batch.end();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // Keeps the sprite in the box
        pos.x = b2body.getPosition().x - type.getWidth() / 2;
        pos.y = b2body.getPosition().y - type.getHeight() / 2;
    }

    /**
     * Creates a body for the player in the levels (Box2D)
     */
    @Override
    public void defineEntityBox2D(float xPos, float yPos) {

        // Define the box2d body around player
        bdef = new BodyDef();
        bdef.position.set(xPos, yPos);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        // Add the box2d body to the levels
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((type.getWidth() - type.getWidth() / 2) - 11 / scale, ((type.getHeight() - type.getHeight() / 2) - 4 / scale));

        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        //What type of fixture def I am
        fixtureDef.filter.categoryBits = MapObjectLayers.ENTITY_OBJECT;
        // What other fixtures I can collide with
        fixtureDef.filter.maskBits = MapObjectLayers.FLOOR_OBJECT | MapObjectLayers.BOUNDARY_OBJECT;

        // So we can reference the player when using the contact listener
        b2body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
    }

    @Override
    public float getSpeed() {
        return speed;
    }


    @Override
    public void reverseMovement(){
        if(moveRight){
            moveRight = false;
        }
        else{
            moveRight = true;
        }
    }
}

