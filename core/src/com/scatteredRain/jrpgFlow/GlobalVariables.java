package com.scatteredRain.jrpgFlow;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.scatteredRain.jrpgFlow.Constants.SpriteID;
import com.scatteredRain.jrpgFlow.general.ActiveWorldList;

import lombok.Data;

/** Static Class That Is Supposed To Be Easily Accessible And Holds Most Generals */
public class GlobalVariables {
	
	/** Globally Used Tween Manager */
	public static TweenManager globalTweenManager;
	
	/** Globally Used Sprite Atlas */
	public static TextureAtlas globalSpriteAtlas;
	
	/** The List Of Active Worlds */
	public static ActiveWorldList globalActiveWorldsList;
	
	/** The Players Current Skin */
	public static SpriteID globalPlayerSkin;
	
	/** The Default Skin That Is Used By The Game */
	public static Skin globalSkin;
	
}
