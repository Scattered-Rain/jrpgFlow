package com.scatteredRain.jrpgFlow.action.coreAction;

import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.general.AwlRequest;
import com.scatteredRain.jrpgFlow.general.CompletionListener;

import lombok.AllArgsConstructor;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

public class Textboxing implements Action, AwlRequest{
	
	private String text;
	private CompletionListener completionListener;
	
	/** Requests New, Simple Textbox With Given Text */
	public Textboxing(String text){
		this.text = text;
	}
	
	/** Inject a CompletionListener */
	public void injectCompletionListener(CompletionListener completionListener){
		this.completionListener = completionListener;
	}
	

	@Override
	public void act() {
		globalActiveWorldsList.sendRequest(this);
	}

	@Override
	public void doRequest() {
		if(completionListener!=null){
			globalActiveWorldsList.startTextbox(text, 2, true, completionListener);
		}
		else{
			globalActiveWorldsList.startTextbox(text, 2, true);
		}
	}

}
