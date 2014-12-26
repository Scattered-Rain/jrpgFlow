package com.scatteredRain.jrpgFlow.artemis.components;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

@Data
@AllArgsConstructor
public class MapComponent extends Component{
	
	/** Tiled Map */
	private TiledMap map;

}
