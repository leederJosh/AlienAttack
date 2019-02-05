package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.AlienGame;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = AlienGame.TITLE + " v" + AlienGame.VERSION;
		config.width = AlienGame.V_WIDTH ;
		config.height = AlienGame.V_HEIGHT;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.resizable = true;
		
		new LwjglApplication (new AlienGame(), config);	
	}
}



