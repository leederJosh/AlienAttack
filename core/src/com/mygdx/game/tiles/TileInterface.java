package com.mygdx.game.tiles;

import java.util.HashMap;

/**
 * An interface for enforcing behaviour needed in all enums implementing this interface
 * @Author Nathan Batchelor
 * @Date 06/03/19
 */
public interface TileInterface {


    public int getId();

    public boolean isCollidable();

    public String getName();

    public float getDamage();

    //TODO
    // ADD THIS TO ALL THE CONRETE TILE CLASSES AS IT CANNOT BE IN THE INTERFACE
    //public static Tile getTileByid(int id);


}
