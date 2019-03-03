package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Game.AlienGame;
import com.mygdx.game.Game.MyInputProcessor;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;
import com.mygdx.game.world.*;

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


    public PlayScreen (final AlienGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        this.inputProcessor = new MyInputProcessor(camera, EntityList.getEntityList().getPlayer());

        levels = new AbstractLevel[] {new AlleyWayLevel(), new InsideBuildingLevel()};
        this.levelCounter = 0;
        currentMap = levels[levelCounter];
        currentMap.spawnEnemies();
        System.out.print("Num of things in entityList " + EntityList.getListEntities().size() + "\n");
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

    public void update(float delta) {

        if (((Player) EntityList.getEntities().get(0)).hasPlayerFinished()) {
            setLevel();
        }
        stage.act();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMap.update(Gdx.graphics.getDeltaTime());
        currentMap.render(camera, batch);

        for(Entity entity: EntityList.getListEntities()) {
            entity.update(delta, -9.8f);
        }

        update(delta);
        stage.draw();
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
            currentMap = levels[levelCounter];
            EntityList.getEntities().get(0).setx(25);
            EntityList.getEntities().get(0).sety(400);
            EntityList.getEntities().get(0).setLevel(currentMap);
            currentMap.spawnEnemies();
        }
        else { levelCounter--; }

        System.out.print("Num of things in entityList " + EntityList.getListEntities().size() + "\n");
    }

}