package com.scatteredRain.jrpgFlow.artemis.components.maps;

import java.util.Iterator;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.scatteredRain.jrpgFlow.util.Point;

import static com.scatteredRain.jrpgFlow.Constants.*;

public class MapCollisionComponent extends Component{
	
	private int width;
	private int height;
	//Note Y|X, Equal Index to map
	private boolean[][] generalCollision;
	//Up, Right, Down, Left As far as the order of the side collisions is concerned
	private boolean[][][] sideCollision;
	//Collision Caused by entities
	private Entity[][] characterCollision;
	
	
	public MapCollisionComponent(TiledMap map){
		boolean collisionMapsInitiated = false;
		Iterator<MapLayer> iterator = map.getLayers().iterator();
		while(iterator.hasNext()){
			MapLayer layer = iterator.next();
			if(layer instanceof TiledMapTileLayer){
				TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;
				if(tileLayer.isVisible()){
					if(!tileLayer.getProperties().containsKey(TOP_LAYER_PROPERTY)){
						//Init Collision Maps In Case This Is The First Run Through
						if(!collisionMapsInitiated){
							this.width = tileLayer.getWidth();
							this.height = tileLayer.getHeight();
							this.generalCollision = new boolean[height][width];
							this.characterCollision = new Entity[height][width];
							this.sideCollision = new boolean[4][][];
							for(int c=0; c<sideCollision.length; c++){
								this.sideCollision[c] = new boolean[height][width];
							}
							collisionMapsInitiated = true;
						}
						//Actual Collision Checking For Entire Layer
						for(int cy=0; cy<tileLayer.getHeight(); cy++){
							for(int cx=0; cx<tileLayer.getWidth(); cx++){
								if(tileLayer.getCell(cx, cy)!=null && tileLayer.getCell(cx, cy).getTile()!=null){
									TiledMapTile tile = tileLayer.getCell(cx, cy).getTile();
									if(tile.getProperties().containsKey(COLLISION_PROPERTY)){
										generalCollision[cy][cx] = true;
									}
									if(tile.getProperties().containsKey(COLLISION_PROPERTY_UP)){
										sideCollision[UP][cy][cx] = true;
									}
									if(tile.getProperties().containsKey(COLLISION_PROPERTY_RIGHT)){
										sideCollision[RIGHT][cy][cx] = true;
									}
									if(tile.getProperties().containsKey(COLLISION_PROPERTY_DOWN)){
										sideCollision[DOWN][cy][cx] = true;
									}
									if(tile.getProperties().containsKey(COLLISION_PROPERTY_LEFT)){
										sideCollision[LEFT][cy][cx] = true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	/** Returns Whether Given Coordinates Are Within Map Bounds */
	public boolean inBounds(int x, int y){
		return (x>=0 && y>=0 && x<width && y<height);
	}
	
	/** Returns General Collision of given Tile */
	public boolean getCollision(int x, int y){
		return generalCollision[y][x];
	}
	
	/** Returns Side Collision of given tile */
	public boolean getSideCollision(int x, int y, int direction){
		return sideCollision[direction][y][x];
	}
	
	/** Returns Traversability on this map, taking into account the map borders as intraversible */
	public boolean isTraversible(int x, int y, int direction){
		Point target = calcTarget(x, y, direction);
		if(inBounds(target.getX(), target.getY())){
			if(!getCollision(target.getX(), target.getY())){
				if(!getSideCollision(x, y, direction)){
					if(!getSideCollision(target.getX(), target.getY(), oppDirection(direction))){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}
