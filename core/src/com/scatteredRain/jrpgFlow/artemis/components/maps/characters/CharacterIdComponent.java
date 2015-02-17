package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;

@Data
@AllArgsConstructor
/** Component Uniquely Defining The Character On The Specific Map Using ID And TYPE */
public class CharacterIdComponent extends Component{
	
	private String type;
	private int id;
	
}
