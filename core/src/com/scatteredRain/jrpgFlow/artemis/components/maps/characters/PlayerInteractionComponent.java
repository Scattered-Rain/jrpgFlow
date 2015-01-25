package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.action.Action;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO: Make This Legit!

@Data
@AllArgsConstructor
public class PlayerInteractionComponent extends Component{
	
	private Action talking;
	private Action looking;
	private Action touching;
	private Action pushing;
	
	public boolean hasTalking(){
		return talking != null;
	}
	
	public boolean hasLooking(){
		return looking != null;
	}
	
	public boolean hasTouching(){
		return touching != null;
	}
	
	public boolean hasPushing(){
		return pushing != null;
	}

}
