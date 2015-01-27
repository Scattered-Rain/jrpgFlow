package com.scatteredRain.jrpgFlow.action;

import lombok.AllArgsConstructor;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

@AllArgsConstructor
public class Textboxing implements Action{
	
	private String text;

	@Override
	public void act() {
		globalActiveWorldsList.startTextbox(text, 2, true);
	}

}
