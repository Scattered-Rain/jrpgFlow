package com.scatteredRain.jrpgFlow.action.characterAction;

import com.artemis.Entity;
import com.artemis.World;
import com.scatteredRain.jrpgFlow.artemis.systems.PassiveCharacterActionSystem;
import com.scatteredRain.jrpgFlow.action.Action;

/** An Abstract Action Specifically Tailored To The Needs Of Map Characters */
public abstract class CharacterAction implements Action{
	
	/** The Owner Of This Action */
	protected Entity owner;
	/** The World In Question */
	protected World world;
	/** Reference To The Generic System Providing Access To Component Mappers */
	protected PassiveCharacterActionSystem accessor;
	
	/** Constructor */
	public CharacterAction(Entity entity){
		this.owner = entity;
		this.world = entity.getWorld();
		this.accessor = world.getSystem(PassiveCharacterActionSystem.class);
	}
	
	/** Generic Act Function */
	public void act(){}
	
}
