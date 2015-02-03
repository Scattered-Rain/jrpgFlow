package com.scatteredRain.jrpgFlow.util;

import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.SpriteBatchComponent;
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
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterMoveInitSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterSpriteAnimationUpdateSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterSpriteRenderSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.MapCharacterSpriteUpdateSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.MapPlayerCameraFocusSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.MapRenderSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.PassiveCharacterActionSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.PlayerCharacterInputSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.TextboxRenderSystem;
import com.scatteredRain.jrpgFlow.general.Animation;
import com.scatteredRain.jrpgFlow.general.PlayerCharacterInput;
import com.scatteredRain.jrpgFlow.util.mapFactory.MapFactory;

/** Builds And Returns Artemis Worlds */
public class WorldFactory {
	
	/** Build World Concerned With Map Processes */
	public static World buildMapWorld(MapID mapPath, int enter){
		World world = new World();
		
		//Systems
		world.setSystem(new PlayerCharacterInputSystem(), false);
		world.setSystem(new CharacterMoveInitSystem(), false);
		world.setSystem(new MapPlayerCameraFocusSystem(), false);
		world.setSystem(new MapCharacterSpriteUpdateSystem(), false);
		world.setSystem(new CharacterSpriteAnimationUpdateSystem(), false);
		world.setSystem(new PassiveCharacterActionSystem(), true);
		world.setSystem(new MapRenderSystem(false), false);
		world.setSystem(new CharacterSpriteRenderSystem(), false);
		world.setSystem(new MapRenderSystem(true), false);
		world.initialize();
		
		//Entities
		Entity generalEntity = world.createEntity();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(1, h/w);
		camera.position.x = TILE_SIZE*16;
		camera.position.y = TILE_SIZE*16;
		camera.zoom = ZOOM;
		camera.update();
		generalEntity.addComponent(new OrthographicCameraComponent(camera));
		generalEntity.addComponent(new SpriteBatchComponent(new SpriteBatch()));
		Entity mapEntity = world.createEntity();
		TmxMapLoader mapLoader = new TmxMapLoader();
		Parameters mapLoadParams = new Parameters();
		TiledMap map = mapLoader.load(mapPath.getPath());
		world = MapFactory.buildMap(world, map, enter);
		
		return world;
	}
	
	/** Build World Solely Concerned With Textboxes */
	public static World buildTextboxWorld(){
		World world = new World();
		
		world.setSystem(new TextboxRenderSystem());
		world.initialize();
		
		return world;
	}
	

}
