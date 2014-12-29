package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;

@Data
@AllArgsConstructor
public class CharacterLocationComponent extends Component{
	
	private int x;
	private int y;

}
