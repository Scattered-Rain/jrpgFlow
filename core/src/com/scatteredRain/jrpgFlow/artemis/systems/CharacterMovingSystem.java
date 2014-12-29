package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

public class CharacterMovingSystem extends EntitySystem{

	public CharacterMovingSystem() {
		super(Aspect.getAspectForOne());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
	}

}
