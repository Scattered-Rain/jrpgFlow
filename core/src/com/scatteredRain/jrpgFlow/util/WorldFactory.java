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
import com.scatteredRain.jrpgFlow.artemis.components.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.TileMapRenderComponent;
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
		Entity e = world.createEntity();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(1, h/w);
		camera.position.x = 16;
		camera.position.y = 16;
		camera.zoom = 15f;
		camera.update();
		e.addComponent(new OrthographicCameraComponent(camera));
		TmxMapLoader mapLoader = new TmxMapLoader();
		Parameters mapLoadParams = new Parameters();
		TiledMap map = mapLoader.load("maps/first.tmx");
		e.addComponent(new MapComponent(map));
		//TODO: Get Tile size Into Some Constants Class
		OrthogonalTiledMapRenderer mapRenderer  = new OrthogonalTiledMapRenderer(map, 1f/16f);
		e.addComponent(new TileMapRenderComponent(mapRenderer));
		
		return world;
	}

}
