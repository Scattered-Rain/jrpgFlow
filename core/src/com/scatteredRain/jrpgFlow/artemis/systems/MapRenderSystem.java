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
import com.scatteredRain.jrpgFlow.artemis.components.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.TileMapRenderComponent;

@Wire
public class MapRenderSystem extends EntitySystem{
	
	/** The String That Identifies A Layer As A Top Layer */
	private static final String TOP_LAYER_PROPERTY = "top";

	ComponentMapper<TileMapRenderComponent> mapRenderComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	ComponentMapper<MapComponent> mapComp;
	
	/** Whether This System Should Render The Top Layers Of The Map Or Everything but */
	private boolean renderTop;
	
	public MapRenderSystem(boolean renderTop) {
		super(Aspect.getAspectForOne(TileMapRenderComponent.class, OrthographicCameraComponent.class, MapComponent.class));
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
		//Find Entities With Map And MapRenderer, And Render Them
		for(Entity e : entities){
			//NOTE: Assertion That The Entity Holding A Map Is Also Holding Its Map Renderer
			if(mapRenderComp.has(e) && mapComp.has(e)){
				//Find Layers To Render Of Map
				TiledMap map = mapComp.get(e).getMap();
				Iterator<MapLayer> iterator = map.getLayers().iterator();
				ArrayList<Integer> layersToRender = new ArrayList<Integer>();
				int counter = -1;
				while(iterator.hasNext()){
					counter++;
					MapLayer layer = iterator.next();
					if(layer instanceof TiledMapTileLayer){
						TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;
						if(tileLayer.isVisible()){
							if(tileLayer.getProperties().containsKey(TOP_LAYER_PROPERTY)){
								if(renderTop){
									layersToRender.add(counter);
								}
							}
							else if(!renderTop){
								layersToRender.add(counter);
							}
						}
					}
				}
				int[] renderList = new int[layersToRender.size()];
				for(int c=0; c<renderList.length; c++){
					renderList[c] = layersToRender.get(c);
				}
				//Prepare MapRenderer And Render
				OrthogonalTiledMapRenderer mapRenderer = mapRenderComp.get(e).getMapRenderer();
				mapRenderer.setView(camera);
				mapRenderer.render(renderList);
			}
		}
	}

}
