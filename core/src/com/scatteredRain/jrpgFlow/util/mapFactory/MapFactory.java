package com.scatteredRain.jrpgFlow.util.mapFactory;

import static com.scatteredRain.jrpgFlow.Constants.*;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CameraFocusComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent.Entrance;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent.Waypoint;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory;
import com.scatteredRain.jrpgFlow.general.ActiveWorldList;
import com.scatteredRain.jrpgFlow.general.PlayerCharacterInput;
import com.sun.xml.internal.fastinfoset.sax.Properties;
import static com.scatteredRain.jrpgFlow.util.mapFactory.GenericCharacterFactory.*;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class MapFactory {
	
	//Type Of The Character (This Key Is Defined by Tiled Itself)
	public static final String TYPE = "type";
	//Entrance For Player (This Is Used In MapFactory And Will Not Be Translated Into A Character)
	public static final String ENTER = "ENTER";
	//Waypoint used for easily designing Movement processes
	public static final String WAYPOINT = "WAYPOINT";
	//ID and direction as referenced from the proper enum
	public static final String ID = CharacterFactory.AttKey.ID.key();
	public static final String SUB_ID = CharacterFactory.AttKey.SUB_ID.key();
	public static final String DIRECTION = CharacterFactory.AttKey.DIRECTION.key();
	
	
	/** Adds Given Map To Given World, Completely Initializes All Entities Needed Thusly */
	public static World buildMap(World world, TiledMap map, int entrance, Entity mapEntity){
		MapCharacterListComponent characterList = readObjectLayers(world, map, entrance);
		buildMapEntity(mapEntity, map, characterList);
		return world;
	}
	
	
	/** Loads New Map And Attaches All Map Components To Given Entity */
	private static Entity buildMapEntity(Entity e, TiledMap map, MapCharacterListComponent characterList){
		e.addComponent(characterList);
		e.addComponent(new MapComponent(map));
		e.addComponent(new TileMapRenderComponent(map));
		e.addComponent(new MapCollisionComponent(map));
		return e;
	}
	
	
	private static MapCharacterListComponent readObjectLayers(World world, TiledMap map, int entranceId){
		MapCharacterListComponent.Entrance entrance = null;
		//Init Component To Store Chars Easily
		MapCharacterListComponent characterList = null;
		int width = -1;
		int height = -1;
		Iterator<MapLayer> layerIterator = map.getLayers().iterator();
		while(layerIterator.hasNext()){
			MapLayer next = layerIterator.next();
			if((next instanceof TiledMapTileLayer)){
				TiledMapTileLayer tileLayer = (TiledMapTileLayer)layerIterator.next();
				width = tileLayer.getWidth();
				height = tileLayer.getHeight();
				characterList = new MapCharacterListComponent(width, height);
				break;
			}
		}
		//Iterates Through All The Layers, Getting All The ObjectLayer's Objects
		layerIterator = map.getLayers().iterator();
		while(layerIterator.hasNext()){
			MapLayer next = layerIterator.next();
			if(!(next instanceof TiledMapTileLayer)){
				//Object Layer
				MapLayer layer = next;
				//Go Through All The Layers Objects
				Iterator<MapObject> objectIterator = layer.getObjects().iterator();
				while(objectIterator.hasNext()){
					//Note: Assumes All Objects Are RectangleMapObjects
					RectangleMapObject object = (RectangleMapObject)objectIterator.next();
					MapProperties properties = object.getProperties();
					int x = (int)(object.getRectangle().getX()/TILE_SIZE);
					int y = (int)(object.getRectangle().getY()/TILE_SIZE);
					String type = properties.get(TYPE, String.class);
					//Find Entrance
					if(type.equals(ENTER)){
						int entId = Integer.parseInt(properties.get(ID, String.class));
						int enterDir = 2;
						if(properties.containsKey(DIRECTION)){
							enterDir = Integer.parseInt(properties.get(DIRECTION, String.class));
						}
						if(entId == entranceId){
							entrance = new Entrance(x, y, enterDir, entId);
						}
						characterList.addEntrance(new Entrance(x, y, enterDir, entId));
					}
					//Find Waypoints
					else if(type.equals(WAYPOINT)){
						int wayId = Integer.parseInt(properties.get(ID, String.class));
						int waySubId = Integer.parseInt(properties.get(SUB_ID, String.class));
						characterList.addWaypoint(new Waypoint(x, y, wayId, waySubId));
					}
					//Find Other Objects
					else{
						//Adds All Actual Characters Here!
						Entity character = GenericCharacterFactory.readObject(world.createEntity(), type, x, y, object, properties);
						characterList.addEntity(x, y, character);
					}
				}
			}
		}
		//Add Player (At Entrance)
		int playerX = -1;
		int playerY = -1;
		int playerDir = -1;
		if(entrance!=null){
			playerX = entrance.getX();
			playerY = entrance.getY();
			playerDir = entrance.getDir();
		}
		else{
			//If No Entrance Default Placement, Should NOT HAPPEN IN PROPPER GAME
			System.out.println("Could Not Find Proper Entrance On Map!");
			playerX = width/2;
			playerY = height/2;
			playerDir = 2;
		}
		Entity player = buildPlayer(world.createEntity(), playerX, playerY, playerDir);
		characterList.addEntity(playerX, playerY, player);
		characterList.completeInitialSetup();
		return characterList;
	}
	
	/** Adds Character To The World, Given X|Y, direction and Name of the Sprite */
	private static Entity buildPlayer(Entity e, int x, int y, int dir){
		addSprite(e, x, y, GlobalVariables.globalPlayerSkin.getPath(), dir);
		addExistence(e, x, y, dir, true);
		addMovabililty(e);
		
		PlayerCharacterInput playerInput = (PlayerCharacterInput) globalActiveWorldsList.getInput(globalActiveWorldsList.MAP_WORLD);
		playerInput.doTransitionPause();
		
		e.addComponent(new PlayerCharacterComponent(playerInput));
		e.addComponent(new CameraFocusComponent());
		
		return e;
	}

}
