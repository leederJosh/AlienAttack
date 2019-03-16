package com.mygdx.game.screens.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.assets.AssetHandler;

public class LoadingScreen implements Screen {

    private final AlienGame game;
    private ShapeRenderer shapeRenderer;
    private float progress;
    private Texture bg;
    private Stage stage;

    /** Assets needed from the assetHandler */
    private String alienTex = "alienred.jpg";


    public LoadingScreen(final AlienGame game){
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        this.bg = AssetHandler.getAssetHandler().getTexture(alienTex);
        this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
    }

    @Override
    public void show() {
        System.out.println("LOADING");
        Gdx.input.setInputProcessor(stage);
        this.progress = 0f;
        stage.clear();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.font24.draw(game.batch, "Loading....", 200, 230);
        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, game.camera.viewportWidth - 64, 16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, game.camera.viewportHeight / 2 - 8, progress * (game.camera.viewportWidth - 64), 16);
        shapeRenderer.end();

        stage.draw();

    }

    private void update() {
        //game.setScreen(game.splashScreen);
        game.screenManager.setToScreen("splash");
        stage.act();
    }

    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();
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