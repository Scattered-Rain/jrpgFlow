package com.scatteredRain.jrpgFlow.action.coreAction;

import lombok.AllArgsConstructor;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.artemis.systems.map.PassiveCharacterActionSystem;
import com.scatteredRain.jrpgFlow.general.aiMovement.AIMovement;

/** Adds A Movement AI To The Entity */
@AllArgsConstructor
public class AddMovement implements Action{
	
	private Entity target;
	private AIMovement movementAi;
	
	@Override
	public void act() {
		target.getWorld().getSystem(PassiveCharacterActionSystem.class).getMovementAIComp().get(target).setAiMovement(movementAi);
	}

}
