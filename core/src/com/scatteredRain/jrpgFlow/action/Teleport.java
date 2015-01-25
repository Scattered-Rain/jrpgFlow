package com.scatteredRain.jrpgFlow.action;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalActiveWorldsList;
import lombok.AllArgsConstructor;

import com.scatteredRain.jrpgFlow.Constants.MapID;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

//TODO: Finish!
@AllArgsConstructor
public class Teleport implements Action{
	
	private MapID targetMap;
	private int targetEnterID;

	@Override
	public void act() {
		globalActiveWorldsList.switchMaps(targetMap, targetEnterID);
	}
	
	
}
