package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.ActionCompletionListener;
import com.scatteredRain.jrpgFlow.action.CharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.AddMovement;
import com.scatteredRain.jrpgFlow.action.coreAction.ChangeCharacterVariable;
import com.scatteredRain.jrpgFlow.action.coreAction.PlayerInputPossible;
import com.scatteredRain.jrpgFlow.action.coreAction.ResetCharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.action.coreAction.Turning;
import com.scatteredRain.jrpgFlow.general.aiMovement.AIMovement;
import com.scatteredRain.jrpgFlow.general.aiMovement.FollowWaypointsMovement;

public class Smalltalk extends CharacterAction{
	
	private Textboxing textbox;
	private AddMovement movement;
	
	private String text;
	
	public Smalltalk(Entity entity, String text) {
		super(entity);
		this.text = text;
		setup();
	}
	
	public void setup(){
		
		this.textbox = new Textboxing(text);
		
		//TODO: Remove this code from he Smalltalk Action
		AIMovement ai = new FollowWaypointsMovement(owner, 0);
		this.movement = new AddMovement(owner, ai);
		
		ActionCompletionListener acl = new ActionCompletionListener(movement);
		textbox.injectCompletionListener(acl);
		
		ResetCharacterAction reset = new ResetCharacterAction(this);
		ai.injectCompletionListener(new ActionCompletionListener(reset));
		
	}
	
	public void act(){
		if(allowAct){
			super.act();
			Action turning = new Turning(super.owner, getOwnerToPlayerDirection());
			turning.act();
			textbox.act();
			allowAct = false;
		}
	}
	
}
