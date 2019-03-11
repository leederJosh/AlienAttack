package com.mygdx.game.collisions;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entities.*;
import com.mygdx.game.game.AlienGame;

import java.util.ArrayList;

/**
 * Creates world objects from map object layers
 * These world objects are used for collision and spawn points
 * @Author Josh Leeder
 * @Date 09/03/19
 */
public class MapObjectParser {


    /** Needs the levels object to add bodies to for Box2D */
    private World world;
    private TiledMap tiledMap;


    public MapObjectParser(World world, TiledMap tiledMap){

        this.tiledMap = tiledMap;
        this.world = world;
    }

    /**
     * Creates a Box2D body for every object in the given Object layer, adds it to the Box2D game levels
     * This is for collision between entities and the floor
     */
    public void parseFloorObjectLayer() {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("Floor").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getY() + rectangle.getHeight() / 2) / AlienGame.ppm);

            body = world.createBody(bdef);
            shape.setAsBox((rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getHeight() / 2) / AlienGame.ppm);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.8f; // This is the friction for all of the floor tiles, increase and decrease to slop character sliding (max is 1)
            body.createFixture(fixtureDef);
        }
        shape.dispose();
    }

    /**
     * Returns a list of entities spawned at each box of each respective spawn object layer of a map
     */
    public ArrayList<Entity> parseEnemySpawnPoints(){

        ArrayList<Entity>entitiesForGivenLevel = new ArrayList<Entity>();

        // Spawns an existing player at a given location this must go first
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("PlayerSpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            // Creates the player at the first level spawn MIGHT HAVE TO PUT THE ENTITY LIST ADD PLAYER HERE
            if(EntityList.getEntityList().getPlayer() == null){
                Player player = new Player(xPos, yPos);
                EntityList.updateEntityList(player);
                EntityList.setPlayer(player);

            }
            // If player is already created then just update the position to the new spawn
            else{
                EntityList.getEntityList().getPlayer().getBodyDef().position.x = xPos;
                EntityList.getEntityList().getPlayer().getBodyDef().position.y = yPos;
            }

        }

        // Spawns enemies
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("EnemySpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            entitiesForGivenLevel.add(new Enemy(xPos, yPos));
            //EntityList.updateEntityList(new Enemy(xPos, yPos));
        }

        // Spawns friendlies
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("FriendlySpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            entitiesForGivenLevel.add(new Friendly(xPos, yPos));
        }

        // Spawns boss
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("BossSpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            entitiesForGivenLevel.add(new Boss(xPos, yPos));
        }


        return entitiesForGivenLevel;
    }


    /**
     * Returns a Rectangle object to be placed in a map, acts as a transition boundary for the player
     * @return
     */
    public Rectangle parseTransitionObjects(){

        //TODO
        // Get the rectangle from the map
        // Could just add a rectangle to the map at that point
        // Then compare if the player is inside it (maybe do a timer)
        // If they are then play screen levels array needs to be incremented (could be done via boolean in the player saying they have finished)

        Rectangle rectangleToReturn = new Rectangle();

        // Spawns an existing player at a given location this must go first
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("Transition").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;
            float width = rectangle.getWidth() / AlienGame.ppm;
            float height = rectangle.getHeight() / AlienGame.ppm;

            rectangleToReturn.set(xPos, yPos, width, height);
        }

        return rectangleToReturn;
    }
}
