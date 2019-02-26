package com.mygdx.game.Shooting;

import java.util.ArrayList;

/**
 * A singleton class to manage the bullets on screen and remove them
 * @author Joshua Leeder
 * @date 12/02/19
 */
public class BulletList {

    /** Instance on the BulletList class */
    private static BulletList bulletList = null;

    /** List to hold all bullet entities */
    //Not sure if I need this as T or AbstractBullet
    private static ArrayList<AbstractBullet> bullets;

    /** A list of bullets to remove from the screen */
    private static ArrayList<AbstractBullet> bulletsToRemove;


    private BulletList(){

        bullets = new ArrayList<AbstractBullet>();
        bulletsToRemove = new ArrayList<AbstractBullet>();
    }

    public static BulletList getBulletList(){

        if(bulletList == null){
            bulletList = new BulletList();
        }
        return bulletList;
    }

    /**
     * Gets a list of the bullet objects on screen
     * @return bullets
     */
    public ArrayList<AbstractBullet> getBullets(){
        return bullets;
    }

    /**
     * Add a bullet to the list
     * @param bullet
     */
    public void addBullet(AbstractBullet bullet){
        bullets.add(bullet);
    }


    /**
     * Add a bullet to the list to be removed
     * @param bullet
     */
    public void addBulletToRemove(AbstractBullet bullet){
        bulletsToRemove.add(bullet);
    }

    /**
     * Removes bullets from the bulletList
     */
    public void removeBullets(){

        // Only removes the bullets if there is a bullet in the list to remove
        if(bullets.size() != 0){

            //Not sure if this is going to work or if it needs to be a nested loop
            for(AbstractBullet bullet: bulletsToRemove){
                bullets.remove(bullet);
            }

            //This may break things if not done at the right time
            bulletsToRemove.clear();
        }

    }

}
