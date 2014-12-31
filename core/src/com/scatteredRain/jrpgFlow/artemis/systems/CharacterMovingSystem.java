package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterInMoveComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.util.Point;

@Wire
public class CharacterMovingSystem extends EntitySystem{
	
	ComponentMapper<DesiredCharacterMovementComponent> desMoveComp;
	ComponentMapper<CharacterInMoveComponent> isMoveComp;
	ComponentMapper<CharacterLocationComponent> locationComp;
	
	ComponentMapper<MapCollisionComponent> mapCollComp;

	public CharacterMovingSystem() {
		super(Aspect.getAspectForOne(DesiredCharacterMovementComponent.class, CharacterInMoveComponent.class, CharacterLocationComponent.class, MapCollisionComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		MapCollisionComponent map = null;
		for(Entity e : entities){
			if(mapCollComp.has(e)){
				map = mapCollComp.get(e);
				break;
			}
		}
		for(Entity e : entities){
			if(desMoveComp.has(e) && isMoveComp.has(e) && locationComp.has(e)){
				if(!isMoveComp.get(e).isMoving() && desMoveComp.get(e).desiresToMove()){
					int x = locationComp.get(e).getX();
					int y = locationComp.get(e).getY();
					int direction = desMoveComp.get(e).getDesiredDirection();
					if(map.isTraversible(x, x, direction)){
						//Actually Init Movement
						desMoveComp.get(e).setStationary();
						isMoveComp.get(e).setMoving(true);
						Point dest = calcTarget(x, y, direction);
						locationComp.get(e).setX(dest.getX());
						locationComp.get(e).setY(dest.getY());
						//TODO: Send Event To Sprite Movement System
						//TODO: Send Event To Map Collision Update System
					}
				}
			}
		}
	}

}
