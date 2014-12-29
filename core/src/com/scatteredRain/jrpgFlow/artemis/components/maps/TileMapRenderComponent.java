package com.scatteredRain.jrpgFlow.artemis.components.maps;

import java.util.ArrayList;
import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import static com.scatteredRain.jrpgFlow.Constants.*;

/** Holds The TileMapRenderer Map, As Well As The Information Of Which Layers To Render */
@Data
@AllArgsConstructor
public class TileMapRenderComponent extends Component{
	
	private OrthogonalTiledMapRenderer mapRenderer;
	
	//The indexes of the maps layers that should be rendered by the mapRenderer
	private int[] groundLayers;
	private int[] topLayers;
	
	
	public TileMapRenderComponent(TiledMap map){
		//Create Map Renderer
		//TODO: Get Tile size Into Some Constants Class
		this.mapRenderer  = new OrthogonalTiledMapRenderer(map, 1);
		//Figure Out The Layers That Shall Be Rendered
		Iterator<MapLayer> iterator = map.getLayers().iterator();
		ArrayList<Integer> layersGround = new ArrayList<Integer>();
		ArrayList<Integer> layersTop = new ArrayList<Integer>();
		int counter = -1;
		while(iterator.hasNext()){
			counter++;
			MapLayer layer = iterator.next();
			if(layer instanceof TiledMapTileLayer){
				TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;
				if(tileLayer.isVisible()){
					if(tileLayer.getProperties().containsKey(TOP_LAYER_PROPERTY)){
						layersTop.add(counter);
					}
					else{
						layersGround.add(counter);
					}
				}
			}
		}
		//Add Ground Layers
		this.groundLayers = new int[layersGround.size()];
		for(int c=0; c<this.groundLayers.length; c++){
			this.groundLayers[c] = layersGround.get(c);
		}
		//Add Top Layers
		this.topLayers = new int[layersTop.size()];
		for(int c=0; c<this.topLayers.length; c++){
			this.topLayers[c] = layersTop.get(c);
		}
	}

}
