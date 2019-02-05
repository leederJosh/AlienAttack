package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.AlienGame;

public class TiledGameMap extends GameMap{
	private
		TiledMap tiledMap;
		OrthogonalTiledMapRenderer tiledMapRenderer;
		Stage stage;
		ShapeRenderer sr;
		Texture weapon;
		SpriteBatch imageBatch;
		BitmapFont font;
		SpriteBatch text;
	
	public TiledGameMap(SpriteBatch batch) {
		super(batch);
		String path = AlienGame.PROJECT_PATH.replace("desktop", "core/assets");
		tiledMap = new TmxMapLoader().load(path + "/AlleyWay.tmx");
		//this will be what renders the tiled map
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		System.out.println("Tiled Game Map made");
		this.weapon = new Texture(path + "/Pistol.png");
		this.sr = new ShapeRenderer();
		this.imageBatch = new SpriteBatch();
		this.font = new BitmapFont(Gdx.files.internal(path + "/8bit9.fnt"));
		this.text = new SpriteBatch();
	}

	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		//Set the camera/view, tells the renderer what camera to use
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		//this is to render the entities
		//this is so the game renders based on how the cameras want the game to render
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		super.render(camera, batch);
		batch.end();
		
		////////////////////////////////////
		//UI Elements
		
		camera.update();
		
		
		//interface attempt
		batch.begin();
		
		//Set all UI elements to draw a certain amount down the window.
		double windowHeight = Gdx.graphics.getHeight();
		double percentageChangeBars = (windowHeight * 0.05);
		double drawHeightBars = windowHeight - percentageChangeBars;
		double drawHeightText = windowHeight - (percentageChangeBars / 4);
		
		//Get player's health and humanity values for the UI bars.
		int playerHealth = inputProcessor.getPlayer().getHealth();
		int playerHumanity = inputProcessor.getPlayer().getHumanity();
		
		//draw the bars here
		sr.begin(ShapeRenderer.ShapeType.Filled);
		//health
		sr.setColor(Color.RED); //uses gdx.color
		sr.rect(15,	(int) drawHeightBars, 100, 10);
		
		sr.setColor(Color.GREEN);
		sr.rect(15, (int) drawHeightBars, playerHealth, 10);
		//humanity
		sr.setColor(Color.GRAY);
		sr.rect(275, (int) drawHeightBars, 100, 10);
		
		sr.setColor(Color.ORANGE);
		sr.rect(275, (int) drawHeightBars, playerHumanity, 10);
		//TODO: experience
		//sr.setColor(Color.GRAY);
		//sr.rect(15, 440, 100, 5);
		
		//sr.setColor(Color.BLUE);
		//sr.rect(15, 440, 90, 5);
		sr.end();
		
		sr.begin(ShapeRenderer.ShapeType.Line);
		//outlines (weapon and bars)
		//weapon
		sr.setColor(Color.GREEN);
		sr.rect(575, ((int) drawHeightBars - 25), 50, 50);
		batch.draw(weapon, 575, 425);
		//health
		sr.setColor(Color.BLACK);
		sr.rect(15, (int) drawHeightBars, 100, 10);
		//humanity
		sr.rect(275, (int) drawHeightBars, 100, 10);
		//TODO: experience
		//sr.rect(15, ((int) drawHeightBars - 10), 100, 5);
		sr.end();
		batch.end();
		//drawing the pistol in top right
		imageBatch.begin();
		imageBatch.draw(weapon, 450, ((int) drawHeightBars - 30), 50, 50);
		imageBatch.end();
		//Text labels
		text.begin();
		font.draw(text, "HP:", 15, (float) drawHeightText);
		//font.draw(text, "EXP:", 15, (float) drawHeightText - 10)
		font.draw(text, "HUMANITY:", 275, (float) drawHeightText);
		text.end();
		
		/////////////////////////////////////////////////
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	public void dispose() {
		tiledMap.dispose();

	}
	
	//allows us to get data based on the map
		@Override
		public Tile getTileByCoordinate(int layer, int col, int row) {
			//there is different cells in the tilemap which can have tiles within in
			//this method returns the tile type based on the coordinate of the cell (where in the map) (the tile position)
			Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);
			
			if (cell!= null) {
				//if it equals null then it means we are clicking on a tile outside the map or one that doesn't exist on that layer
			TiledMapTile tile = cell.getTile();
			
				if(tile != null) {
					int id = tile.getId();
					return Tile.getTileByid(id);
				}
			}
			return null;
		}

	@Override
	public int getWidth() {
		//given in width of tiles
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		//given in height of tiles
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		//gets the amount of layers
		return tiledMap.getLayers().getCount();
	}

	

}
