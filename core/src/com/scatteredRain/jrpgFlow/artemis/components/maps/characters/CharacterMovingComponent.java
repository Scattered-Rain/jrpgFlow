package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import com.artemis.Component;


//TODO: Implement!
/** Whether Character currently is moving, how fast he is moving (time to complete sprite movement from tile a to tile b) and the collected delta since the start of the characters movement */
@Data
public class CharacterMovingComponent extends Component{
	
	private boolean moving;
	
	public CharacterMovingComponent(){
		this.moving = false;
	}

}
