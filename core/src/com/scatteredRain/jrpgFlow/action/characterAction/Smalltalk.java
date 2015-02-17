package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.CharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.action.coreAction.Turning;

public class Smalltalk extends CharacterAction{
	
	private Action textbox;

	public Smalltalk(Entity entity, String text) {
		super(entity);
		this.textbox = new Textboxing(text);
	}
	
	public void act(){
		super.act();
		Action turning = new Turning(super.owner, getOwnerToPlayerDirection());
		textbox.act();
		turning.act();
	}

}
