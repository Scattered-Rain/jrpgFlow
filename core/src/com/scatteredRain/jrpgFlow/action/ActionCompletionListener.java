package com.scatteredRain.jrpgFlow.action;

import lombok.AllArgsConstructor;

import com.scatteredRain.jrpgFlow.general.CompletionListener;

/** Completion Listener That Executes An Action When It Receives Completion */
@AllArgsConstructor
public class ActionCompletionListener implements CompletionListener{
	
	private Action action;
	
	public void completed() {
		action.act();
	}
	
}
