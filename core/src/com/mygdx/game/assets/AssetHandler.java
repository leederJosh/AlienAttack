package com.mygdx.game.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.PrefixFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.File;

/**
 * A wrapper assetHandler class around the LibGdx AssetManager
 * This will be a singleeton as we only need one assetHandler
 * @Author(s) Nathan Bachelor, Josh Leeder
 * @Date 26/02/19
 */
public class AssetHandler {

    /** Instance of the AssetHandler */
    public static AssetHandler assetHandler;
    /** LibGDX asset Manager */
    private AssetManager assetManager;
    /** The pathway to point to the assets folder */
    public static final File path = new File("");
    /** Loads the levels (tile maps) */
    private TmxMapLoader mapLoader;
    /** Path to the assets */
    private String assetPath = "core/assets/"; // This seems to work for pistol
    /** Loader for bitmap fonts */
    private BitmapFontLoader bitMapLoader;
    /** Handgun sound file */
    Sound sound;

    private AssetHandler(){
        // Create the asset Manager
        assetManager = new AssetManager(new PrefixFileHandleResolver(new InternalFileHandleResolver(), path.toString()));
        bitMapLoader = new BitmapFontLoader(assetManager.getFileHandleResolver());
        assetManager.setErrorListener(new AssetErrorListener() {
            @Override
            public void error(AssetDescriptor asset, Throwable throwable) {

            }
        });

        //Create the level loader
        mapLoader = new TmxMapLoader();
        loadAllAssets();
    }

    /**
     * Get the assetHandler object
     * @return assetHandler
     */
    public static AssetHandler getAssetHandler(){
        if(assetHandler == null){
            assetHandler = new AssetHandler();
        }
        return assetHandler;
    }

    /**
     * Loads in the majority of the assets into the assetManager
     */
    public void loadAllAssets() {

        /** Textures */
        assetManager.load(assetPath + "bullet.png", Texture.class);
        assetManager.load(assetPath + "Pistol.png", Texture.class);
        assetManager.load(assetPath + "alienred.jpg", Texture.class);
        assetManager.load(assetPath + "logonew.png", Texture.class);
        assetManager.load(assetPath + "alienred.jpg", Texture.class);
        assetManager.load(assetPath + "keys.png", Texture.class);
        assetManager.load(assetPath + "Healthico.png", Texture.class);
        assetManager.load(assetPath + "PistolLeft.png", Texture.class);
        assetManager.load(assetPath + "civilianLeftFace.png", Texture.class);
        assetManager.load(assetPath + "AlienLeftFace.png", Texture.class);
        assetManager.load(assetPath + "gameOver.png", Texture.class);


        //Boss
        assetManager.load(assetPath + "BossLeftThree.png", Texture.class);

        //Player animation textures
        assetManager.load(assetPath + "SpriteSheets/MoveRightMiddleBig.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterRight.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterLeft.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/25MainCharacter.png", Texture.class);


        //Player animation textures transferred:
        //Player animation textures
        assetManager.load(assetPath + "SpriteSheets/MoveRightMiddleBig.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MoveLeftMiddleBig.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterRight.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterLeft.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/25%first char right.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/25%first char left.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/50%first char right.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/50% MainCharacter left.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/75%first char right.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/75%first char left.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/100%first char right.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/100%first char left.png", Texture.class);

//player still assets
        assetManager.load(assetPath + "SpriteSheets/25%first-charstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/25%first char leftstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/50main-leftstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/50main-rightstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/75%first-char-leftstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/75%first-char-rightstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/100%first-char-leftstill.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/100%first-char-rightstill.png", Texture.class);


//Alien animation textures
        assetManager.load(assetPath + "SpriteSheets/AlienFacingLeft.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/AlienFacingRight.png", Texture.class);

//Civilian animation textures
        assetManager.load(assetPath + "SpriteSheets/CivilianLeft.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/CivilianRight.png", Texture.class);

//boss animation textures
        assetManager.load(assetPath + "SpriteSheets/BossFacingLeft.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/BossFacingRight.png", Texture.class);



        /** Texture Atlas */
        assetManager.load(assetPath + "uiskin.atlas", TextureAtlas.class);

        /** Json */
        assetManager.load(assetPath + "uiskin.json", Skin.class);

        /** Buttons */
        assetManager.load(assetPath + "logonew.png", Texture.class);

        /** Music */
        assetManager.load(assetPath + "Music/MenuScreen.mp3", Music.class);

        // Wait for the Assetmanager to finish loading all the assets
        assetManager.finishLoading();
    }

    public void dispose(){
        assetManager.dispose();
    }

    public AssetManager getAssetManager(){
        return assetManager;
    }

    /**
     * Loads the given level and returns it
     * @param level
     * @return tiledMap
     */
    public TiledMap loadLevel(String level){
        return mapLoader.load( assetPath + level);
    }

    /**
     * Return a given texture from the Asset manager
     * @param texture
     * @return
     */
    public Texture getTexture(String texture){
        return assetManager.get(assetPath + texture, Texture.class);
    }

    /**
     * Return a given font asset from the Asset Manager
     * @param texture
     * @return
     */
    public TextureAtlas getTextureAtlas(String texture){
        return assetManager.get(assetPath + texture, TextureAtlas.class);
    }

    public FileHandle resolveJson(String file){
        return assetManager.getFileHandleResolver().resolve(assetPath + file);
    }

    public void handGunSound(){
        sound = Gdx.audio.newSound(Gdx.files.internal(assetPath + "pistol.mp3"));
        sound.play();
    }

    public Music getMusic(String fileName){
        return assetManager.get(assetPath + "Music/" + fileName);
    }

}
