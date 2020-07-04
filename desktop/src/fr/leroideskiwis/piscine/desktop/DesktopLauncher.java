package fr.leroideskiwis.piscine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.leroideskiwis.piscine.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		config.foregroundFPS = 1000;
		new LwjglApplication(new Main(), config);
	}
}
