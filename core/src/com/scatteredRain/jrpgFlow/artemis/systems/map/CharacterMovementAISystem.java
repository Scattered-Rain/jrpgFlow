package com.scatteredRain.jrpgFlow.artemis.systems.map;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MovementAIComponent;

/** System that translates Movement AI of an entity into desired direction */
@Wire
public class CharacterMovementAISystem extends EntitySystem{
	
	ComponentMapper<MovementAIComponent> aiComp;
	ComponentMapper<DesiredCharacterMovementComponent> desMovComp;
	
	public CharacterMovementAISystem() {
		super(Aspect.getAspectForAll(MovementAIComponent.class, DesiredCharacterMovementComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(Entity e : entities){
			int desiredDirection = aiComp.get(e).getAISteering();
			desMovComp.get(e).setDesiredDirection(desiredDirection);
		}
	}
	
	
	
}
