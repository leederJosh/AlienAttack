package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.world.AssetHandler;

/**
 * A class to track the humanity of the player and change the behaviour of the game accordingly
 * @Author Josh Leeder
 * @Date 06/03/19
 */
public class Humanity {

    /** Boundaries for the humanityValue */
    private final int MAX_HUMANITY = 100;
    private final int MIN_HUMANITY = 0;

    /** Animation */
    private float stateTime;
    private static final float Animation_speed = 0.5f;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> walkLeftAnimation;
    private Animation<TextureRegion> walkRight25;
    private static int FRAME_COLS = 4;
    private static int FRAME_ROWS = 1;

    /** Humanity variables */
    private int humanityValue;
    private int playerSpeed;
    private final int defaultPlayerSpeed = 60;

    /** Textures for player and weapon */
    private Texture weaponLeft;
    private Texture walkSheet;
    private Texture walkSheetLeft;

    // 25% Alien
    private Texture player25;




    public Humanity() {
        humanityValue = 75;
        playerSpeed = 60;

        // Animation
        this.walkSheet = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterRight.png");
        this.walkSheetLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterLeft.png");

        player25 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25MainCharacter.png");


        setUpAnimations();
    }


    public void update(float deltaTime) {

        //Make sure the humanity cannot be above 100 or below 0.
        if (humanityValue < MIN_HUMANITY) {
            humanityValue = 0;
        } else if (humanityValue > MAX_HUMANITY) {
            humanityValue = 75;
        }

        stateTime += deltaTime;
        setSpeed();
        handleControls(deltaTime);
    }

    /**
     * Returns true if the player humanity is low enough to use the Alien Hand Gun
     */
    private boolean canUseAlienHandGun() {

        if (humanityValue < 50) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the player humanity is low enough to use the Alien Rifle
     */
    private boolean canUseAlienRifle() {

        if (humanityValue < 25) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Handles movement of the player based on the current humanity
     */
    public void handleControls(float deltaTime) {
        //check the humanity level

        if (humanityValue < 50) {
            //then swap the keys (punish the player for a low humanity score)
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                //move right
                EntityList.getEntityList().getPlayer().moveX(playerSpeed * deltaTime);
            }
            //move left
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                EntityList.getEntityList().getPlayer().moveX(-playerSpeed * deltaTime);
            }
        }
        //otherwise, move normally
        else {
            //moving left (negative speed so we move left)
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                EntityList.getEntityList().getPlayer().moveX(-playerSpeed * deltaTime);
            }

            //moving right (positive speed so we move right)
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                EntityList.getEntityList().getPlayer().moveX(playerSpeed * deltaTime);
            }
        }
    }


    /** GETTERS AND SETTERS BELOW */

    /**
     * Handles the speed the player can move based on their humanity
     * Also sets the players texture depending on humanity
     */
    private void setSpeed() {

        if (humanityValue > 75) {
            playerSpeed = 60;
        } else if (humanityValue >= 50 && humanityValue <= 74) {
            playerSpeed = 50;
        } else if (humanityValue < 50 && humanityValue >= 25) {
            playerSpeed = 40;
        } else if (humanityValue < 25) {
            playerSpeed = 30;
        }
    }

    public TextureRegion getAnimation(String direction){

        TextureRegion texRegion = null;

        // IF the player is moving to the right
        if(direction.equals("right")) {

            // 25% Alien texture
            if (humanityValue >= 75) {
                return new TextureRegion(walkRight25.getKeyFrame(stateTime, true));
            }
            // 50% Alien texture
            else if (humanityValue >= 50 && humanityValue <= 74) {
                return new TextureRegion(walkRight25.getKeyFrame(stateTime, true));
            }
            // 75% Alien texture
            else if (humanityValue < 50 && humanityValue >= 25) {
                return new TextureRegion(walkRight25.getKeyFrame(stateTime, true));
            }
            // 100% Alien texture
            else if (humanityValue < 25) {
                return new TextureRegion(walkRight25.getKeyFrame(stateTime, true));
            }
        }
        // IF the player is moving to the left
        else if(direction.equals("left")){

            texRegion = new TextureRegion(walkRight25.getKeyFrame(stateTime, true));

        }

        return texRegion;
    }

    //TODO
    // Need to implement different animations depending on the humanityValue
    // ALSO the correct direction needs to be given back

    public void decreaseHumanity(int amount){
        humanityValue -= amount;
    }

    public void increaseHumanity(int amount){
        humanityValue += amount;
    }

    public int getHumanityValue(){
        return humanityValue;
    }

    private void setUpAnimations(){


        // WALKING RIGHT 25% ALIEN
        // Animation - Walking right
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        // Animation for walking right
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for(int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp [i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.33f, walkFrames);


        // Walking left
        TextureRegion[][] tmp2 = TextureRegion.split(walkSheetLeft, walkSheetLeft.getWidth()/FRAME_COLS, walkSheetLeft.getHeight()/FRAME_ROWS);
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        // Animation for walking left
        int index2 = 0;
        for (int r = 0; r < FRAME_ROWS; r++) {
            for(int c = 0; c < FRAME_COLS; c++) {
                walkLeftFrames[index2++] = tmp2 [r][c];
            }
        }
        walkLeftAnimation = new Animation<TextureRegion>(0.33f, walkLeftFrames);



        // WALKING 25% ALIEN
        // Animation - Walking right
        TextureRegion[][] tmp3 = TextureRegion.split(player25, player25.getWidth()/FRAME_COLS, player25.getHeight()/FRAME_ROWS);
        TextureRegion[] player25Right = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        // Animation for walking right
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for(int j = 0; j < FRAME_COLS; j++) {
                player25Right[index3++] = tmp3 [i][j];
            }
        }
        walkRight25 = new Animation<TextureRegion>(0.33f, player25Right);

        //THE ABOVE WORKS AND LOADS BUT IT LOADS TWO MINI PEOPLE ON TOP OF EACH OTHER








        stateTime = 0f;


    }
}

