package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.assets.AssetHandler;

public class AnimationHandler {
/*  NATHAN'S ANIMATION HANDLER DON'T TOUCH, NEED TO SORT WEAPONS*/
    private float stateTime;
    private static final float Animation_speed = 0.5f;
    private Animation<TextureRegion> walkAnimation;

    private static int FRAME_COLS = 4;
    private static int FRAME_ROWS = 1;

    // 25% Alien (Player changing)
    private Texture
            walkSheet,
            walkSheetLeft,
            player25,
            playerLeft25,
            player50,
            playerLeft50,
            player75,
            playerLeft75,
            player100,
            playerLeft100,
            friendlyRight,
            friendlyLeft,
            enemyRight,
            enemyLeft,
            bossRight,
            bossLeft;

    public AnimationHandler()

    {
        // Animation
        walkSheet = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25%first char right.png");
        walkSheetLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/MainCharacterLeft.png");

        player25 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25%first char right.png");
        playerLeft25 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/25%first char left.png");
        player50 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/50%first char right.png");
        playerLeft50 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/50% MainCharacter left.png");
        player75 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/75%first char right.png");
        playerLeft75 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/75%first char left.png");
        player100 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/100%first char right.png");
        playerLeft100 = AssetHandler.getAssetHandler().getTexture("SpriteSheets/100%first char left.png");

        friendlyRight = AssetHandler.getAssetHandler().getTexture("SpriteSheets/CivilianRight.png");
        friendlyLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/CivilianLeft.png");

        enemyRight = AssetHandler.getAssetHandler().getTexture("SpriteSheets/AlienFacingRight.png");
        enemyLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/AlienFacingLeft.png");

        bossRight = AssetHandler.getAssetHandler().getTexture("SpriteSheets/BossFacingRight.png");
        bossLeft = AssetHandler.getAssetHandler().getTexture("SpriteSheets/BossFacingLeft.png");
        stateTime = 0f;
    }


    public Animation setUpAnimations(Texture textureSet){
        // Animation - Walking right
        TextureRegion[][] tmp = TextureRegion.split(textureSet, textureSet.getWidth()/FRAME_COLS, textureSet.getHeight()/FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        // Animation for walking right
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for(int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index] = tmp [i][j];
                index++;
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.33f, walkFrames);
        return walkAnimation;
    }


    public TextureRegion getAnimation(String direction, Player player){
        TextureRegion texRegion = null;
        // If the player is moving to the right
        if (direction.equals("right")) {
            FRAME_COLS = 4;
            // Main character normal
            if (player.getHumanity().getHumanityValue() >= 75) {
                setUpAnimations(walkSheet);
                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));

            }
            // Main character 25%
            else if (player.getHumanity().getHumanityValue() >= 50 && player.getHumanity().getHumanityValue() <= 74) {
                setUpAnimations(player25);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            // Main character 50%
            else if (player.getHumanity().getHumanityValue() < 50 && player.getHumanity().getHumanityValue() >= 25) {
                setUpAnimations(playerLeft50);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            // Main character 75%
            else if (player.getHumanity().getHumanityValue() < 25) {
                setUpAnimations(playerLeft75);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            //Main character 100%
            else if (player.getHumanity().getHumanityValue() == 0) {
                setUpAnimations(playerLeft100);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
        }
        // IF the player is moving to the lefta
        else if (direction.equals("left")) {
            FRAME_COLS = 4;
            // 25% Alien texture
            if (player.getHumanity().getHumanityValue() >= 75) {
                setUpAnimations(walkSheetLeft);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            // 50% Alien texture
            else if (player.getHumanity().getHumanityValue() >= 50 && player.getHumanity().getHumanityValue() <= 74) {
                setUpAnimations(playerLeft25);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            // 75% Alien texture
            else if (player.getHumanity().getHumanityValue() < 50 && player.getHumanity().getHumanityValue() >= 25) {
                setUpAnimations(player50);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            // 100% Alien texture
            else if (player.getHumanity().getHumanityValue() < 25) {
                setUpAnimations(player75);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            } else if (player.getHumanity().getHumanityValue() == 0) {
                setUpAnimations(player100);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
        }

        return texRegion;
    }

    public TextureRegion getAiAnimation(String direction, Entity entity){
        TextureRegion aiTexRegion = null;
        if (entity instanceof Friendly) {
            FRAME_COLS = 4;
            if (direction.equals("right")) {
                setUpAnimations(friendlyRight);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            if (direction.equals("left")) {
                setUpAnimations(friendlyLeft);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
        }
        if (entity instanceof Enemy){
            FRAME_COLS = 3;
            if (direction.equals("right")){
                setUpAnimations(enemyRight);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            if (direction.equals("left")){
                setUpAnimations(enemyLeft);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
        }
        if (entity instanceof Boss){
            FRAME_COLS = 4;
            if (direction.equals("right")){
                setUpAnimations(bossRight);
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
            if (direction.equals("left")){
                setUpAnimations((bossLeft));
                return new TextureRegion(walkAnimation.getKeyFrame(stateTime, true));
            }
        }
        return aiTexRegion;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }
}


//TODO
//setupAnimations will create the animations from the textures
//getAnimation will get the relevant animation for the relevant entity

