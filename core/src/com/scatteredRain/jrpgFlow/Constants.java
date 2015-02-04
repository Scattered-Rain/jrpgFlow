package com.scatteredRain.jrpgFlow;

import static com.scatteredRain.jrpgFlow.Constants.TILE_SIZE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.scatteredRain.jrpgFlow.util.FloatPoint;
import com.scatteredRain.jrpgFlow.util.Point;

public class Constants {
	
	/** Pixel Size Of Any Tile */
	public static final int TILE_SIZE = 16;
	
	//Map Key Words
	/** The String That Identifies A Layer As A Top Layer */
	public static final String TOP_LAYER_PROPERTY = "top";
	/** The String That Identifies A Tile As Having General Collision */
	public static final String COLLISION_PROPERTY = "coll";
	/** The String That Identifies A Tile As Having Sideways Collision In The Corresponding Directions */
	public static final String COLLISION_PROPERTY_UP = "collUP";
	public static final String COLLISION_PROPERTY_RIGHT = "collRIGHT";
	public static final String COLLISION_PROPERTY_DOWN = "collDOWN";
	public static final String COLLISION_PROPERTY_LEFT = "collLEFT";
	
	
	/** The Game's Zoom Level (Gets Slightly Adjusted Depending On Screen Size) */
	public static float ZOOM = 0; 
	/** Original Zoom Used To Calculate Actual Zoom */
	public static final float BASE_ZOOM = TILE_SIZE*16;//12
	
	
	/** The IDs corresponding to the directions */
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	/** Return Opposite Direction */
	public static int oppDirection(int direction){
		int opposite = -1;
		if(direction==UP){
			opposite = DOWN;
		}
		else if(direction==RIGHT){
			opposite = LEFT;
		}
		else if(direction==DOWN){
			opposite = UP;
		}
		else if(direction==LEFT){
			opposite = RIGHT;
		}
		return opposite;
	}
	
	/** Converts Given Position Into Position Adjacent Given Direction */
	public static Point calcTarget(int x, int y, int direction){
		if(direction==UP){
			y++;
		}
		else if(direction==RIGHT){
			x++;
		}
		else if(direction==DOWN){
			y--;
		}
		else if(direction==LEFT){
			x--;
		}
		return new Point(x, y);
	}
	
	/** Returns The Direction In Which The Target Is Adjacent To The Origin, Only If Directly Adjacent, Otherwise -1 */
	public static int calcAdjacencyDirection(int originX, int originY, int targetX, int targetY){
		if((Math.abs(originX-targetX)+Math.abs(originY-targetY))==1){
			if(originY<targetY){
				return UP;
			}
			else if(originX<targetX){
				return RIGHT;
			}
			else if(originY>targetY){
				return DOWN;
			}
			else if(originX>targetX){
				return LEFT;
			}
		}
		return -1;
	}
	
	
	
	@AllArgsConstructor
	public enum MapID{
		DEBUG_FIRST("maps/first.tmx"),
		DEBUG_CAVE("maps/cave.tmx");
		
		@Getter
		private String path;
	}
	
	@AllArgsConstructor
	public enum SpriteID{
		GENTLEMAN("elderlyGentleman"),
		STRAWHAT("strawhatBoy");
		
		@Getter
		private String path;
	}
	
	
}
