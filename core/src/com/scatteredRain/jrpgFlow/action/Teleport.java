package com.scatteredRain.jrpgFlow.action;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalActiveWorldsList;
import lombok.AllArgsConstructor;

import com.scatteredRain.jrpgFlow.enums.MapID;
import com.scatteredRain.jrpgFlow.general.AwlRequest;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

//TODO: Finish!
@AllArgsConstructor
public class Teleport implements Action, AwlRequest{
	
	private MapID targetMap;
	private int targetEnterID;

	@Override
	public void act() {
		globalActiveWorldsList.sendRequest(this);
	}

	@Override
	public void doRequest() {
		globalActiveWorldsList.switchMaps(targetMap, targetEnterID);
	}
	
	
}
