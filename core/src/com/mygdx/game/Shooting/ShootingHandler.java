package com.mygdx.game.shooting;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.EntityList;
import com.mygdx.game.shooting.bullets.AlienHandgunBullet;
import com.mygdx.game.shooting.bullets.AlienRifleBullet;

import java.util.ArrayList;

/**
 * Handles bullet collision between bullets and entities
 * @Author Josh Leeder
 * @Date 19/02/19
 */
public class ShootingHandler {

    /**
     * Holds the entities to remove from the Entity List
     */
    private ArrayList<Entity> deadEntities;

    public ShootingHandler() {
        deadEntities = new ArrayList<Entity>();
    }

    /**
     * Handles what happens when a bullet collides with an entity
     * Compares a given bullet list and a given entity list
     * Returns true if an entity has died
     * @param entity
     */
    public boolean handleBullet(Entity entity) {

        boolean entityHasDied = false;

        ArrayList<AbstractBullet>alienRifleBulletsToAdd = new ArrayList<AbstractBullet>();

        for (AbstractBullet bullet : BulletList.getBulletList().getBullets()) {

            // Makes the alien bullets act
            if(bullet instanceof AlienHandgunBullet){
                bullet.act();
            }

            String entityType = entity.getType().getId();
            String bulletType = bullet.bulletType.getId();

            // If the bullet has collided with an entity
            if(bulletHasCollided(entity, bullet)){

                int bulletDamage = bullet.getBulletDamage();

                // Handles player bullet hitting enemy
                if(bulletType.equals("player") && entityType.equals("enemy")){

                    entity.reduceHealth(bulletDamage);
                    if(entity.getHealth() <= 0){
                        EntityList.getEntityList().getPlayer().getHumanity().increaseHumanity(15);
                    }

                    bullet.setDamage(0);
                    BulletList.getBulletList().addBulletToRemove(bullet);
                }
                // Handles player bullet hitting a friendly
                else if(bulletType.equals("player") && entityType.equals("friendly")){

                    entity.reduceHealth(bulletDamage);
                    EntityList.getEntityList().getPlayer().getHumanity().decreaseHumanity(10);

                    bullet.setDamage(0);
                    BulletList.getBulletList().addBulletToRemove(bullet);

                }
                // Handles player bullet hitting player
                else if(bulletType.equals("player") && entityType.equals("player")){
                    // DO NOTHING
                }
                //Handles enemy bullet hitting the player
                else if(bulletType.equals("enemy") && entityType.equals("player")){

                    EntityList.getEntityList().getPlayer().reduceHealth(bulletDamage);
                    bullet.setDamage(0);
                    BulletList.getBulletList().addBulletToRemove(bullet);
                }
                // Handles enemy bullet hitting enemy
                else if(bulletType.equals("enemy") && entityType.equals("enemy")){
                    //DO NOTHING
                }
                // Handles enemy bullet hitting friendly
                else if(bulletType.equals("enemy") && entityType.equals("friendly")){
                    //DO NOTHING
                }

                //Handles the entity dying
                // NOT PLAYER IS ONLY IN AT THE MOMENT TO STOP GAME CRASHING OUT
                if(entity.getHealth() <= 0 && !entityType.equals("player")){
                    deadEntities.add(entity);
                    entityHasDied = true;
                    World world = entity.getWorld();
                    world.destroyBody(entity.getB2body());
                    //entity.dispose();
                    //AlienGame.world.destroyBody(entity.getB2body());

                    // If the gun is an alien rifle spawn 4 other bullets on killing an entity
                    if(bullet instanceof AlienRifleBullet){
                        for(AbstractBullet bullet1 :((AlienRifleBullet) bullet).spawnExtraBullets(entity)){
                            alienRifleBulletsToAdd.add(bullet1);
                        }
                        System.out.print("\nALIEN RIFLE ACTING");
                    }
                }
            }
        }

        // Add the ALIEN RIFLE BULLETS HERE
        for(AbstractBullet rifleBullets: alienRifleBulletsToAdd){
            BulletList.getBulletList().addBullet(rifleBullets);
        }
        removeDeadEntities();
        return entityHasDied;
    }

    /**
     * Remove all the entities in the dead list from the EntityList
     */
    public void removeDeadEntities() {
        for (Entity entity : deadEntities) {
            EntityList.getEntityList().addToRemoval(entity);
        }
    }

    /**
     * Returns true when the hit box of a given bullet overlaps the hitbox of a given entity
     * @param entity
     * @param bullet
     * @return boolean
     */
    private boolean bulletHasCollided(Entity entity, AbstractBullet bullet) {

        boolean hasCollided = false;

        //Bullet variables to use
        float bulletWidth = bullet.getWidth();
        float bulletHeight = bullet.getHeight();
        float bulletX = bullet.getBulletX();
        float bulletY = bullet.getBulletY();

        //Entity variables to use
        float entityWidth = entity.getWidth();
        float entityHeight = entity.getHeight();
        float entityX = entity.getx();
        float entityY = entity.gety();

        //Hit boxes (mostly to make the IFs easier to read
        float entityXHitBox = entityX + entityWidth;
        float entityYHitBox = entityY + entityHeight;
        float bulletXHitBox = bulletX + bulletWidth;
        float bulletYHitBox = bulletY + bulletHeight;

        // Shoot to the right
        if (bulletXHitBox > entityX && bulletXHitBox < entityXHitBox) {

            // Shoot up
            if (bulletYHitBox > entityY && bulletYHitBox < entityYHitBox) {

                hasCollided = true;
            }
            // Shoot down
            else if (bulletY < entityYHitBox && bulletY > entityY) {

                hasCollided = true;
            }

        }
        //Shoot to the left
        else if (bulletX < entityXHitBox && bulletX > entityX) {

            // Shoot up
            if (bulletYHitBox > entityY && bulletYHitBox < entityYHitBox) {

                hasCollided = true;
            }
            // Shoot down
            else if (bulletY < entityYHitBox && bulletY > entityY) {

                hasCollided = true;
            }
        }

        return hasCollided;
    }
}
