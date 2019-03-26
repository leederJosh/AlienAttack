package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.assets.AssetHandler;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PlayerTest {

    @Mock
    private Player player = mock(Player.class);
    static World world;

    @BeforeClass
    public static void setUp() throws Exception {
        world = new World(new Vector2(0f, -9.81f), true);
        //player.reDefinePlayerBox2D(1, 1, world);
    }

    @AfterClass
    public static void tearDown() throws Exception {

    }

    @Test
    public void limitAirSpeedTest() {

        assertFalse(player.getIsDead());

        // Make a player
        // Give it a speed where y is greater than 0
        // then give it an x speed above the max speed
        // call limit air speed
        // Check the x speed is less than the max x speed in player

        //World world = new World(new Vector2(0f, -9.81f), true);

        //verify(player).reDefinePlayerBox2D(1, 1, world);
        //verify(player, times(1)).reDefinePlayerBox2D(1, 1, world);
//        player.reDefinePlayerBox2D(1.0f, 1.0f, world);
//        player.getB2body().setLinearVelocity(5, 1);
//        player.limitInAirSpeed();
//        assertEquals(player.MAX_PLAYER_SPEED, player.getLinearVelocity());
    }

    @Test
    public void reDefinePlayerBox2D() {
    }

    @Test
    public void shoot() {
    }

    @Test
    public void setGun() {
    }

    @Test
    public void hasPlayerFinished() {
    }

    @Test
    public void setPlayerFinished() {
    }

    @Test
    public void getHumanity() {
    }

    @Test
    public void getSpeed() {
    }

    @Test
    public void getBodyDef() {
    }

    @Test
    public void getHealth() {
    }

    @Test
    public void setIsDead() {
        AssetHandler.getAssetHandler().getTexture( "core/assets/SpriteSheets/MoveRightMiddleBig.png");
        AssetHandler.getAssetHandler().loadAllAssets();
        Player player = new Player( 25,  400,  world);
        player.setIsDead(true);
        assertTrue(player.isDead());
    }

    @Test
    public void getIsDead() {
    }

    @Test
    public void isDead() {

    }

    @Test
    public void setHealth() {
    }

    @Test
    public void reverseMovement() {
    }

    @Test
    public void refreshHumanity() {
    }
}