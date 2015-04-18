package com.scatteredRain.jrpgFlow.action.coreAction;

import lombok.AllArgsConstructor;

import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.CharacterAction;

/** Action That Calls The Reset Method Of The Character Action Given To It */
@AllArgsConstructor
public class ResetCharacterAction implements Action{
	
	private CharacterAction characterAction;

	@Override
	public void act() {
		characterAction.reset();
	}
	
}
