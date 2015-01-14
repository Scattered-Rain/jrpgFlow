package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;

@Data
@AllArgsConstructor
public class CharacterCollisionComponent extends Component{
	
	private boolean collidable;

}
