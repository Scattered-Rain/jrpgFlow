package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.general.Animation;

@Wire
public class MapCharacterSpriteUpdateSystem extends EntitySystem{
	
	ComponentMapper<CharacterDirectionComponent> dirComp;
	ComponentMapper<ActiveCharacterSpriteAnimationComponent> animComp;
	ComponentMapper<MapCharacterAnimationSetComponent> animSetComp;
	ComponentMapper<CharacterMoveProgressionComponent> isMoveComp;
	
	public MapCharacterSpriteUpdateSystem() {
		super(Aspect.getAspectForAll(CharacterDirectionComponent.class, ActiveCharacterSpriteAnimationComponent.class, MapCharacterAnimationSetComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(Entity e : entities){
			//See What The 'New' Animation Should Be
			Animation newAnim = null;
			if(isMoveComp.has(e) && isMoveComp.get(e).isMoving() && animSetComp.get(e).hasWalking()){
				newAnim = animSetComp.get(e).getActiveWalking(dirComp.get(e).getDirection());
			}
			else if(animSetComp.get(e).hasStanding()){
				newAnim = animSetComp.get(e).getActiveStanding(dirComp.get(e).getDirection());
			}
			else if(animSetComp.get(e).hasSingle()){
				newAnim = animSetComp.get(e).getActiveSingle();
			}
			//Check Whether Current Animation Is Equal To New Animation, If Not Change
			if(!animComp.get(e).getAnimation().equals(newAnim) && newAnim!=null){
				animComp.get(e).setNewAnimation(newAnim);
			}
		}
	}

}
