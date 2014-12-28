package com.scatteredRain.jrpgFlow.util;

import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.artemis.systems.MapRenderSystem;

/** Builds And Returns Artemis Worlds */
public class WorldFactory {
	
	/** Build World Concerned With Map Processes */
	public static World buildMapWorld(){
		World world = new World();
		
		//Systems
		world.setSystem(new MapRenderSystem(false), false);
		world.setSystem(new MapRenderSystem(true), false);
		
		world.initialize();
		//Entities
		Entity cameraEntity = world.createEntity();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(1, h/w);
		camera.position.x = 16;
		camera.position.y = 16;
		camera.zoom = 16f;
		camera.update();
		cameraEntity.addComponent(new OrthographicCameraComponent(camera));
		Entity mapEntity = world.createEntity();
		buildMapEntity(mapEntity, "maps/first.tmx");
		return world;
	}
	
	/** Loads New Map And Attaches All Map Components To Given Entity */
	private static Entity buildMapEntity(Entity e, String mapFile){
		TmxMapLoader mapLoader = new TmxMapLoader();
		Parameters mapLoadParams = new Parameters();
		TiledMap map = mapLoader.load(mapFile);
		e.addComponent(new MapComponent(map));
		e.addComponent(new TileMapRenderComponent(map));
		e.addComponent(new MapCollisionComponent(map));
		return e;
	}
	

}
