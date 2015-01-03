package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.general.PlayerCharacterInput;

/** Defines Entity As Active Player Character, Manages Player Inputs */
@Data
@AllArgsConstructor
public class PlayerCharacterComponent extends Component{
	
	private PlayerCharacterInput input;
	
}
