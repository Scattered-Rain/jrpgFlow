package com.scatteredRain.jrpgFlow.action;

import com.scatteredRain.jrpgFlow.general.AwlRequest;

import lombok.AllArgsConstructor;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

@AllArgsConstructor
public class Textboxing implements Action, AwlRequest{
	
	private String text;

	@Override
	public void act() {
		globalActiveWorldsList.sendRequest(this);
	}

	@Override
	public void doRequest() {
		globalActiveWorldsList.startTextbox(text, 2, true);
	}

}
