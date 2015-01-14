package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;

@Wire
public class PlayerCharacterInputSystem extends EntitySystem{

	ComponentMapper<PlayerCharacterComponent> playerComp;
	ComponentMapper<DesiredCharacterMovementComponent> dirComp;
	
	//Indicates Whether The Currently Registered Action Button Press Was Already Used For Triggering Something Else
	private boolean actionUsed;
	
	
	public PlayerCharacterInputSystem() {
		super(Aspect.getAspectForAll(PlayerCharacterComponent.class, DesiredCharacterMovementComponent.class));
		this.actionUsed = false;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(Entity e : entities){
			PlayerCharacterComponent player = playerComp.get(e);
			//Player Movement
			if(player.getInput().hasDirection()){
				dirComp.get(e).setDesiredDirection(player.getInput().getDirection());
			}
			else{
				dirComp.get(e).setStationary();
			}
			//Player Action Button
			if(player.getInput().getAction() && !actionUsed){
				//TODO: Add Check For Character For Interaction Here!
				System.out.println("Action!");
				actionUsed = true;
			}
			else if(!player.getInput().getAction()){
				actionUsed = false;
			}
			
		}
	}

}
