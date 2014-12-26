package com.scatteredRain.jrpgFlow.artemis.components;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

@Data
@AllArgsConstructor
public class TileMapRenderComponent extends Component{
	
	private OrthogonalTiledMapRenderer mapRenderer;

}
