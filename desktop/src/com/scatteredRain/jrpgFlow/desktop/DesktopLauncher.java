package com.scatteredRain.jrpgFlow.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.scatteredRain.jrpgFlow.JrpgFlow;
import com.scatteredRain.jrpgFlow.desktop.util.Autopacker;

public class DesktopLauncher {
	
	/** Whether The Game Is Supposed To Pack */
	private static final boolean PACKING = true;
	
	/** List Of Debug Resolutions And The Index Of The Active Resolution */
	private static final int[][] DEBUG_WINDOW_SIZES = new int[][]{{640, 480}, {2560/4, 1440/4}, {2560/8, 1440/8},  {480, 480}};
	private static final int USE_WINDOW_SIZE= 0;
	private static final boolean FULLSCREEN = true;
	
	public static void main (String[] arg) {
		if(PACKING){
			pack();
		}
		//Start Actual Game
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "JRPG Flow";
		if(!FULLSCREEN){
			config.fullscreen = false;
			config.width = DEBUG_WINDOW_SIZES[USE_WINDOW_SIZE][0];
			config.height = DEBUG_WINDOW_SIZES[USE_WINDOW_SIZE][1];
		}
		else{
			config.fullscreen = true;
			config.width = config.getDesktopDisplayMode().width;
			config.height = config.getDesktopDisplayMode().height;
			config.vSyncEnabled = true;
		}
		new LwjglApplication(new JrpgFlow(), config);
	}
	
	/** Initializes All Packing */
	private static void pack(){
		Autopacker.packFolder("sprites");
		Autopacker.packFolder("ui");
	}
	
}
