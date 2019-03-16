package com.mygdx.game.entities;

import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class  EntityList {

    private static EntityList entityList = null;
    private static TreeMap<Double, Entity> entityMap;
    private static ArrayList<Entity> entityArrayList;
    /** Holds dead entities to remove */
    private static ArrayList<Entity> entitiesToRemove;
    /** List to remove from map */
    private static ArrayList<Double> mapRemover;
    /** Keeps the instance of the player */
    private static Player player;

    //Private constructor
    private EntityList() {
        this.entityMap = new TreeMap<Double, Entity>();
        this.entityArrayList = new ArrayList<Entity>();
        mapRemover = new ArrayList<Double>();
    }

    //Gets instance or makes a new one
    public static EntityList getEntityList() {
        if(entityList == null){
            entityList = new EntityList();
            entitiesToRemove = new ArrayList<Entity>();
        }
        return entityList;
    }

    public static ArrayList<Entity> getEntities(){
        return entityArrayList;
    }

    public static void updateEntityList(Entity e) {
        entityArrayList.add(e);

        double hyp = 0;

        for (int i = 1; i < entityArrayList.size(); i++) {
            double xDifference = entityArrayList.get(0).getx() - entityArrayList.get(i).getx();
            double yDifference = entityArrayList.get(0).gety() - entityArrayList.get(i).gety();
            hyp = Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
        }
        updateEntityMap(hyp, e);
    }

    public static void checkCollections() {
        ArrayList<Entity> tempList = new ArrayList<Entity>();

        for (Double key : entityMap.keySet()) {
            tempList.add(entityMap.get(key));
        }

        //If the map has more entites in it, then the arrayList is more up to date (ie update the TreeMap)
        if (tempList.size() > entityArrayList.size()) {
            //Remove all entities in EntityMap
            entityMap.clear();
            //Add entities to EntityList
            for (Entity e : entityArrayList) {
                updateEntityList(e);
            }
        }
        //Otherwise the map has less entities, so its the most up to date (ie update the arrayList)
        else if (tempList.size() < entityArrayList.size()) {
            entityArrayList = tempList;
        }
    }


    public static ArrayList<Entity> getListEntities(){
        ArrayList<Entity> output = new ArrayList<Entity>();

        for(Double key : entityMap.keySet()) {
            output.add(entityMap.get(key));
        }
        return output;
    }

    public static void setListEntities(ArrayList<Entity> list) {
        System.out.println(list);
        entityArrayList = new ArrayList<Entity>(list);
        System.out.println("entityArrayList: " + entityArrayList);
        entityMap.clear();

        for (int i = 0; i < entityArrayList.size(); i++) {
            double xDifference = entityArrayList.get(0).getx() - entityArrayList.get(i).getx();
            double yDifference = entityArrayList.get(0).gety() - entityArrayList.get(i).gety();
            double hyp = Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
            updateEntityMap(hyp, entityArrayList.get(i));
        }
    }

    /** Clears entity list and map */
    public static void purge() {
//        for(Entity entity : entityArrayList){
//            World world = entity.getWorld();
//            world.destroyBody(entity.getB2body());
//            entity.disposeOfBox2d();
//        }
        Player temp = (Player)entityArrayList.get(0);
        entityArrayList.clear();
        entityMap.clear();
        updateEntityList(temp);
    }

    /**
     * Remove a given dead entity from the entity list
     * Added 22/2/19
     */
    public void addToRemoval(Entity entity){
        entitiesToRemove.add(entity);
    }


    /**
     * Remove a given dead entity from the entity list
     * Added 22/2/19
     */
    public void removeDeadEntities(){

        // This seems long but is necessary for concurrency errors in the map
        for(Entity entity : entitiesToRemove){
            //entityArrayList.remove(entity);
            for(Map.Entry<Double, Entity> entry : entityMap.entrySet()){
                if(entry.getValue().equals(entity)){
                    mapRemover.add(entry.getKey());
                }
            }

            //Also removes the entity from the list
            entityArrayList.remove(entity);
        }

        for(Double key : mapRemover){
            entityMap.remove(key);
        }

        mapRemover.clear();
        entitiesToRemove.clear();
    }

    public static void setPlayer(Player newPlayer){
        player = newPlayer;
    }

    public Player getPlayer(){
        return player;
    }

    //Adds an entity to the TreeMap
    private static void updateEntityMap(Double key, Entity value) {
        entityMap.put(key, value);
    }

    public static TreeMap<Double, Entity> getMapEntities() {
        return entityMap;
    }
}