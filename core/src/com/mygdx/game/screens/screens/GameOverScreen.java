package com.mygdx.game.screens.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.entities.EntityList;

public class GameOverScreen implements Screen {

    private AlienGame game;
    private Stage stage;
    private Skin skin;
    private TextButton buttonMainMenu, buttonExit, restartButton;
    private Texture bg;
    private Texture gameOver;
    private String uiAtlas = "uiskin.atlas";
    private String uiJson = "core/assets/uiskin.json";
    private String gameOverTex = "gameOver.png";
    private String alienTex = "alienred.jpg";




    public GameOverScreen (final AlienGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
        this.bg = AssetHandler.getAssetHandler().getTexture(alienTex);
        this.gameOver = AssetHandler.getAssetHandler().getTexture(gameOverTex);
    }

    @Override
    public void show() {
        System.out.println("GAME OVER");
        Gdx.input.setInputProcessor(stage);
        stage.clear();


        this.skin = new Skin();
        this.skin.addRegions(AssetHandler.getAssetHandler().getTextureAtlas(uiAtlas));
        this.skin.add("default-font", game.fontB24);
        this.skin.load(AssetHandler.getAssetHandler().getAssetManager().getFileHandleResolver().resolve(uiJson));
        initButtons();

    }

    public void update(float delta) {
        stage.act();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        game.batch.begin();

        game.batch.draw(bg, 0, 0);
        game.batch.draw(gameOver, 115, 240);
        game.font40.draw(game.batch,"You're Dead", 110, 480);
        game.fontT16.draw(game.batch,"..ya know, in case you didn't notice.", 70, 440);
        game.font40.draw(game.batch,"GAME OVER", 140, 240);
        game.batch.end();

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
        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(15, 30);
        buttonExit.setSize(90, 30);
        buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonMainMenu = new TextButton("Main Menu", skin, "default");
        buttonMainMenu.setPosition(170, 140);
        buttonMainMenu.setSize(180, 30);
        buttonMainMenu.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.mainMenuScreen);
                game.getLevelManager().setLevelCounter(0);
                game.getLevelManager().getCurrentLevel().spawnPlayer();
                game.screenManager.setToScreen("menu");
            }
        });

        restartButton = new TextButton("Restart", skin, "default");
        restartButton.setPosition(170, 180);
        restartButton.setSize(180, 30);
        restartButton.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                EntityList.purge();
                EntityList.getEntityList().getPlayer().setIsDead(false);
                EntityList.getEntityList().getPlayer().setHealth(100);
                EntityList.getEntityList().getPlayer().refreshHumanity();
                game.getLevelManager().getCurrentLevel().spawnPlayer();
                game.getLevelManager().refreshEntities();
                game.getLevelManager().getCurrentLevel().spawnEntities();
                game.screenManager.setToScreen("play");
            }
        });

        stage.addActor(buttonMainMenu);
        stage.addActor(buttonExit);
        stage.addActor(restartButton);


    }
}