package com.mygdx.game.Game;

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
import com.mygdx.game.Pickups.PickupHandler;
import com.mygdx.game.Screens.*;
import com.mygdx.game.Shooting.BulletList;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;
import com.mygdx.game.world.AbstractLevel;
import com.mygdx.game.world.AlleyWayLevel;
import com.mygdx.game.world.AssetHandler;

/**
 * AlienGame represents the window. Handles the different screens.
 *
 * @auothor Sam Robinson,
 */
public class AlienGame extends Game {

    /** Level 1 */
    private AbstractLevel currentMap;

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

    /** Screens */
    public LoadingScreen loadingScreen;
    public SplashScreen splashScreen;
    public MainMenuScreen mainMenuScreen;
    public PlayScreen playScreen;
    public OptionScreen optionScreen;
    public CreditScreen creditScreen;




    //TODO - Right refactoring is out of the question (I have tried every way I can and it's not gonna happen)
    // New plan is:
    // Create the first level in the AlienGame class and give it to the player
    // Have a method in each level to spawn entities
    // Call this method in every level constructor EXCEPT the first
    // Call it after in Alien game (after the list and the player are created)
    // Add an object of EVERY level in playScreen and a way to transition between them
    // Bish, Bash, bosh hope it works
    // Would mean after transitioning from the first to the second and then back again all levels would be handled by the play screen

    //TODO
    // PROBLEMS WITH THE ABOVE
    //  IF we create all the object in the playScreen constructor (with methods for spawning enemies in each levels constructor) all get added to the EntityList at once
    //  WE will instead have to manually call the currentMap.spawnEntities() method every time we change level DONE THIS

    //TODO
    // After the above
    // AI for movement needs to be fixed
    // AI shooting needs to be fixed (The bullets need to collide and disappear when hitting the player)
    // They also need to not hit each other


    @Override
    public void create () {

        // Creates the singletons
        AssetHandler.getAssetHandler();
        EntityList.getEntityList();
        BulletList.getBulletList();

        batch = new SpriteBatch();

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);

        // Create level 1, needed for the player
        currentMap = new AlleyWayLevel();

        // Create player
        Player player = new Player(25, 400, currentMap);
        EntityList.updateEntityList(player);
        EntityList.setPlayer(player);


        initFonts();

        // Creates the screens
        loadingScreen = new LoadingScreen(this);
        splashScreen = new SplashScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        playScreen = new PlayScreen(this);
        optionScreen = new OptionScreen(this);
        creditScreen = new CreditScreen(this);

        // Set the screen to the loading screen
        this.setScreen(loadingScreen);

        // For the window and the HUD elements
        sr = new ShapeRenderer();
        weapon = AssetHandler.getAssetHandler().getTexture("Pistol.png");
        float w = Gdx.graphics.getWidth();
        float y = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();

        //false so it doesn't load from top left
        camera.setToOrtho(true, w, y);
        camera.update();
    }

    @Override
    public void render () {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        camera.update();
        super.render();

    }

    @Override
    public void dispose () {
        batch.dispose();
        font24.dispose();
        AssetHandler.getAssetHandler().dispose();
        loadingScreen.dispose();
        splashScreen.dispose();
        mainMenuScreen.dispose();
        playScreen.dispose();
        optionScreen.dispose();
        creditScreen.dispose();
        currentMap.dispose();
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

}
