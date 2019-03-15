package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.collisions.WorldFilterBits;
import world.DialogNode;
import java.util.ArrayList;

public class Friendly extends Entity {

    private Texture image;

    //Happy dialog tree.
    private ArrayList<DialogNode<CharSequence>> happyDialog;

    //Dialog tree for when a friendly is hit.
    private ArrayList<DialogNode<CharSequence>> isHitDialog;
    private int dialogIndex;

    private AnimationHandler animationHandler;


    public Friendly(float x, float y, World world) {
        super(x, y, EntityType.FRIENDLY, world);
        this.image = AssetHandler.getAssetHandler().getTexture("civilianLeftFace.png");

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
        isHitDialog.get(0).addChild(isHitDialog.get(1));

        animationHandler = new AnimationHandler();

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
        shape.setAsBox((type.getWidth() - type.getWidth() / 2) - 2 / scale, ((type.getHeight() - type.getHeight() / 2) - 4 / scale));

        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        //What type of fixture def I am
        fixtureDef.filter.categoryBits = WorldFilterBits.ENTITY_OBJECT;
        // What other fixtures I can collide with
        fixtureDef.filter.maskBits = WorldFilterBits.COLLIDABLE_OBJECT;
        b2body.createFixture(fixtureDef);

        // So we can reference the player when using the contact listener
        b2body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
    }


    /** Getters and Setters */

    /**
     * Returns the current node in the 'is hit' dialog tree.
     * @return
     */
    public DialogNode getHitDialog() {
        DialogNode output = isHitDialog.get(dialogIndex);
        return output;
    }

    @Override
    public void reduceHealth(int amount) {
        super.reduceHealth(amount);
        if (dialogIndex < isHitDialog.size() - 1) {
            dialogIndex++;
        }
    }

    @Override
    public float getSpeed() {
        return speed;
    }
}