package com.scatteredRain.jrpgFlow.action;

/** Action That Executes Several Actions When Executed */
public class MultiAction implements Action{
	
	private Action[] actions;
	
	public MultiAction(Action ... actions){
		this.actions = actions;
	}
	
	@Override
	public void act() {
		for(Action action : actions){
			action.act();
		}
	}
	
}
