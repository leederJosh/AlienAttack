package com.mygdx.game;

import Screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.entities.EntityList;
import world.GameMap;
import world.TiledGameMap;

import java.io.File;

/**
 * AlienGame represents the window. Handles the different screens.
 *
 * @auothor Sam Robinson,
 */
public class AlienGame extends Game {

    public static final String TITLE = "The Final Stand";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 512;
    public static final int V_HEIGHT = 512;
    public static final String PROJECT_PATH = new File("").getAbsolutePath();

    public OrthographicCamera camera;
    public SpriteBatch batch;
    public Texture img;
    /////////////////////////////////
    //NATHAN
    public GameMap gameMap;
    public ShapeRenderer sr;
    public Texture weapon;
    public SpriteBatch imageBatch;
    ////////////////////////////////

    public BitmapFont font24;
    public BitmapFont font40;
    public BitmapFont fontT16;
    public BitmapFont fontB24;


    public AssetManager assets;

    public LoadingScreen loadingScreen;
    public SplashScreen splashScreen;
    public MainMenuScreen mainMenuScreen;
    public PlayScreen playScreen;
    public OptionScreen optionScreen;
    public CreditScreen creditScreen;



    @Override
    public void create () {

        //TODO: Remove this after pathing is done.
        System.out.println(PROJECT_PATH);

        //Creates the singleton
        EntityList.getEntityList();
        assets= new AssetManager();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);



        initFonts();

        gameMap = new TiledGameMap(batch);

        loadingScreen = new LoadingScreen(this);
        splashScreen = new SplashScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        playScreen = new PlayScreen(this, gameMap);
        optionScreen = new OptionScreen(this);
        creditScreen = new CreditScreen(this);

        this.setScreen(loadingScreen);

        String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");

        /////////////////////////////////////
        //NATHAN
        sr = new ShapeRenderer();
        weapon = new Texture(path + "/Pistol.png");
        float w = Gdx.graphics.getWidth();
        float y = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        //false so it doesn't load from top left
        camera.setToOrtho(true, w, y);
        camera.update();
        //loads the game map
        ////////////////////////////////////
    }

    @Override
    public void render () {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        ////////////////////////////////////
        //NATHAN

        camera.update();


        //interface attempt
        batch.begin();
        //draw the bars here
        sr.begin(ShapeRenderer.ShapeType.Filled);
        //health
        sr.setColor(Color.RED); //uses gdx.color
        sr.rect(15, 450, 100, 10);

        sr.setColor(Color.GREEN);
        sr.rect(15, 450, 90, 10);
        //humanity
        sr.setColor(Color.GRAY);
        sr.rect(275, 450, 100, 10);

        sr.setColor(Color.ORANGE);
        sr.rect(275, 450, 90, 10);
        //experience
        sr.setColor(Color.GRAY);
        sr.rect(15, 440, 100, 5);

        sr.setColor(Color.BLUE);
        sr.rect(15, 440, 90, 5);
        sr.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        //outlines (weapon and bars)
        //weapon
        sr.setColor(Color.GREEN);
        sr.rect(575, 425, 50, 50);
        batch.draw(weapon, 575, 425);
        //health
        sr.setColor(Color.BLACK);
        sr.rect(15, 450, 100, 10);
        //humanity
        sr.rect(275, 450, 100, 10);
        //experience
        sr.rect(15, 440, 100, 5);
        sr.end();
        batch.end();
        /////////////////////////////////////////////////
        super.render();

    }

    @Override
    public void dispose () {
        batch.dispose();
        font24.dispose();
        assets.dispose();
        loadingScreen.dispose();
        splashScreen.dispose();
        mainMenuScreen.dispose();
        playScreen.dispose();
        optionScreen.dispose();
        creditScreen.dispose();
        /////////////////////////
        //NATHAN
        gameMap.dispose();
        ////////////////////////


    }

    private void initFonts() {
        System.out.println(PROJECT_PATH);
        String path = PROJECT_PATH.replace("desktop", "core/assets/fonts");
        System.out.println(path);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(PROJECT_PATH + "/pixelFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter paramsTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter paramsButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsText.size = 16;
        paramsText.color = Color.WHITE;
        fontT16 = generator.generateFont(paramsText);

        paramsButton.size = 24;
        paramsButton.color = Color.BLACK;
        fontB24 = generator.generateFont(paramsButton);
        paramsTitle.size = 40;
        paramsTitle.color = Color.WHITE;
        font40 = generator.generateFont(paramsTitle);

        params.size = 24;
        params.color = Color.WHITE;
        font24 = generator.generateFont(params);
    }

}