package com.mygdx.game.Shooting;

/**
 * A class to give each bullet a type (Human or Alien)
 * @Author Josh Leeder
 * @Date 26/02/19
 */
public enum BulletType {

    ALIEN("alien"), PLAYER("player"), ENEMY("enemy");
    private String id;

    private BulletType(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
}