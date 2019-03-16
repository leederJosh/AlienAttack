package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.collisions.MapObjectLayers;
import com.mygdx.game.guns.*;
import com.mygdx.game.assets.AssetHandler;

public class Player extends Entity {

    /** game stats */
    private float stateTime;
    private static int FRAME_COLS = 4, FRAME_ROWS = 1;

    /** Player statistics */
    private static final int JUMP_VELOCITY = 5;

    /** Textures for player and weapon */
    private Texture
            image,
            weapon,
            weaponLeft,/*
            walkSheet,
            walkSheetLeft,*/
            playerLeftStill,
            playerLeftStill25,
            playerLeftStill50,
            playerLeftStill75,
            playerLeftStill100,
            playerRightStill,
            playerRightStill25,
            playerRightStill50,
            playerRightStill75,
            playerRightStill100;

    private SpriteBatch spriteBatch;
    private boolean wasRight;

    /** Animation */
    private static final float Animation_speed = 0.5f;
    private Animation<TextureRegion>
            walkAnimation,
            walkLeftAnimation;

    /** Humanity */
    private Humanity humanity;
    private AnimationHandler animationHandler;

    /** Gun types */
    private GunInterface currentGun;
    private GunInterface handGun;
    private GunInterface shotGun;
    private GunInterface alienHandGun;
    private GunInterface alienRifle;
    private boolean isFinished;

    private boolean isDead;

    /** max speed of player */
    private final float MAX_PLAYER_SPEED = 3f;

    public Player(float x, float y, World world) {
        super(x, y, EntityType.PLAYER, world);
        this.isDead = false;

        //Create humanity object
        humanity = new Humanity();

        // Textures
        this.image = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveRightMiddleBig.png");
        weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");
        weaponLeft = AssetHandler.getAssetHandler().getTexture("PistolLeft.png");
        //this.walkSheet = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterRight.png");
        //this.walkSheetLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterLeft.png");

        animationHandler = new AnimationHandler();

        wasRight = true;
//        this.image = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveRightMiddleBig.png");
        //      this.image = AssetHandler.getAssetHandler().getTexture("SpriteSheets/100%first-char-leftstill.png");
        playerLeftStill = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveLeftMiddleBig.png");
        playerLeftStill25 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25%first char leftstill.png");
        playerLeftStill50 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/50main-leftstill.png");
        playerLeftStill75 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/75%first-char-leftstill.png");
        playerLeftStill100 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/100%first-char-leftstill.png");

        playerRightStill = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveRightMiddleBig.png");
        playerRightStill25 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25%first-charstill.png");
        playerRightStill50 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/50main-rightstill.png");
        playerRightStill75 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/75%first-char-rightstill.png");
        playerRightStill100 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/100%first-char-rightstill.png");
/*

        // Animation - Walking right
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        // Walking left
        TextureRegion[][] tmp2 = TextureRegion.split(walkSheetLeft, walkSheetLeft.getWidth()/FRAME_COLS, walkSheetLeft.getHeight()/FRAME_ROWS);
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for(int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp [i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.33f, walkFrames);

        int index2 = 0;
        for (int r = 0; r < FRAME_ROWS; r++) {
            for(int c = 0; c < FRAME_COLS; c++) {
                walkLeftFrames[index2++] = tmp2 [r][c];
            }
        }
        walkLeftAnimation = new Animation<TextureRegion>(0.33f, walkLeftFrames); */

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        //Create a gun for the player to use by default (the Handgun)
        handGun = new HandGun();
        shotGun = new ShotGun();
        alienHandGun = new AlienHandGun();
        alienRifle = new AlienRifle();

        //Gun objects for the player
        currentGun = handGun;
        //currentGun = shotGun;
        //currentGun = alienHandGun;
        //currentGun = alienRifle;
        isFinished = false;

        // The collision box to be drawn around the player
        defineEntityBox2D(x, y);
    }


    //control space provides us with a list of all the methods we have access to
    @Override
    public void render(SpriteBatch batch) {


        batch.begin();
        //we scale the image so that it is the same size as we specified in entityType
        //current image is
    /*if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.D)) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }*/
        if (Gdx.input.isKeyPressed(Keys.A)) {
            if (this.humanity.getHumanityValue() >= 50) {
                wasRight = false;
                batch.draw(weaponLeft, pos.x - 22, pos.y + 2);
            }
            if (this.humanity.getHumanityValue() < 50) {
                wasRight = true;
                batch.draw(weapon, pos.x, pos.y + 2);
            }
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            if (this.humanity.getHumanityValue() >= 50) {
                wasRight = true;
                batch.draw(weapon, pos.x, pos.y + 2);
            } else if (this.humanity.getHumanityValue() < 50) {
                wasRight = false;
                batch.draw(weaponLeft, pos.x - 22, pos.y + 2);
            }
        }
        //player animated
        stateTime += Gdx.graphics.getDeltaTime();


        if (Gdx.input.isKeyPressed(Keys.D)) {
            batch.draw(animationHandler.getAnimation("right", this), pos.x, pos.y, getWidth(), getHeight());
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            batch.draw(animationHandler.getAnimation("left", this), pos.x, pos.y, getWidth(), getHeight());
        } else {
            if (wasRight == true) {
                if (this.humanity.getHumanityValue() >= 75) {
                    batch.draw(playerRightStill, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() >= 50 && this.humanity.getHumanityValue() <= 74) {
                    batch.draw(playerRightStill25, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() < 50 && this.humanity.getHumanityValue() >= 25) {
                    batch.draw(playerRightStill50, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() < 25) {
                    batch.draw(playerRightStill75, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() == 0) {
                    batch.draw(playerRightStill100, pos.x, pos.y, getWidth(), getHeight());
                } //TODO : BELOW IS A PROBLEM "wasRight" is always true
            } else if (wasRight == false) {
                if (this.humanity.getHumanityValue() >= 75) {
                    batch.draw(playerLeftStill, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() >= 50 && this.humanity.getHumanityValue() <= 74) {
                    batch.draw(playerLeftStill25, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() < 50 && this.humanity.getHumanityValue() >= 25) {
                    batch.draw(playerLeftStill50, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() < 25) {
                    batch.draw(playerLeftStill75, pos.x, pos.y, getWidth(), getHeight());
                } else if (this.humanity.getHumanityValue() == 0) {
                    batch.draw(playerLeftStill100, pos.x, pos.y, getWidth(), getHeight());
                }
            }
            //batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
        }
        batch.end();
    }

        @Override
    public void update(float deltaTime) {
        if (health <= 0){
            isDead = true;
        }

        // Keeps the sprite in the box
        pos.x = b2body.getPosition().x - type.getWidth() / 2;
        pos.y = b2body.getPosition().y - type.getHeight() / 2;

        // Update humanity stats
        humanity.update(deltaTime);
        animationHandler.update(deltaTime);

        //Deduct health when the player is on top of an enemy (Enemy melee)
        for (int i = 1; i < EntityList.getListEntities().size(); i++) {
            Entity e = EntityList.getListEntities().get(i);

            if ((int) pos.x == e.getx() && e instanceof Enemy && (int) pos.y == e.gety()) {
                System.out.println("playerX: " + pos.x + " entityX: " + e.getx());
                reduceHealth(10);
                System.out.println("Health (move on entity): " + health);
            }
        }

        //Make sure that the health never goes below 0 or above 100.
        if (health < MIN_HEALTH) {
            health = 0;
        }
        else if (health > MAX_HEALTH) {
            health = 100;
        }

        handleInput();

       limitInAirSpeed();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {

            currentGun = shotGun;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {

            currentGun = alienHandGun;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            currentGun = alienRifle;
        }

    }

    private void handleInput(){

        // If the player is below half humanity flip controls
        if(getHumanity().isPlayerBelowHalfHumanity() == true) {

            // Player moves left
            if (Gdx.input.isKeyPressed(Keys.D)) {
                moveRight(false);
            }

            // Player moves right
            if (Gdx.input.isKeyPressed(Keys.A)) {
                moveRight(true);
            }
        }
        // Else if player above 50 humanity (inclusive) then normal controls
        else{

            // Player moves left
            if (Gdx.input.isKeyPressed(Keys.A) && b2body.getLinearVelocity().x <= MAX_SPEED) {
                moveRight(false);
            }

            // Player moves right
            if (Gdx.input.isKeyPressed(Keys.D) && b2body.getLinearVelocity().x >= MAX_SPEED * -1) {
                moveRight(true);
            }
        }

        // Player jump (only when linear velocity is equal to 0 (This isn't great as the player can have 0 y velocity when in the ceiling)
        // Might have to try the contact listener again
        if (Gdx.input.isKeyJustPressed(Keys.W) && b2body.getLinearVelocity().y < 0.1){
            System.out.println("W");
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
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
        fixtureDef.filter.categoryBits = MapObjectLayers.PLAYER_OBJECT;
        // What other fixtures I can collide with
        fixtureDef.filter.maskBits = MapObjectLayers.FLOOR_OBJECT;
        //b2body.createFixture(fixtureDef);

        // So we can reference the player when using the contact listener
        b2body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
    }

    /** Give player a new body definition in a new given world */
    public void reDefinePlayerBox2D(float xPos, float yPos, World newWorld) {

        // Define the box2d body around player
        bdef = new BodyDef();
        bdef.position.set(xPos, yPos);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = newWorld.createBody(bdef);

        // Add the box2d body to the levels
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((type.getWidth() - type.getWidth() / 2) - 2 / scale, ((type.getHeight() - type.getHeight() / 2) - 4 / scale));

        fixtureDef.shape = shape;
        fixtureDef.friction = 0.2f;
        //What type of fixture def I am
        fixtureDef.filter.categoryBits = MapObjectLayers.PLAYER_OBJECT;
        // What other fixtures I can collide with
        fixtureDef.filter.maskBits = MapObjectLayers.FLOOR_OBJECT;
        //b2body.createFixture(fixtureDef);

        // So we can reference the player when using the contact listener
        b2body.createFixture(fixtureDef).setUserData(this);
        shape.dispose();
    }


    /** Current shooting behaviour of the player */
    public void shoot(float mouseX, float mouseY){
        currentGun.shoot(mouseX, mouseY);
    }

    private void limitInAirSpeed(){

        // To stop the player being able to travel quickly in the air
        if(b2body.getLinearVelocity().y != 0) {
            // Checks positive x velocity in air
            if (b2body.getLinearVelocity().x > MAX_PLAYER_SPEED) {
                b2body.setLinearVelocity(new Vector2(MAX_PLAYER_SPEED, b2body.getLinearVelocity().y));
            }
            // Checks negative x velocity in air
            if (b2body.getLinearVelocity().x < MAX_PLAYER_SPEED * -1) {
                b2body.setLinearVelocity(new Vector2(MAX_PLAYER_SPEED * -1, b2body.getLinearVelocity().y));
            }
        }
    }

    /** Getters and setters */

    public void setGun(GunInterface gun){
        currentGun = gun;
    }

    public boolean hasPlayerFinished() { return isFinished; }

    public void setPlayerFinished(boolean b) { isFinished = b; }

    public Humanity getHumanity(){
        return humanity;
    }

    @Override
    public float getSpeed() {
        return humanity.getSpeed();
    }

    public BodyDef getBodyDef(){
        return bdef;
    }

    public int getHealth(){ return health;}

    public void setIsDead(boolean dead){
        isDead = dead;
    }

    public boolean getIsDead(){
        return isDead;
    }

    public boolean isDead(){
        return isDead;
    }

    public void setHealth(int health){ this.health = health;}

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
