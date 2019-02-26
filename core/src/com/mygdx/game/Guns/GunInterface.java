package com.mygdx.game.Guns;

/**
 * An interface to enforce behaviour all guns should do
 * @Author Josh Leeder
 * @Date 25/02/19
 */
public interface GunInterface {


    /** Will hold differnt shooting logic for differnt types of guns */
    void shoot(float mappedMouseX, float mappedMouseY);
}
