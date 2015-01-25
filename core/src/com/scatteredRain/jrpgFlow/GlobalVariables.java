package com.scatteredRain.jrpgFlow;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	
}
