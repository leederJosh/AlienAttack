package com.mygdx.game.screens;

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
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.assets.AssetHandler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class CreditScreen implements Screen {

    private AlienGame game;
    private Stage stage;
    private Skin skin;
    private TextButton buttonMainMenu;
    private Texture bg;

    /** Assets needed from the assetManager */
    private String alienTexture = "alienred.jpg";
    private String atlasSkin = "uiskin.atlas";
    private String jsonSkin = "uiskin.json";


    public CreditScreen (final AlienGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
        this.bg = AssetHandler.getAssetHandler().getTexture(alienTexture);
    }

    @Override
    public void show() {
        System.out.println("CREDITS");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(AssetHandler.getAssetHandler().getTextureAtlas(atlasSkin));
        this.skin.add("default-font", game.fontB24);
        this.skin.load(AssetHandler.getAssetHandler().getAssetManager().getFileHandleResolver().resolve(jsonSkin));
        initButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.font40.draw(game.batch,"Credits", 170, 480);
        game.font40.draw(game.batch,"The Final Stand", 60, 420);
        game.fontT16.draw(game.batch,"-------------------------------------------------", 10, 380);
        game.fontT16.draw(game.batch,"PRODUCED BY TEAM 12 OFFCIAL", 110, 350);
        game.fontT16.draw(game.batch,"COORDINATOR        AMER", 30, 300);
        game.fontT16.draw(game.batch,"ART DESIGN          ADBUL / HASNAATH", 30, 270);
        game.fontT16.draw(game.batch,"SOUND DESIGN       SAMUEL", 30, 240);
        game.fontT16.draw(game.batch,"PROGRAMMERS       EHSAN / SAMUEL / SOJOURNER", 30, 210);
        game.fontT16.draw(game.batch,"MENU DESIGN        EHSAN", 30, 180);
        game.fontT16.draw(game.batch,"LEVEL DESIGN        HASNAATH", 30, 150);
        game.fontT16.draw(game.batch,"WEBSITE DESIGN     ABDUL", 30, 120);
        game.batch.end();

        stage.draw();
    }

    public void update() {
        stage.act();
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

    @Override
    public void dispose() {
        stage.dispose();
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
}