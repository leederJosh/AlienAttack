package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.tiles.InsideBuildingTiles;
import com.mygdx.game.tiles.TileInterface;

/**
 * Handles level 2.
 */
public class InsideBuildingLevel extends AbstractLevel {

    public InsideBuildingLevel() {
        super();
        tiledMap = AssetHandler.getAssetHandler().loadLevel("InsideBuilding1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void spawnEnemies() {
        Enemy enemy = new Enemy( 35, 400, this);
        EntityList.updateEntityList(enemy);
    }

    @Override
    public void spawnFriendlies() {

    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        //Set the camera/view, tells the renderer what camera to use
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        //this is to render the entities
        //this is so the game renders based on how the cameras want the game to render
        batch.setProjectionMatrix(camera.combined);
        super.render(camera, batch);
        camera.update();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }




    /**
     * gets a tile by pixel position within the game com.mygdx.game.world at a specified layer.
     * @param layer
     * @param x
     * @param y
     * @return
     */
    //called getTileByLocation on video tutorial
    public TileInterface getTileByid(int layer, float x, float y) {
        //gets the location on the screen (game com.mygdx.game.world) and divide it by the tile size
        //converts it to an integer, it gets the location within the array that the tile exists in
        //then calls the next method to get the tile from where it is.
        return this.getTileByCoordinate(layer, (int) (x/ InsideBuildingTiles.TILE_SIZE), (int) (y/ InsideBuildingTiles.TILE_SIZE));
    }


    /**
     * Gets a tile at its coordinate wihtin the map at a specified layer
     * @param layer
     * @param col
     * @param row
     * @return
     */
    //allows us to get data based on the map
    public TileInterface getTileByCoordinate(int layer, int col, int row){
        //there is different cells in the tilemap which can have tiles within in
        //this method returns the tile type based on the coordinate of the cell (where in the map) (the tile position)
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);

        if (cell!= null) {
            //if it equals null then it means we are clicking on a tile outside the map or one that doesn't exist on that layer
            TiledMapTile tile = cell.getTile();

            if(tile != null) {
                int id = tile.getId();
                return InsideBuildingTiles.getTileByid(id);
            }
        }
        return null;
    }


    public boolean doesRectCollideWithMap(float x, float y, int width, int height){
        //checking whether the player is outside the map (also checking the width of the player is inside the map)
        if (x < 0 || y < 0 || x + width > getPixelWidth()|| y + height > getPixelHeight()) {
            return true;
        }
        //triple embedded loop to check for each tile
        //math.ceil rounds numbers up to the next value, if you dont do this will have weird results if entities are same size as tiles
        //only check the squares around the entity
        for(int row = (int) (y / InsideBuildingTiles.TILE_SIZE); row < Math.ceil((y + height) / InsideBuildingTiles.TILE_SIZE); row++) {
            for(int col = (int) (x / InsideBuildingTiles.TILE_SIZE); col < Math.ceil((x + width) / InsideBuildingTiles.TILE_SIZE); col++) {
                for(int layer = 0; layer < getLayers(); layer++) {
                    TileInterface type = getTileByCoordinate(layer, col, row);
                    if(type != null && type.isCollidable())
                        return true;
                    //tells the game there is a collision
                }
            }
        }
        return false;
    }
}
