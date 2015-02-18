package com.scatteredRain.jrpgFlow.action.coreAction;

import lombok.AllArgsConstructor;

import com.artemis.Entity;
import com.artemis.World;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.artemis.systems.map.PassiveCharacterActionSystem;

@AllArgsConstructor
public class Turning implements Action{
	
	private Entity target;
	private int dir;

	@Override
	public void act() {
		target.getWorld().getSystem(PassiveCharacterActionSystem.class).getDirectionComp().get(target).setDirection(dir);
	}

}
