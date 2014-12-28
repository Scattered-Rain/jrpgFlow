package com.scatteredRain.jrpgFlow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.scatteredRain.jrpgFlow.JrpgFlow;
import com.scatteredRain.jrpgFlow.desktop.util.Autopacker;

public class DesktopLauncher {
	
	/** Whether The Game Is Supposed To Pack */
	private static final boolean PACKING = true;
	
	public static void main (String[] arg) {
		if(PACKING){
			pack();
		}
		//Start Actual Game
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "JRPG Flow";
		config.fullscreen = false;
		new LwjglApplication(new JrpgFlow(), config);
	}
	
	/** Initializes All Packing */
	private static void pack(){
		Autopacker.packFolder("sprites");
	}
	
}
