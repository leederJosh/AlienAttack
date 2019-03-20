package com.mygdx.game.levels;

import com.mygdx.game.entities.EntityList;
import com.mygdx.game.entities.Player;

/**
 * Holds an array of all levels and tracks the current level
 * @Author Josh Leeder
 * @Date 15/03/19
 */
public class LevelManager {


    private int levelCounter;
    private AbstractLevel[] levels;
    private AbstractLevel currentLevel;

    public LevelManager(){
        levelCounter = 0;
        levels = new AbstractLevel[] {new AlleyWayLevel(), new InsideBuildingLevel(), new SideWalkRiverLevel(), new HospitalLevel(), new ArenaLevel()};
        currentLevel = levels[levelCounter];
    }

    public void initialiseNextLevel(){
        Player player = ((Player) EntityList.getEntities().get(0));
        EntityList.purge();
        EntityList.updateEntityList(player);
        EntityList.getEntityList().getPlayer().setPlayerFinished(false);

        levelCounter++;
        if (!(levelCounter > levels.length - 1)) {
            //currentMap.getWorld().dispose(); DO NOT UNCOMMENT, BREAKS EVERYTHING, but leaving as I should dispose of the worlds somewhere
            //currentMap.clearEntitiesToSpawn();
            currentLevel = levels[levelCounter];
            currentLevel.spawnPlayer();
            currentLevel.spawnEntities();
        }
        else {levelCounter--; }
    }

    public AbstractLevel getCurrentLevel(){
        return currentLevel;
    }

    public void setLevelCounter(int index){
        if(index <= levels.length - 1){
            levelCounter = index;
        }
        currentLevel = levels[index];
    }
}
