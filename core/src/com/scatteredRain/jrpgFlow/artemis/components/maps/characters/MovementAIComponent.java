package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import com.artemis.Component;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.general.aiMovement.AIMovement;

/** Component Used To Control NPC Movement, if applicable */
@Data
public class MovementAIComponent extends Component{
	
	//Active AI Movement Component
	private AIMovement aiMovement;
	
	public int getAISteering(){
		if(aiMovement!=null){
			return aiMovement.desiredDirection();
		}
		return STATIONARY;
	}
	
}
