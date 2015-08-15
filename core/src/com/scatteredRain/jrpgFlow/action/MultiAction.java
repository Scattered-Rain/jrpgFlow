package com.scatteredRain.jrpgFlow.action;

import java.util.ArrayList;

/** Action That Executes Several Actions When Executed */
public class MultiAction implements Action{
	
	private Action[] actions;
	
	public MultiAction(Action ... actions){
		this.actions = actions;
	}
	
	public MultiAction(ArrayList<Action> actions){
		this.actions = new Action[actions.size()];
		for(int c=0; c<actions.size(); c++){
			this.actions[c] = actions.get(c);
		}
	}
	
	@Override
	public void act() {
		for(Action action : actions){
			action.act();
		}
	}
	
}
