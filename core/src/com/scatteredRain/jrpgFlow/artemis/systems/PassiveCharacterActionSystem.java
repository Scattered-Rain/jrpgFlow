package com.scatteredRain.jrpgFlow.artemis.systems;

import lombok.Data;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;

/** This System is supposed to never be actively used, instead it is supposed to be an easily reachable object for accessing Component Mappers */
@Data
@Wire
public class PassiveCharacterActionSystem extends EntitySystem{
	
	
	ComponentMapper<CharacterLocationComponent> locationComp;
	ComponentMapper<CharacterDirectionComponent> directionComp;
	
	
	/** Constructor */
	public PassiveCharacterActionSystem() {
		super(Aspect.getEmpty());
		this.setEnabled(false);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		//This is supposed to NEVER be called
	}

}
