package com.scatteredRain.jrpgFlow.action.coreAction;

import lombok.AllArgsConstructor;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.artemis.systems.map.PlayerCharacterInputSystem;

@AllArgsConstructor
public class PlayerInputPossible implements Action{
	
	private boolean playerInputPossible;
	
	@Override
	public void act() {
		globalActiveWorldsList.getWorld(globalActiveWorldsList.MAP_WORLD).getSystem(PlayerCharacterInputSystem.class).setPlayerActionEnabled(playerInputPossible);
	}
	
}
