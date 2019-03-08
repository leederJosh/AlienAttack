package com.mygdx.game.tiles;

import java.util.HashMap;

public enum InsideBuildingTiles implements TileInterface {

    brick(1, false, "brick"),
    Window(2, false, "Window"),
    Floor1(3, true, "Floor1"),
    Furniture(4, false, "Furniture"),
    Furniture1(5, true, "Furniture1"),
    LightBrick(6, false, "LightBrick"),
    Bed (7, true, "Bed"),
    BedTransparent(8, false, "BedTransparent"),
    FurnitureTransparent(9, false, "FurnitureTransparent");


    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    //private since all tiles will be intiated within this class
     InsideBuildingTiles(int id, boolean collidable, String name) {
        this(id, collidable, name, 0);
    }
     InsideBuildingTiles(int id, boolean collidable, String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.name= name;
        this.damage= damage;

    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }


    //use a hash map to get the enum id for the tile that is going to be used
    private static HashMap<Integer, InsideBuildingTiles> tileMap;
    //static method so when the game starts, this code will run (will fill the hashmap)

    static {
        tileMap = new HashMap<Integer, InsideBuildingTiles>();
        for (InsideBuildingTiles tileType : InsideBuildingTiles.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }
    //static method to get the tile by using their id from the hashmap
    public static InsideBuildingTiles getTileByid(int id) {
        return tileMap.get(id);
        //return null;
    }

}

