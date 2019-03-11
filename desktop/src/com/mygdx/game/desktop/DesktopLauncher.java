package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.game.AlienGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AlienGame(), config);

		/** Stops the screen from resizing and gets rid of the grey boarder */
		config.width = AlienGame.V_WIDTH;
		config.width = AlienGame.V_HEIGHT;
		config.resizable = false;
	}
}
