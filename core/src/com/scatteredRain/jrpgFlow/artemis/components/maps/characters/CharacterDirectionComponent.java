package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;

@Data
@AllArgsConstructor
public class CharacterDirectionComponent extends Component{
	
	/** The Direction The Movement is being done */
	private int direction;
	
}
