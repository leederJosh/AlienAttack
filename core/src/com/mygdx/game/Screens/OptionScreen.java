package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Game.AlienGame;
import com.mygdx.game.world.AssetHandler;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class OptionScreen implements Screen {

    private AlienGame game;
    private Stage stage;
    private Skin skin;
    private TextButton buttonMainMenu;
    private TextButton musicImg;
    private Texture bg;
    private Texture wasd;
    private String path;




    public OptionScreen (final AlienGame game) {
        this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
        this.game = game;
        this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
        //this.bg = new Texture(path + "/alienred.jpg");
        //this.wasd = new Texture(path + "/keys.png");

        this.bg = AssetHandler.getAssetHandler().getTexture("alienred.jpg");
        this.wasd = AssetHandler.getAssetHandler().getTexture("keys.png");

    }



    @Override
    public void show() {
        System.out.println("OPTION");
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        path.replace("assets", "UI");

        //Made this work on windows by taking out path from the get statement, instead added "/assets" to the name of the file i want to get
        this.skin = new Skin();
        //this.skin.addRegions(game.assets.get( "/assets/uiskin.atlas", TextureAtlas.class));
        this.skin.addRegions(AssetHandler.getAssetHandler().getTextureAtlas("uiskin.atlas"));
        this.skin.add("default-font", game.fontB24);
        //this.skin.load(Gdx.files.internal( "uiskin.json"));
        this.skin.load(AssetHandler.getAssetHandler().getAssetManager().getFileHandleResolver().resolve("uiskin.json"));
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
        game.batch.draw(wasd, 20, 100);

        game.font40.draw(game.batch,"Option", 170, 480);
        game.font40.draw(game.batch,"The Final Stand", 60, 420);
        game.fontT16.draw(game.batch,"-------------------------------------------------", 10, 380);
        game.fontT16.draw(game.batch,"SURVIVE EACH TREACHEROUS LEVEL LONG ENOUGH TO", 10, 350);
        game.fontT16.draw(game.batch,"PROCEDD TO THE NEXT STAGE IN ORDER TO SAVE", 10, 320);
        game.fontT16.draw(game.batch,"HUMANITY THROUGH OBTAINING THE CURE, BUT BE", 10, 290);
        game.fontT16.draw(game.batch,"AWARE ONE WRONG STEP COULD END YOUR MISSION.", 10, 260);
        game.fontT16.draw(game.batch,"WITH THE CURE HUMANITY CAN STRIKE BACK AND", 10, 230);
        game.fontT16.draw(game.batch,"RECLAIM THEIR FREEDOM.", 10, 200);
        game.fontT16.draw(game.batch,"WASD / ARROW KEYS TO MOVE", 140, 150);
        game.fontT16.draw(game.batch,"MOUSE LEFT CLICK TO SHOOT", 140, 120);
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


}