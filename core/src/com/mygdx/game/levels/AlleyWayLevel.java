package com.mygdx.game.levels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Player;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.assets.AssetHandler;
import com.mygdx.game.collisions.MapObjectParser;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.EntityList;

public class AlleyWayLevel extends AbstractLevel {


    public AlleyWayLevel() {
        super();
        tiledMap = AssetHandler.getAssetHandler().loadLevel("Level One/FirstLevel.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,1  / scale);
        mapObjectParser = new MapObjectParser(AlienGame.world, tiledMap);
        mapObjectParser.parseFloorObjectLayer();
        entitiesToSpawn = mapObjectParser.parseEnemySpawnPoints();
        levelEnd = mapObjectParser.parseTransitionObjects();
    }

    @Override
    public void spawnEntities() {

        // Not sure why but this works without being size()-1
        for(int index = 0; index < entitiesToSpawn.size(); index++){
            EntityList.updateEntityList(entitiesToSpawn.get(index));
        }
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

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public Rectangle getLevelEnd(){
        return levelEnd;
    }

    /**
     * Returns true if the player is within the level transition box
     * @return
     */
    @Override
    public boolean hasPlayerFinished(){

        boolean hasPlayerFinished = false;

        Player player = EntityList.getEntityList().getPlayer();
        float playerX = player.getx();
        float playerY = player.gety();
        float playerWidth = player.getWidth();
        float playerHeight = player.getHeight();


        if(playerX + playerWidth > levelEnd.getX() && playerX < levelEnd.getX() + levelEnd.getWidth()){

            if(playerY > levelEnd.getY() && playerY + playerHeight < levelEnd.getY() + levelEnd.getHeight()){
                hasPlayerFinished = true;
            }
        }
        return hasPlayerFinished;
    }

}