package com.cubegame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.cubegame.game.BlocksGame;
import com.cubegame.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
		new LwjglApplication(BlocksGame.getInstance(), config);
	}
}
