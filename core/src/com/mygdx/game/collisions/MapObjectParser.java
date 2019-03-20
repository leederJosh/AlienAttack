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

            //What type of fixture def I am
            fixtureDef.filter.categoryBits = MapObjectLayers.FLOOR_OBJECT;
            // What other fixtures I can collide with
            fixtureDef.filter.maskBits = MapObjectLayers.ENTITY_OBJECT | MapObjectLayers.PLAYER_OBJECT;
            body.createFixture(fixtureDef);
        }
        shape.dispose();
    }

    /**
     * Parse the player spawn point, will create the player the first time it is called (if the player in EntityList is null)
     * @param world
     */
    public void parsePlayerSpawnPoint(World world){

        // Spawns an existing player at a given location this must go first
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("PlayerSpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            // Creates the player at the first level spawn MIGHT HAVE TO PUT THE ENTITY LIST ADD PLAYER HERE
            if (EntityList.getEntityList().getPlayer() == null) {
                Player player = new Player(xPos, yPos, world);
                EntityList.updateEntityList(player);
                EntityList.setPlayer(player);

            }
            // If player is already created then just update the position to the new spawn
            else {
                EntityList.getEntityList().getPlayer().getBodyDef().position.x = xPos;
                EntityList.getEntityList().getPlayer().getBodyDef().position.y = yPos;
                EntityList.getEntityList().getPlayer().reDefinePlayerBox2D(xPos, yPos, world);

                System.out.println("The player x is now: " + EntityList.getEntityList().getPlayer().getx());
                System.out.println("The player y is now: " + EntityList.getEntityList().getPlayer().gety());
            }
        }
    }


    /**
     * Returns a list of entities spawned at each box of each respective spawn object layer of a map
     */
    public ArrayList<Entity> parseEntitySpawnPoints(){

        ArrayList<Entity>entitiesForGivenLevel = new ArrayList<Entity>();


        // Spawns enemies
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("EnemySpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            entitiesForGivenLevel.add(new Enemy(xPos, yPos, world));
        }

        // Spawns friendlies
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("FriendlySpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            entitiesForGivenLevel.add(new Friendly(xPos, yPos, world));
        }

        return entitiesForGivenLevel;
    }


    /**
     * Parses a boss spawn point, spawning them at a location and returning them
     * @return boss
     */
    public Entity parseBossSpawn(){

        Entity boss = null;
        //Spawns boss
        //Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("BossSpawn").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;

            boss = new Boss(xPos, yPos, world);
        }
        return boss;
    }


    /**
     * Returns a Rectangle object to be placed in a map, acts as a transition boundary for the player
     * @return
     */
    public Rectangle parseTransitionObjects(){

        Rectangle rectangleToReturn = new Rectangle();

        // Spawns an existing player at a given location this must go first
        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("LevelTransition").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            float xPos = rectangle.getX() / AlienGame.ppm;
            float yPos = rectangle.getY() / AlienGame.ppm;
            float width = rectangle.getWidth() / AlienGame.ppm;
            float height = rectangle.getHeight() / AlienGame.ppm;

            rectangleToReturn.set(xPos, yPos, width, height);
        }

        return rectangleToReturn;
    }

    public void parseBoundaryObjects() {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("Boundary").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getY() + rectangle.getHeight() / 2) / AlienGame.ppm);

            body = world.createBody(bdef);
            shape.setAsBox((rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getHeight() / 2) / AlienGame.ppm);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.8f; // This is the friction for all of the floor tiles, increase and decrease to slop character sliding (max is 1)

            //What type of fixture def I am
            fixtureDef.filter.categoryBits = MapObjectLayers.BOUNDARY_OBJECT;
            // What other fixtures I can collide with
            fixtureDef.filter.maskBits = MapObjectLayers.ENTITY_OBJECT;
            body.createFixture(fixtureDef);
        }
        shape.dispose();
    }

    /**
     * Creates a Box2D body for every object in the given Object layer, adds it to the Box2D game levels
     * This is for collision between players and trap objects
     */
    public void parseTrapObjectLayer() {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Gets all the rectangle objects from a given map and layer
        for (MapObject object : tiledMap.getLayers().get("Trap").getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getY() + rectangle.getHeight() / 2) / AlienGame.ppm);

            body = world.createBody(bdef);
            shape.setAsBox((rectangle.getWidth() / 2) / AlienGame.ppm, (rectangle.getHeight() / 2) / AlienGame.ppm);
            fixtureDef.shape = shape;
            fixtureDef.friction = 0.8f; // This is the friction for all of the floor tiles, increase and decrease to slop character sliding (max is 1)

            //What type of fixture def I am
            fixtureDef.filter.categoryBits = MapObjectLayers.TRAP_OBJECT;
            // What other fixtures I can collide with
            fixtureDef.filter.maskBits = MapObjectLayers.PLAYER_OBJECT;
            body.createFixture(fixtureDef);
        }
        shape.dispose();
    }
}
