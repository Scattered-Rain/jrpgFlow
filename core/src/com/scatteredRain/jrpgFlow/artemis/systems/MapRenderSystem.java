package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.scatteredRain.jrpgFlow.artemis.components.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;

@Wire
public class MapRenderSystem extends EntitySystem{

	ComponentMapper<MapComponent> mapComp;
	ComponentMapper<OrthographicCameraComponent>cameraComp;
	
	public MapRenderSystem() {
		super(Aspect.getAspectForOne(MapComponent.class, OrthographicCameraComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		OrthographicCamera camera = null;
		for(Entity e : entities){
			if(cameraComp.has(e)){
				camera = cameraComp.get(e).getCamera();
				break;
			}
		}
		for(Entity e : entities){
			if(mapComp.has(e)){
				TiledMap map = mapComp.get(e).getMap();
				//TODO: Outsource Tile Size To Constants Class
				//TODO: Make TiledMapRenderer Own Component
				OrthogonalTiledMapRenderer mapRenderer  = new OrthogonalTiledMapRenderer(map, 1f/16f);
				mapRenderer.setView(camera);
				mapRenderer.render();
			}
		}
	}

}
