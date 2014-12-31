package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import com.artemis.Component;

/** Whether Character Is currently moving between Tile A to Tile B */
@Data
public class CharacterInMoveComponent extends Component{
	
	private boolean moving;
	
	public CharacterInMoveComponent(){
		this.moving = false;
	}

}
