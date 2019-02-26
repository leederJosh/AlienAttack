package com.mygdx.game.Shooting;

/**
 * Behavior for the hand gun bullet
 * @author Joshua Leeder
 * @date 12/02/19
 */
public class HandGunBullet extends AbstractBullet {


    public HandGunBullet(float posX, float posY){
        super(posX, posY, BulletType.HUMAN);
        width = 15;
        height = 15;
        speed = 5;
        bulletDamage = 25;
    }

    @Override
    public void act() {

    }

    // To do for other guns
    // Machine gun
    // Make a machine gun bullet class
    // Lower damage, and size of bullet compared to the handun
    // Increase speed
    // How do I make it shoot fast of burst fire?

    //Shotgun
    // Again make shotgun bullet class
    // The bullets will be smaller and slightly lower damange
    // However i need to spawn 3 per shot and these should travel in a spread
    // Should be a case of messing with the Ys of the bullet slightly when they spawn
    // Could make them despawn after a shorter distance or make them weaker if further away but this could be too tricky to implement

    //Add these objects to the player
    // Add a way to swtich between them or just set them manually so i can test them

    //Also don;t forget i need to do collision with the map
    // And alter the humanity depending on what a bullet hits, believe this is in the shooting handler, could probably pinch the old version
}
