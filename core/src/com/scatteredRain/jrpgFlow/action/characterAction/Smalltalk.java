package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.CharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.AddMovement;
import com.scatteredRain.jrpgFlow.action.coreAction.PlayerInputPossible;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.action.coreAction.Turning;
import com.scatteredRain.jrpgFlow.general.aiMovement.AIMovement;
import com.scatteredRain.jrpgFlow.general.aiMovement.FollowWaypointsMovement;

public class Smalltalk extends CharacterAction{
	
	private Action textbox;
	
	private Action movement;
	
	public Smalltalk(Entity entity, String text) {
		super(entity);
		this.textbox = new Textboxing(text);
		
		//TODO: Find out why this doesn't work, fix it, and remove this code from he Smalltalk Action :p
		this.movement = new AddMovement(entity, new FollowWaypointsMovement(entity, 0));
		
		
	}
	
	public void act(){
		super.act();
		Action turning = new Turning(super.owner, getOwnerToPlayerDirection());
		textbox.act();
		turning.act();
		movement.act();
	}
	
}
