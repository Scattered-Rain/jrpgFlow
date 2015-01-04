package com.scatteredRain.jrpgFlow.artemis.components.maps;

import java.util.Iterator;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

@Data
public class MapComponent extends Component{
	
	/** Tiled Map */
	private TiledMap map;
	
	private int width;
	private int height;
	
	public MapComponent(TiledMap map){
		this.map = map;
		findDimensions();
	}
	
	//Sets Width And Height
	private void findDimensions(){
		boolean done = false;
		Iterator<MapLayer> iterator = map.getLayers().iterator();
		while(iterator.hasNext() && !done){
			MapLayer layer = iterator.next();
			if(layer instanceof TiledMapTileLayer){
				TiledMapTileLayer tileLayer = (TiledMapTileLayer)layer;
				this.width = tileLayer.getWidth();
				this.height = tileLayer.getHeight();
			}
		}
	}

}
