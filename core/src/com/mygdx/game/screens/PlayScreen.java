package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.game.MyInputProcessor;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;
import com.mygdx.game.levels.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class PlayScreen implements Screen {

    private AlienGame game;
    private Stage stage;
    private Skin skin;
    private TextButton buttonMainMenu;
    private AbstractLevel currentMap;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    private MyInputProcessor inputProcessor;
    private int levelCounter;
    private AbstractLevel[] levels;

    /** Necessary assets */
    private String uiAtlas = "uiskin.atlas";
    private String uiJson = "uiskin.json";

    /** Box2D */
    private Box2DDebugRenderer box2DDebugRenderer;
    private Viewport gamePort;


    //TODO
    // BUGS
    // HOSPITAL LEVEL TRANSITION DOES NOT SEND THE PLAYER TO THE ARENA
    // SIDE WALK LEVEL CRASHES OUT TRYING TO GET TO HOSPITAL (EVEN WITH MUSIC TURNED OFF)
    // A

    public PlayScreen (final AlienGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new FitViewport(AlienGame.V_WIDTH / AlienGame.ppm, AlienGame.V_HEIGHT / AlienGame.ppm, camera);

        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth() / AlienGame.ppm, Gdx.graphics.getHeight()  / AlienGame.ppm , camera));
        batch = new SpriteBatch();

        this.inputProcessor = new MyInputProcessor(camera);
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        levels = new AbstractLevel[] {new AlleyWayLevel(), new InsideBuildingLevel(), new SideWalkRiverLevel(), new HospitalLevel(), new ArenaLevel()};
        this.levelCounter = 0;
        currentMap = levels[levelCounter];
        //currentMap.spawnEntities();

        // Just needed to see Box2d effects for development
        box2DDebugRenderer = new Box2DDebugRenderer();
    }



    @Override
    public void show() {
        System.out.println("PLAY");
        Gdx.input.setInputProcessor(inputProcessor);

        this.skin = new Skin();
        this.skin.addRegions(AssetHandler.getAssetHandler().getTextureAtlas(uiAtlas));
        this.skin.add("default-font", game.fontB24);
        this.skin.load(AssetHandler.getAssetHandler().resolveJson(uiJson));
        initButtons();

    }


    public void update() {

        stage.act();

        //For box2D
        currentMap.getWorld().step(1/60f, 6, 2);

        //Centre camera on players Body
        camera.position.x = EntityList.getEntityList().getPlayer().getB2body().getPosition().x;
        camera.position.y = EntityList.getEntityList().getPlayer().getB2body().getPosition().y;

        if(currentMap.hasPlayerFinished() == true){
            setLevel();
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMap.update(Gdx.graphics.getDeltaTime());
        currentMap.render(camera, batch);

        for(Entity entity: EntityList.getListEntities()) {
            entity.update(delta);
        }

        update();
        stage.draw();

        box2DDebugRenderer.render(currentMap.getWorld(), camera.combined);
    }


    private void initButtons() {
        buttonMainMenu = new TextButton("Back", skin, "default");
        buttonMainMenu.setPosition(15, 30);
        buttonMainMenu.setSize(90, 30);
        buttonMainMenu.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });
        stage.addActor(buttonMainMenu);
    }


    public void setLevel(){
        if(((Player)EntityList.getEntities().get(0)).hasPlayerFinished()) {
            Player player = ((Player) EntityList.getEntities().get(0));
            EntityList.purge();
            EntityList.updateEntityList(player);
            EntityList.getEntityList().getPlayer().setPlayerFinished(false);

        }

        levelCounter++;
        if (!(levelCounter > levels.length - 1)) {
            //currentMap.getWorld().dispose(); DO NOT UNCOMMENT, BREAKS EVERYTHING, but leaving as I should dispose of the worlds somewhere
            currentMap.clearEntitiesToSpawn();
            currentMap = levels[levelCounter];
            currentMap.spawnPlayer();
            currentMap.spawnEntities();
        }
        else {levelCounter--; }

        System.out.print("Num of things in entityList " + EntityList.getListEntities().size() + "\n");
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}