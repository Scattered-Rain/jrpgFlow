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
	
	public PlayerCharacterInputSystem() {
		super(Aspect.getAspectForAll(PlayerCharacterComponent.class, DesiredCharacterMovementComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(Entity e : entities){
			PlayerCharacterComponent player = playerComp.get(e);
			if(player.getInput().hasDirection()){
				dirComp.get(e).setDesiredDirection(player.getInput().getDirection());
			}
			else{
				dirComp.get(e).setStationary();
			}
		}
	}

}
