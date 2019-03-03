package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.PrefixFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Game.AlienGame;
import jdk.internal.cmm.SystemResourcePressureImpl;
import sun.font.TrueTypeFont;

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


    /**
     * At the moment it won't load textures, it was having problems with Pistol.png until I put path in to the load and get
     * After that it had problems with PistolLeft which is some improvements
     */


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
        //assetManager = new AssetManager(new InternalFileHandleResolver());
        // assetManager.setLoader(Texture.class, null, TextureLoader.TextureParameter );

        //assets = new AssetManager(new PrefixFileHandleResolver(new InternalFileHandleResolver(PROJECT_PATH)));

        //path = new File("/core/assets/");
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

        //Boss
        assetManager.load(assetPath + "BossLeftThree.png", Texture.class);

        //Player animation textures
        assetManager.load(assetPath + "SpriteSheets/MoveRightMiddleBig.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterRight.png", Texture.class);
        assetManager.load(assetPath + "SpriteSheets/MainCharacterLeft.png", Texture.class);


        /** Fonts */
        //assetManager.load("/8bit9.fnt", BitmapFont.class);
        //assetManager.load("/pixelFont.ttf", FreeType.class);

        /** Maps */
        //assetManager.load("/AlleyWay.tmx", TiledMap.class);

        /** Screens */

        /** Texture Atlas */
        assetManager.load(assetPath + "uiskin.atlas", TextureAtlas.class);

        /** Json */
        assetManager.load(assetPath + "uiskin.json", Skin.class);

        /** Buttons */
        assetManager.load(assetPath + "logonew.png", Texture.class);

        // Wait for the assetmanager to finish loading all the assets
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

        //  I treid this to get the levels to load from core/assets but no luck
        // For some reason it is loading them from the root folder (click on alien attack once)
//        System.out.print("The below is the path\n");
//        System.out.print(Gdx.files.internal(assetPath).toString() + "/" + level + "\n");
//        String fileToGet = Gdx.files.internal(assetPath).toString() + "/" + level;
////        Gdx.files.internal(assetPath).toString()
//        return mapLoader.load( fileToGet);

        return mapLoader.load( assetPath + level);

    }

    /**
     * Return a given texture from the Asset manager
     * @param texture
     * @return
     */
    public Texture getTexture(String texture){

        //assetManager.finishLoadingAsset(texture);
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










    // TODO
    // Load all the assets of the game in the assetManger
    // Dispose of all assets in the game
    // Create an assetHandler object in Game ITiled game map or gamp map?)
    // Change all of the
}
