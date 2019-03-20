package com.mygdx.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.levels.LevelManager;
import com.mygdx.game.screens.managers.ScreenManager;
import com.mygdx.game.shooting.BulletList;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.assets.AssetHandler;

/**
 * AlienGame represents the window. Handles the different screens.
 *
 * @auothor Sam Robinson,
 */
public class AlienGame extends Game {

    /** The levels object for Box2D */ // This is X gravity and Y gravity, leave at zero for now as it seems to cause problems
    //public static World world = new World(new Vector2(0f, -9.81f), false);
    public static final float ppm = 100;


    /** Window */
    public OrthographicCamera camera;
    public static final int V_WIDTH = 512;
    public static final int V_HEIGHT = 512;

    /** HUD */
    public SpriteBatch batch;
    public ShapeRenderer sr;
    public Texture weapon;

    /** Fonts */
    public BitmapFont font24;
    public BitmapFont font40;
    public BitmapFont fontT16;
    public BitmapFont fontB24;

    /** screens */
    public ScreenManager screenManager;

    /**Level manager */
    private LevelManager levelManager;
//    public LoadingScreen loadingScreen;
//    public SplashScreen splashScreen;
//    public MainMenuScreen mainMenuScreen;
//    public PlayScreen playScreen;
//    public OptionScreen optionScreen;
//    public CreditScreen creditScreen;
//    public GameOverScreen gameOverScreen;



    @Override
    public void create () {

        // Creates the singletons
        AssetHandler.getAssetHandler();
        EntityList.getEntityList();
        BulletList.getBulletList();

        batch = new SpriteBatch();

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH , V_HEIGHT);

        // Create player
//        Player player = new Player(400, 300);
//        EntityList.updateEntityList(player);
//        EntityList.setPlayer(player);


        initFonts();

        // Creates the screens
        screenManager = new ScreenManager(this);
        screenManager.setToScreen("loading");
        levelManager = new LevelManager();
//        loadingScreen = new LoadingScreen(this);
//        splashScreen = new SplashScreen(this);
//        mainMenuScreen = new MainMenuScreen(this);
//        playScreen = new PlayScreen(this);
//        optionScreen = new OptionScreen(this);
//        creditScreen = new CreditScreen(this);
//        gameOverScreen = new GameOverScreen(this);


        // Set the screen to the loading screen
        //this.setScreen(loadingScreen);

        // For the window and the HUD elements
        sr = new ShapeRenderer();
        weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");
        float w = Gdx.graphics.getWidth();
        float y = Gdx.graphics.getHeight();
        //camera = new OrthographicCamera();

        //false so it doesn't load from top left
        //camera.setToOrtho(true, w, y);
        camera.update();

        //world.setContactListener(new MyContactListener());
    }

    @Override
    public void render () {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        screenManager.update(Gdx.graphics.getDeltaTime());
        camera.update();
        super.render();

    }

    @Override
    public void dispose () {
        batch.dispose();
        font24.dispose();
        AssetHandler.getAssetHandler().dispose();
//        loadingScreen.dispose();
//        splashScreen.dispose();
//        mainMenuScreen.dispose();
//        playScreen.dispose();
//        optionScreen.dispose();
//        creditScreen.dispose();
//        gameOverScreen.dispose();
    }

    private void initFonts() {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(AssetHandler.getAssetHandler().getAssetManager().getFileHandleResolver().resolve("core/assets/pixelFont.ttf"));
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

    public LevelManager getLevelManager(){
        return levelManager;
    }

}
