package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Guns.*;
import com.mygdx.game.world.AbstractLevel;
import com.mygdx.game.world.AssetHandler;

public class Player extends Entity {

    /** Game stats */
    private float stateTime;
    private static int FRAME_COLS = 4, FRAME_ROWS = 1;

    /** Player statistics */
    private static final int
            SPEED = 60,
            JUMP_VELOCITY = 5,
            MAX_HUMANITY = 100,
            MIN_HUMANITY = 0;

    /** Textures for player and weapon */
    private Texture
            image,
            weapon,
            weaponLeft,
            walkSheet,
            walkSheetLeft;
    private SpriteBatch spriteBatch;

    /** Animation */
    private static final float Animation_speed = 0.5f;
    private Animation<TextureRegion>
            walkAnimation,
            walkLeftAnimation;

    /** Humanity */
    //public static int humanity;
    private Humanity humanity;

    /** Gun types */
    private GunInterface currentGun;
    private GunInterface handGun;
    private GunInterface shotGun;
    private GunInterface alienHandGun;
    private GunInterface alienRifle;
    private boolean isFinished;


    public Player(float x, float y, AbstractLevel map) {
        super(x, y, EntityType.PLAYER, map);

        //Create humanity object
        humanity = new Humanity();

        this.image = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MoveRightMiddleBig.png");
        //this.humanity = 100;

        // Textures
        this.weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");
        this.weaponLeft = AssetHandler.getAssetHandler().getTexture("PistolLeft.png");
        this.walkSheet = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterRight.png");
        this.walkSheetLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterLeft.png");

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
        walkLeftAnimation = new Animation<TextureRegion>(0.33f, walkLeftFrames);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;

        //Create a gun for the player to use by default (the Handgun)
        handGun = new HandGun();
        shotGun = new ShotGun();
        alienHandGun = new AlienHandGun();
        alienRifle = new AlienRifle();

        //currentGun = handGun;
        //currentGun = shotGun;
        currentGun = alienHandGun;
        //currentGun = alienRifle;
        isFinished = false;
    }

    @Override
    public void update(float deltaTime, float gravity) {

        humanity.update(deltaTime);
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



        //Deduct health when the player is on top of an enemy (Enemy melee)
        for (int i = 1; i < EntityList.getListEntities().size(); i++) {
            Entity e = EntityList.getListEntities().get(i);

            if ((int) pos.x == e.getx() && e instanceof Enemy && (int) pos.y == e.gety()) {
                System.out.println("playerX: " + pos.x + " entityX: " + e.getx());
                reduceHealth(10);
                System.out.println("Health (move on entity): " + health);
            }
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
                //TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
                batch.draw(humanity.getAnimation("right"), pos.x, pos.y, getWidth(), getHeight());
            }
            if (Gdx.input.isKeyPressed(Keys.A)) {
                //TextureRegion currentFrame2 = walkLeftAnimation.getKeyFrame(stateTime, true);
                batch.draw(humanity.getAnimation("left"), pos.x, pos.y, getWidth(), getHeight());
            }
        }
        batch.end();
    }

    /** Current shooting behaviour of the player */
    public void shoot(float mouseX, float mouseY){
        currentGun.shoot(mouseX, mouseY);
    }

    public static int getSpeed() {
        return SPEED;
    }

    public static int getJumpVelocity() {
        return JUMP_VELOCITY;
    }

    public void setGun(GunInterface gun){
        currentGun = gun;
    }

//    public int getHumanity() {
//        return humanity;
//    }
//
//    public void decreaseHumanity(int amount) {
//        humanity -= amount;
//    }
//
//    public void increaseHumanity(int amount) {
//        humanity += amount;
//    }

    public boolean hasPlayerFinished() { return isFinished; }

    public void setPlayerFinished(boolean b) { isFinished = b; }

    public void setPlayerMap(AbstractLevel abstractLevel){
        map = abstractLevel;
    }

    public Texture getWeapon() {
        return weapon;
    }

    public void setWeapon(Texture weapon) {
        this.weapon = weapon;
    }

    public Texture getImage() {
        return image;
    }

    public Humanity getHumanity(){
        return humanity;
    }

    public void setPlayerTexture(Texture texture){
        image = texture;
    }

}

//TODO
// HUMANITY
// WHAT IS HUMANITY GOING TO DO?
// AFFECT DAMAGE OUTPUT
// WHAT GUNS CAN BE USED
// SPEED OF PLAYER
// HEALTH OF PLAYER
// MUSIC FOR EACH LEVEL
// FINISH GUNS