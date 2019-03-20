package com.mygdx.game.collisions;

/**
 * Holds constant short values used to decide what world objects can collide with each other
 * @Author Josh Leeder
 * @Date 15/03/19
 */
public class MapObjectLayers {

    /** Defines collidable bodies like the floor that all entities will collide with */
    public static final short FLOOR_OBJECT = 2;
    /** Defines entity (non-player) bodies */
    public static final short ENTITY_OBJECT = 4;
    /** Defines boundary objects for entity movement */
    public static final short BOUNDARY_OBJECT = 8;
    /** Defines the player object */
    public static final short PLAYER_OBJECT = 16;
    /** Defines the trap object */
    public static final short TRAP_OBJECT = 32;

}
