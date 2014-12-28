package com.scatteredRain.jrpgFlow;

import com.scatteredRain.jrpgFlow.util.Point;

public class Constants {
	
	/** Pixel Size Of Any Tile */
	public static final int TILE_SIZE = 16;
	
	/** The String That Identifies A Layer As A Top Layer */
	public static final String TOP_LAYER_PROPERTY = "top";
	
	/** The String That Identifies A Tile As Having General Collision */
	public static final String COLLISION_PROPERTY = "coll";
	/** The String That Identifies A Tile As Having Sideways Collision In The Corresponding Directions */
	public static final String COLLISION_PROPERTY_UP = "collUP";
	public static final String COLLISION_PROPERTY_RIGHT = "collRIGHT";
	public static final String COLLISION_PROPERTY_DOWN = "collDOWN";
	public static final String COLLISION_PROPERTY_LEFT = "collLEFT";
	
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
		//Should Never be -1
		return opposite;
	}
	
	/** Converts Given Position Into Position Adjacent Given Direction */
	public static Point calcTarget(int x, int y, int direction){
		if(direction==UP){
			y--;
		}
		else if(direction==RIGHT){
			x++;
		}
		else if(direction==DOWN){
			y++;
		}
		else if(direction==LEFT){
			x--;
		}
		return new Point(x, y);
	}
	
}
