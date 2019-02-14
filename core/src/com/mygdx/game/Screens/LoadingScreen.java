package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Game.AlienGame;

public class LoadingScreen implements Screen {



    private final AlienGame game;
    private ShapeRenderer shapeRenderer;
    private float progress;
    private Texture bg;
    private Stage stage;
    private String path;





    public LoadingScreen(final AlienGame game){
        path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
        this.game = game;
        this.shapeRenderer = new ShapeRenderer();
        this.bg = new Texture(path + "/alienred.jpg");
        this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));


    }


    private void queueAssets() {
        game.assets.load("/assets/logonew.png", Texture.class);
        game.assets.load("/assets/uiskin.atlas", TextureAtlas.class);
        game.assets.load("/assets/alienred.jpg", Texture.class);
    }


    @Override
    public void show() {
        System.out.println("LOADING");
        Gdx.input.setInputProcessor(stage);
        this.progress = 0f;
        queueAssets();
        stage.clear();

    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assets.getProgress(), 0.08f);
        if(game.assets.update() && progress >= game.assets.getProgress() - 0.1f) {
            game.setScreen(game.splashScreen);
        }
        stage.act();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);



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
        shapeRenderer.dispose();
    }


}