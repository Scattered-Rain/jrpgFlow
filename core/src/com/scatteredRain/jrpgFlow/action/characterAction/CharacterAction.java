package com.scatteredRain.jrpgFlow.action.characterAction;

import java.util.Random;

import com.artemis.Entity;
import com.artemis.World;
import com.scatteredRain.jrpgFlow.Constants;
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
	
	//Utitlity Methods
	/** Returns The Adjacent Direction The Player Is Standing In Relation To This Event's Location, If None Is Applicable -1 */
	protected int getOwnerToPlayerDirection(){
		if(accessor.getLocationComp().has(owner)){
			int x = accessor.getLocationComp().get(owner).getX();
			int y = accessor.getLocationComp().get(owner).getY();
			int pX = accessor.getLocationComp().get(accessor.getPlayer()).getX();
			int pY = accessor.getLocationComp().get(accessor.getPlayer()).getY();
			return Constants.calcAdjacencyDirection(x, y, pX, pY);
		}
		return -1;
	}
	
}
