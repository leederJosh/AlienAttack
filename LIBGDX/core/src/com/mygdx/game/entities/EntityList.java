package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.TreeMap;

public class EntityList {
	
	private static EntityList entityList = null;
	//private static ArrayList<Entity> myEntities;
	private static TreeMap<Double, Entity> entityMap;
	private static ArrayList<Entity> entityArrayList;
	
	//Private constructor
	private EntityList() {
		this.entityMap = new TreeMap<Double, Entity>();
		this.entityArrayList = new ArrayList<Entity>();
	}
	
	//Gets instance or makes a new one
	public static EntityList getEntityList() {
		if(entityList == null){
			entityList = new EntityList();
		}
		return entityList;
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
			//Remove all entities in EntityList
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
	
	//Adds an entity to the TreeMap
	private static void updateEntityMap(Double key, Entity value) {
		entityMap.put(key, value);
	}
	
	public static TreeMap<Double, Entity> getMapEntities() {
		return entityMap;
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
		
		/*//Add entities to EntityList
		for (Entity e : entityArrayList) {
			updateEntityList(e);
		}*/
	}

	public static void clear() {
		entityArrayList.clear();
		entityMap.clear();
		
	}
}
