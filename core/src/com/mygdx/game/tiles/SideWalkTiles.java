package com.mygdx.game.tiles;

import java.util.HashMap;

public enum SideWalkTiles implements TileInterface {


    Floor1(1, true, "Floor1"),
    Water(2, false, "Water"),
    Boat(3, true, "Boat"),
    BuildingTile(4, false, "BuildingTile"),
    BuildingTileLightGrey(5, false, "LightGreyBuildingTile"),
    Window(6, false, "FireEscape");

    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    //private since all tiles will be intiated within this class
     SideWalkTiles(int id, boolean collidable, String name) {
        this(id, collidable, name, 0);
    }

     SideWalkTiles(int id, boolean collidable, String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.name= name;
        this.damage= damage;
    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isCollidable() {
        return collidable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
