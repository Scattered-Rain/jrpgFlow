package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.Textboxing;

public class Smalltalk extends CharacterAction{
	
	private Action textbox;

	public Smalltalk(Entity entity, String text) {
		super(entity);
		this.textbox = new Textboxing(text);
	}
	
	public void act(){
		super.act();
		textbox.act();
		//TODO: Find & Set To Player
		super.accessor.getDirectionComp().get(super.owner).setDirection(2);
	}

}
