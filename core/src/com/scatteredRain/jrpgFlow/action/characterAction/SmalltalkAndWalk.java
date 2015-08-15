package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.ActionCompletionListener;
import com.scatteredRain.jrpgFlow.action.CharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.AddMovement;
import com.scatteredRain.jrpgFlow.action.coreAction.PlayerInputPossible;
import com.scatteredRain.jrpgFlow.action.coreAction.ResetCharacterAction;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.action.coreAction.Turning;
import com.scatteredRain.jrpgFlow.general.aiMovement.AIMovement;
import com.scatteredRain.jrpgFlow.general.aiMovement.FollowWaypointsMovement;

public class SmalltalkAndWalk extends CharacterAction{
	
	private Textboxing textbox;
	private AddMovement movement;
	
	private String text;
	private String text2;
	
	private boolean walked;
	
	public SmalltalkAndWalk(Entity entity, String text, String text2) {
		super(entity);
		this.text = text;
		this.text2 = text2;
		this.walked = false;
		setup();
	}
	
	public void setup(){
		
		if(!walked){
			this.textbox = new Textboxing(text);
			
			AIMovement ai = new FollowWaypointsMovement(owner, 0);
			this.movement = new AddMovement(owner, ai);
			
			ActionCompletionListener acl = new ActionCompletionListener(movement);
			textbox.injectCompletionListener(acl);
			
			ResetCharacterAction reset = new ResetCharacterAction(this);
			ai.injectCompletionListener(new ActionCompletionListener(reset));
		}
		else{
			this.textbox = new Textboxing(text2);
		}
		
		
	}
	
	public void act(){
		if(allowAct){
			this.walked = true;
			super.act();
			Action turning = new Turning(super.owner, getOwnerToPlayerDirection());
			turning.act();
			textbox.act();
			allowAct = true;
		}
	}
	
}
