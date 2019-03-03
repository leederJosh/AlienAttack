package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Game.AlienGame;

import com.mygdx.game.Guns.AlienHandGun;
import com.mygdx.game.Guns.GunInterface;
import com.mygdx.game.Guns.HandGun;
import com.mygdx.game.Guns.ShotGun;
import com.mygdx.game.world.AbstractLevel;
import com.mygdx.game.world.AssetHandler;

public class Player extends Entity {

    public static Player playerInsance = null;

    private static final int
            SPEED = 60,
            JUMP_VELOCITY = 5,
            MAX_HUMANITY = 100,
            MIN_HUMANITY = 0;
    private Texture
            image,
            weapon,
            weaponLeft;
    private static final float Animation_speed = 0.5f;
    public static int humanity;
    //animation
    private SpriteBatch spriteBatch;
    private Texture
            walkSheet,
            walkSheetLeft;
    private float stateTime;
    private static int FRAME_COLS = 4, FRAME_ROWS = 1;
    private Animation<TextureRegion>
            walkAnimation,
            walkLeftAnimation;

    /** Gun types */
    private GunInterface currentGun;
    private GunInterface handGun;
    private GunInterface shotGun;
    private GunInterface alienHandGun;
    private boolean isFinished;

    public Texture getWeapon() {
        return weapon;
    }

    public void setWeapon(Texture weapon) {
        this.weapon = weapon;
    }

    public Texture getImage() {
        return image;
    }



    public Player(float x, float y, AbstractLevel map) {
        super(x, y, EntityType.PLAYER, map);

        //we can pass the entity type in directly since we know it is going to be a player
        //this.image = new Texture(path + "/MoveRightMiddleBig.png");
        this.image = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveRightMiddleBig.png");
        this.humanity = 100;
        //this.weapon = new Texture (path + "/Pistol.png");

        this.weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");
        //this.weaponLeft = new Texture (path + "/PistolLeft.png");
        this.weaponLeft = AssetHandler.getAssetHandler().getTexture("PistolLeft.png");

        //this.walkSheet = new Texture (path + "/SpriteSheets/MainCharacterRight.png");
        this.walkSheet = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterRight.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for(int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp [i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.33f, walkFrames);

        //this.walkSheetLeft = new Texture (path + "/SpriteSheets/MainCharacterLeft.png");
        this.walkSheetLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterLeft.png");
        TextureRegion[][] tmp2 = TextureRegion.split(walkSheetLeft, walkSheetLeft.getWidth()/FRAME_COLS, walkSheetLeft.getHeight()/FRAME_ROWS);
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index2 = 0;
        for (int r = 0; r < FRAME_ROWS; r++) {
            for(int c = 0; c < FRAME_COLS; c++) {
                walkLeftFrames[index2++] = tmp2 [r][c];
            }
        }
        walkLeftAnimation = new Animation<TextureRegion>(0.33f, walkLeftFrames);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        //Create a gun for the player to use by default (the Handgun)
        handGun = new HandGun();
        shotGun = new ShotGun();
        alienHandGun = new AlienHandGun();
        currentGun = handGun;
        //currentGun = shotGun;
        //currentGun = alienHandGun;
        isFinished = false;
    }

    @Override
    public void update(float deltaTime, float gravity) {
        //jumping controls, (velocity takes them up gravity brings them back)
        if(Gdx.input.isKeyPressed(Keys.W) && grounded) {
            this.velocityY += JUMP_VELOCITY * getWeight();
        }
        //if they are holding the space bar whilst jumping they will jump higher
        //multiply by deltatime so they dont jump too high
        else if (Gdx.input.isKeyPressed(Keys.W) && !grounded && this.velocityY > 0) {
            this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
        }

        //this will apply the gravity
        super.update(deltaTime, gravity);

        //check the humanity level
        if (humanity < 90) {
            //then swap the keys (punish the player for a low humanity score)
            if (Gdx.input.isKeyPressed(Keys.A)) {
                //move right
                moveX(SPEED * deltaTime);
            }
            //move left
            if (Gdx.input.isKeyPressed(Keys.D)) {
                moveX(-SPEED * deltaTime);
            }
        }
        //otherwise, move normally
        else {
            //moving left (negative speed so we move left)
            if (Gdx.input.isKeyPressed(Keys.A)) {
                moveX(-SPEED * deltaTime);
            }

            //moving right (positive speed so we move right)
            if (Gdx.input.isKeyPressed(Keys.D)) {
                moveX(SPEED * deltaTime);
            }
        }

        //Deduct health when the player is on top of an enemy
        //Iterate through the EntityList
        //TODO: Reduce health when player goes into the enemy's sprite boundary (currently only on left side)
        for (int i = 1; i < EntityList.getListEntities().size(); i++) {
            Entity e = EntityList.getListEntities().get(i);

            //System.out.println("playerX: " + pos.x + " entityX: " + e.getx());

            if ((int) pos.x == e.getx() && e instanceof Enemy && (int) pos.y == e.gety()) {
                System.out.println("playerX: " + pos.x + " entityX: " + e.getx());
                reduceHealth(10);
                System.out.println("Health (move on entity): " + health);
            }
        }

        //Make sure the humanity cannot be above 100 or below 0.
        if (humanity < MIN_HUMANITY) {
            humanity = 0;
        }
        else if (humanity > MAX_HUMANITY) {
            humanity = 100;
        }
        
        //Check to see if the player has finished the level
        if (pos.x > 500) {
            isFinished = true;
        }
    }

    //control space provides us with a list of all the methods we have access to
    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());

        //we scale the image so that it is the same size as we specified in entityType
        //current image is
        if (Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.D)) {
            batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
        } else {
            if (Gdx.input.isKeyPressed(Keys.D)) {
                batch.draw(weapon, pos.x, pos.y);
            }
            if (Gdx.input.isKeyPressed(Keys.A)) {
                batch.draw(weaponLeft, pos.x - 22, pos.y + 2);
            }
            //player animated
            stateTime += Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Keys.D)) {
                TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, pos.x, pos.y, getWidth(), getHeight());
            }
            if (Gdx.input.isKeyPressed(Keys.A)) {
                TextureRegion currentFrame2 = walkLeftAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame2, pos.x, pos.y, getWidth(), getHeight());
            }
        }
        batch.end();
    }

    public static int getSpeed() {
        return SPEED;
    }

    public static int getJumpVelocity() {
        return JUMP_VELOCITY;
    }

    /** Current shooting behaviour of the player */
    public void shoot(float mouseX, float mouseY){
        currentGun.shoot(mouseX, mouseY);
    }

    /** Set the gun to be used by the player */
    public void setGun(GunInterface gun){
        currentGun = gun;
    }

    public int getHumanity() {
        return humanity;
    }

    public void decreaseHumanity(int amount) {
        humanity -= amount;
    }

    public void increaseHumanity(int amount) {
        humanity += amount;
    }

    public boolean hasPlayerFinished() { return isFinished; }

    public void setPlayerFinished(boolean b) { isFinished = b; }

    public void setPlayerMap(AbstractLevel abstractLevel){
        map = abstractLevel;
    }

}