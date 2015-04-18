package com.scatteredRain.jrpgFlow.artemis.components.maps.map;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.enums.MapID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapIdComponent extends Component{
	
	private MapID id;
	
}
