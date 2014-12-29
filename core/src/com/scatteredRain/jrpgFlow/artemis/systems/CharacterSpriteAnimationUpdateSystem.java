package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.general.Animation;

@Wire
public class CharacterSpriteAnimationUpdateSystem extends EntitySystem{
	
	ComponentMapper<ActiveCharacterSpriteComponent> spriteComp;
	ComponentMapper<ActiveCharacterSpriteAnimationComponent> animComp;
	
	
	public CharacterSpriteAnimationUpdateSystem() {
		super(Aspect.getAspectForOne(ActiveCharacterSpriteComponent.class, ActiveCharacterSpriteAnimationComponent.class));
	}
	
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(Entity e : entities){
			if(spriteComp.has(e) && animComp.has(e)){
				Animation ani = animComp.get(e).getAnimation();
				ani.addDelta(world.delta);
				spriteComp.get(e).setActiveSprite(ani.currentFrame());
			}
		}
	}
	
}
