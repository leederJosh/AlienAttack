//package com.mygdx.game;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//
//import Screens.CreditScreen;
//import Screens.LoadingScreen;
//import Screens.MainMenuScreen;
//import Screens.OptionScreen;
//import Screens.PlayScreen;
//import Screens.SplashScreen;
//
//public class MainGame extends Game {
//	
//	public static final String TITLE = "The Final Stand";
//	public static final float VERSION = 0.1f;
//	public static final int V_WIDTH = 512;
//	public static final int V_HEIGHT = 512;
//	
//	public OrthographicCamera camera;
//	public SpriteBatch batch;
//	
//	public BitmapFont font24;
//	public BitmapFont fontB24;
//	public BitmapFont fontT16;
//	public BitmapFont font40;
//
//	
//	public AssetManager assets;
//	
//	public LoadingScreen loadingScreen;
//	public SplashScreen splashScreen;
//	public MainMenuScreen mainMenuScreen;
//	public PlayScreen playScreen;
//	public OptionScreen optionScreen;
//	public CreditScreen creditScreen;
//
//	
//	@Override
//	public void create () {
//		assets= new AssetManager();
//		batch = new SpriteBatch();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
//		
//		
//		initFonts();
//		
//		loadingScreen = new LoadingScreen(this);
//		splashScreen = new SplashScreen(this);
//		mainMenuScreen = new MainMenuScreen(this);
//		playScreen = new PlayScreen(this);
//		optionScreen = new OptionScreen(this);
//		creditScreen = new CreditScreen(this);
//
//		this.setScreen(loadingScreen);
//
//	}
//
//	@Override
//	public void render () {
//		super.render();
//		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//			Gdx.app.exit();
//		}
//	}
//	
//	@Override
//	public void dispose () {
//		batch.dispose();
//		font24.dispose();
//		assets.dispose();
//		loadingScreen.dispose();
//		splashScreen.dispose();
//		mainMenuScreen.dispose();
//		playScreen.dispose();
//		optionScreen.dispose();
//		creditScreen.dispose();
//
//
//	}
//	
//	private void initFonts() {
//		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("C:/LIBGDXGAME/LIBGDX/core/assets/fonts/pixelFont.ttf"));
//		FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		FreeTypeFontGenerator.FreeTypeFontParameter paramsTitle = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		FreeTypeFontGenerator.FreeTypeFontParameter paramsButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();
//		
//		paramsText.size = 16;
//		paramsText.color = Color.WHITE;
//		fontT16 = generator.generateFont(paramsText);
//		
//		paramsButton.size = 24;
//		paramsButton.color = Color.BLACK;
//		fontB24 = generator.generateFont(paramsButton);
//		
//		paramsTitle.size = 40;
//		paramsTitle.color = Color.WHITE;
//		font40 = generator.generateFont(paramsTitle);
//		
//		params.size = 24;
//		params.color = Color.WHITE;
//		font24 = generator.generateFont(params);
//	}
//
//}
