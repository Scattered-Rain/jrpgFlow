package com.scatteredRain.jrpgFlow.desktop.util;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

/** Packs All Images Into Their TextureAtlasses, Used In Desktop As Tools Not Compatible With Other Versions */
public class Autopacker {
	
	/** The Dimensions Of The Final Image The Atlas Uses Worst Case */
	private static final int DIMENSIONS = 1024;
	/** Root Folder Of Images */
	private static final String ROOT = "../core/assets/img";
	/** Input Folder */
	private static final String INPUT = ROOT+"/unpacked";
	/** Output Folder */
	private static final String OUTPUT = ROOT+"/packed";
	
	/** Pack All Images Within Folder Into Single Atlas */
	public static void packFolder(String folderName){
		String atlasName = folderName;
		Settings settings = new Settings();
		settings.maxWidth = DIMENSIONS;
		settings.maxHeight = DIMENSIONS;
		//settings.debug = true;
		TexturePacker.process(settings, INPUT+"/"+folderName, OUTPUT, atlasName);
	}
	
}
