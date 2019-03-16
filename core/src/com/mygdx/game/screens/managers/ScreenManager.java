package com.mygdx.game.screens.managers;

import com.badlogic.gdx.Screen;
import com.mygdx.game.game.AlienGame;
import com.mygdx.game.screens.screens.*;

/**
 * Manages the current scene and sets new scenes as current depending on input
 * @Author Josh Leeder
 * @Date 14/03/19
 */
public class ScreenManager {

    private Screen currentScreen;
    private AlienGame game;

    public ScreenManager(AlienGame game){
        currentScreen = new LoadingScreen(game);
        this.game = game;
    }


    public void update(Float delta){
        currentScreen.render(delta);
    }

    /** Set the current screen to a new screen using a given string */
    public void setToScreen(String screen){

        if(screen.equals("credit")){
            currentScreen = new CreditScreen(game);
        }
        else if(screen.equals("gameOver")){
            currentScreen = new GameOverScreen(game);
        }
        else if(screen.equals("loading")){
            currentScreen = new LoadingScreen(game);
        }
        else if(screen.equals("option")){
            currentScreen = new OptionScreen(game);
        }
        else if(screen.equals("play")){
            currentScreen = new PlayScreen(game);
        }
        else if(screen.equals("splash")){
            currentScreen = new SplashScreen(game);
        }
        else if(screen.equals("menu")){
            currentScreen = new MainMenuScreen(game);
        }

        currentScreen.show();

    }


    public AlienGame getGame(){
        return game;
    }

    public Screen getCurrentScreen(){
        return currentScreen;
    }

    public void setCurrentScreen(Screen newScreen){
        currentScreen = newScreen;
    }

    public void disposeOfCurrentScene(){
        currentScreen.dispose();
    }

    //TODO
    // What this includes
    // A way to instantiate all screens
    // Holds the current Screen
    // Handles the current scenes request to change screens
    // Will need an abstract screen so that all screens are of the same type
}
