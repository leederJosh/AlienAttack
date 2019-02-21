<<<<<<< HEAD
package Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AlienGame;
import com.mygdx.game.MyInputProcessor;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

import world.GameMap;
import world.TiledGameMap;

public class PlayScreen implements Screen {

	private AlienGame game;
	private Stage stage;
	private Skin skin;
	private TextButton buttonMainMenu;
	private GameMap gameMap;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public static final int V_WIDTH = 512;
	public static final int V_HEIGHT = 512;
	private ArrayList<Entity> entities;
	private GameMap map;
	private MyInputProcessor inputProcessor;
	private String path;
	
	
	
	public PlayScreen (final AlienGame game, GameMap map) {
		this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.game = game;
		this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		gameMap = map;
		this.inputProcessor = new MyInputProcessor(camera, map.getPlayer());
	}
	
	
	
	@Override
	public void show() {
		System.out.println("PLAY");
		Gdx.input.setInputProcessor(inputProcessor);
		//stage.clear();

		
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
		//stage.clear();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera, batch);
		
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


=======
package Screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AlienGame;
import com.mygdx.game.MyInputProcessor;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

import world.GameMap;
import world.TiledGameMap;

public class PlayScreen implements Screen {

	private AlienGame game;
	private Stage stage;
	private Skin skin;
	private TextButton buttonMainMenu;
	private GameMap gameMap;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public static final int V_WIDTH = 512;
	public static final int V_HEIGHT = 512;
	private ArrayList<Entity> entities;
	private GameMap map;
	private MyInputProcessor inputProcessor;
	private String path;
	
	
	
	public PlayScreen (final AlienGame game, GameMap map) {
		this.path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		this.game = game;
		this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		gameMap = map;
		this.inputProcessor = new MyInputProcessor(camera, map.getPlayer());
	}
	
	
	
	@Override
	public void show() {
		System.out.println("PLAY");
		Gdx.input.setInputProcessor(inputProcessor);
		//stage.clear();

		
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
		//stage.clear();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera, batch);
		
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


>>>>>>> dd976ff4a39baa7a1f78997f93df7140588716c3
}