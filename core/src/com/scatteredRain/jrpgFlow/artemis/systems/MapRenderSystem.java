package com.scatteredRain.jrpgFlow.artemis.systems;

import java.util.ArrayList;
import java.util.Iterator;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;

/** System Designated To Render All Maps In World */
@Wire
public class MapRenderSystem extends EntitySystem{

	ComponentMapper<TileMapRenderComponent> mapRenderComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	
	/** Whether This System Should Render The Top Layers Of The Map Or Everything but */
	private boolean renderTop;
	
	public MapRenderSystem(boolean renderTop) {
		super(Aspect.getAspectForOne(TileMapRenderComponent.class, OrthographicCameraComponent.class));
		this.renderTop = renderTop;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		//Retrieving Camera And Map
		OrthographicCamera camera = null;
		for(Entity e : entities){
			if(cameraComp.has(e)){
				camera = cameraComp.get(e).getCamera();
				break;
			}
		}
		//Find Entities With MapRenderer, And Render Them
		for(Entity e : entities){
			if(mapRenderComp.has(e)){
				TileMapRenderComponent mapRend = mapRenderComp.get(e);
				//Prepare Layer List
				int[] renderList = new int[0];
				if(renderTop){
					renderList = mapRend.getTopLayers();
				}
				else{
					renderList = mapRend.getGroundLayers();
				}
				//Prepare MapRenderer And Render
				OrthogonalTiledMapRenderer mapRenderer = mapRend.getMapRenderer();
				mapRenderer.setView(camera);
				mapRenderer.render(renderList);
			}
		}
	}

}
