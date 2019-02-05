package Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AlienGame;


public class SplashScreen implements Screen {

	private final AlienGame game;
	private Stage stage;
	private Image splashImg;
	private Texture bg;
	private String path;

	
	public SplashScreen(final AlienGame game) {
		this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.game = game;
		this.stage = new Stage(new StretchViewport(AlienGame.V_WIDTH, AlienGame.V_HEIGHT, game.camera));
		this.bg = new Texture(path + "/alienred.jpg");
		
		
	}
	
	
	@Override
	public void show() {
		System.out.println("SPLASH");
		Gdx.input.setInputProcessor(stage);
		
		Runnable transtionRunnable = new  Runnable(){
			@Override
			public void run() {
				game.setScreen(game.mainMenuScreen);
			}
		};
		
		Texture splashTex = game.assets.get(path + "/logonew.png", Texture.class);
		splashImg = new Image(splashTex);
		splashImg.setOrigin(splashImg.getWidth() / 2 , splashImg.getHeight() / 2);
		splashImg.setPosition(stage.getWidth()/2 - 50, stage.getHeight() / 2 + 32);
		splashImg.addAction(sequence(alpha(0), scaleTo(0.1f, 0.1f),
				parallel(fadeIn(2f, Interpolation.pow2),
						scaleTo(2f, 2f, 2.5f, Interpolation.pow5),
						moveTo(stage.getWidth() / 2- 50, stage.getHeight() / 2 - 32 , 2f, Interpolation.swing)),
				delay(1.5f), fadeOut(1.25f), run(transtionRunnable)));
		
		stage.addActor(splashImg);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		game.batch.begin();
		game.batch.draw(bg, 0, 0);
		game.font24.draw(game.batch, "Screen: SPLASH", 20, 20);
		game.batch.end();
		stage.draw();

	}
	
	public void update(float delta) {
		stage.act(delta);
	}
	
	

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
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
