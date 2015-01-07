package com.scatteredRain.jrpgFlow.util;

import static com.scatteredRain.jrpgFlow.Constants.TILE_SIZE;

import java.util.Iterator;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CameraFocusComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;
import com.scatteredRain.jrpgFlow.general.PlayerCharacterInput;

public class MapFactory {
	
	/** Adds Given Map To Given World, Completely Initializes All Entities Needed Thusly */
	public static World buildMap(World world, TiledMap map){
		buildMapEntity(world.createEntity(), map);
		Iterator<MapLayer> i = map.getLayers().iterator();
		MapLayer layer = null;
		while(i.hasNext()){
			MapLayer next = i.next();
			if(!(next instanceof TiledMapTileLayer)){
				layer = next;
				break;
			}
		}
		Iterator<MapObject> o = layer.getObjects().iterator();
		while(o.hasNext()){
			MapObject object = o.next();
			RectangleMapObject object2 = (RectangleMapObject)object;
			if(object2.getProperties().get("type", String.class).equals("ENTER")){
				int x = (int)(object2.getRectangle().getX()/TILE_SIZE);
				int y = (int)(object2.getRectangle().getY()/TILE_SIZE);
				buildPlayer(world.createEntity(), "", x, y, 2);
			}
			break;
		}
		return world;
	}
	
	/** Loads New Map And Attaches All Map Components To Given Entity */
	private static Entity buildMapEntity(Entity e, TiledMap map){
		e.addComponent(new MapComponent(map));
		e.addComponent(new TileMapRenderComponent(map));
		e.addComponent(new MapCollisionComponent(map));
		return e;
	}
	
	/** Adds Character To The World, Given X|Y, direction and Name of the Sprite */
	private static Entity buildPlayer(Entity e, String spriteName, int x, int y, int dir){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/packed/sprites.atlas"));
		MapCharacterAnimationSetComponent mapCharAniComp = new MapCharacterAnimationSetComponent(atlas, "strawhatBoy");
		e.addComponent(mapCharAniComp);
		e.addComponent(new ActiveCharacterSpriteAnimationComponent(mapCharAniComp.getActiveWalking(dir)));
		e.addComponent(new ActiveCharacterSpriteComponent(mapCharAniComp.getActiveWalking(dir).currentFrame()));
		e.addComponent(new CharacterLocationComponent(x, y));
		e.addComponent(new CharacterSpriteLocationComponent(TILE_SIZE*x+TILE_SIZE*0.5f, TILE_SIZE*y));
		e.addComponent(new DesiredCharacterMovementComponent(dir));
		e.addComponent(new CharacterDirectionComponent(dir));
		e.addComponent(new CharacterMoveProgressionComponent());
		PlayerCharacterInput playerInput = new PlayerCharacterInput();
		Gdx.input.setInputProcessor(playerInput);
		e.addComponent(new PlayerCharacterComponent(playerInput));
		e.addComponent(new CameraFocusComponent());
		return e;
	}

}
