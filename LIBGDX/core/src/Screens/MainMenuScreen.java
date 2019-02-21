<<<<<<< HEAD
package Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AlienGame;


import world.GameMap;
import world.TiledGameMap;


public class MainMenuScreen implements Screen {

	private final AlienGame game;
	private Texture bg;
	private Stage stage;
	private TextButton buttonPlay, buttonOption, buttonCredit, buttonExit;
	
	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	private boolean isInGame;
	
	private TiledGameMap tiledGameMap;
	
	private String path;
	
	
	
	
	public MainMenuScreen( final AlienGame game) {
		this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.game = game;
		this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
		this.shapeRenderer = new ShapeRenderer();
		this.bg = new Texture(path + "/alienred.jpg");
		this.camera = new OrthographicCamera();
		this.batch = new SpriteBatch();
		this.isInGame = false;		
	}	
	
	
	@Override
	public void show() {
		System.out.println("MAIN MENU");
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		
		path.replace("assets", "UI");
		
		this.skin = new Skin();
		this.skin.addRegions(game.assets.get(path + "/uiskin.atlas", TextureAtlas.class));
		this.skin.add("default-font", game.fontB24);
		this.skin.load(Gdx.files.internal(path + "/uiskin.json"));
		
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
		if (isInGame == false) {
			game.batch.draw(bg, 0, 0);
			game.font40.draw(game.batch, "The Final Stand", 70, 440);		
			game.font24.draw(game.batch, "Screen: MAIN MENU", 20, 20);
		}
		else {
			tiledGameMap.render(camera, batch);
		}
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
		shapeRenderer.dispose();
	}
	private void initButtons() {
		buttonPlay = new TextButton("Play", skin, "default");
		buttonPlay.setPosition(160, 310);
		buttonPlay.setSize(200, 40);
		buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.playScreen);

				//isInGame = true;
				//game.setScreen(TiledGameMap());
			}
		});
		
		buttonOption = new TextButton("Option", skin, "default");
		buttonOption.setPosition(160, 250);
		buttonOption.setSize(200, 40);
		buttonOption.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonOption.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.optionScreen);
			}
		});
		
		buttonCredit = new TextButton("Credits", skin, "default");
		buttonCredit.setPosition(160, 190);
		buttonCredit.setSize(200, 40);
		buttonCredit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonCredit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.creditScreen);
			}
		});
		
		
		buttonExit = new TextButton("Exit", skin, "default");
		buttonExit.setPosition(160, 130);
		buttonExit.setSize(200, 40);
		buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
				
		stage.addActor(buttonPlay);
		stage.addActor(buttonOption);
		stage.addActor(buttonCredit);
		stage.addActor(buttonExit);
	}

=======
package Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AlienGame;


import world.GameMap;
import world.TiledGameMap;


public class MainMenuScreen implements Screen {

	private final AlienGame game;
	private Texture bg;
	private Stage stage;
	private TextButton buttonPlay, buttonOption, buttonCredit, buttonExit;
	
	private Skin skin;
	private ShapeRenderer shapeRenderer;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	private boolean isInGame;
	
	private TiledGameMap tiledGameMap;
	
	private String path;
	
	
	
	
	public MainMenuScreen( final AlienGame game) {
		this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.game = game;
		this.stage = new Stage(new StretchViewport(game.V_WIDTH, game.V_HEIGHT,game.camera));
		this.shapeRenderer = new ShapeRenderer();
		this.bg = new Texture(path + "/alienred.jpg");
		this.camera = new OrthographicCamera();
		this.batch = new SpriteBatch();
		this.isInGame = false;		
	}	
	
	
	@Override
	public void show() {
		System.out.println("MAIN MENU");
		Gdx.input.setInputProcessor(stage);
		stage.clear();
		
		path.replace("assets", "UI");
		
		this.skin = new Skin();
		this.skin.addRegions(game.assets.get(path + "/uiskin.atlas", TextureAtlas.class));
		this.skin.add("default-font", game.fontB24);
		this.skin.load(Gdx.files.internal(path + "/uiskin.json"));
		
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
		if (isInGame == false) {
			game.batch.draw(bg, 0, 0);
			game.font40.draw(game.batch, "The Final Stand", 70, 440);		
			game.font24.draw(game.batch, "Screen: MAIN MENU", 20, 20);
		}
		else {
			tiledGameMap.render(camera, batch);
		}
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
		shapeRenderer.dispose();
	}
	private void initButtons() {
		buttonPlay = new TextButton("Play", skin, "default");
		buttonPlay.setPosition(160, 310);
		buttonPlay.setSize(200, 40);
		buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.playScreen);

				//isInGame = true;
				//game.setScreen(TiledGameMap());
			}
		});
		
		buttonOption = new TextButton("Option", skin, "default");
		buttonOption.setPosition(160, 250);
		buttonOption.setSize(200, 40);
		buttonOption.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonOption.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.optionScreen);
			}
		});
		
		buttonCredit = new TextButton("Credits", skin, "default");
		buttonCredit.setPosition(160, 190);
		buttonCredit.setSize(200, 40);
		buttonCredit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonCredit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.creditScreen);
			}
		});
		
		
		buttonExit = new TextButton("Exit", skin, "default");
		buttonExit.setPosition(160, 130);
		buttonExit.setSize(200, 40);
		buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, 0.5f, Interpolation.pow5Out))));
		buttonExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
				
		stage.addActor(buttonPlay);
		stage.addActor(buttonOption);
		stage.addActor(buttonCredit);
		stage.addActor(buttonExit);
	}

>>>>>>> dd976ff4a39baa7a1f78997f93df7140588716c3
}