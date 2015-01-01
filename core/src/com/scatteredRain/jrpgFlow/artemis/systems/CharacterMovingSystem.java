package com.scatteredRain.jrpgFlow.artemis.systems;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Linear;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import static com.scatteredRain.jrpgFlow.Constants.*;

import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMovingComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.util.Point;

//TODO: Finish This Class!

@Wire
public class CharacterMovingSystem extends EntitySystem{
	
	ComponentMapper<DesiredCharacterMovementComponent> desMoveComp;
	ComponentMapper<CharacterMovingComponent> isMoveComp;
	ComponentMapper<CharacterLocationComponent> locationComp;
	
	ComponentMapper<CharacterSpriteLocationComponent> spriteLocationComp;
	
	ComponentMapper<MapCollisionComponent> mapCollComp;

	public CharacterMovingSystem() {
		super(Aspect.getAspectForOne(DesiredCharacterMovementComponent.class, CharacterMovingComponent.class, CharacterLocationComponent.class, MapCollisionComponent.class));
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
					if(map.isTraversible(x, y, direction)){
						//Actually Init Movement
						desMoveComp.get(e).setStationary();
						isMoveComp.get(e).setMoving(true);
						Point dest = calcTarget(x, y, direction);
						//Set Map Location To Destination
						locationComp.get(e).setX(dest.getX());
						locationComp.get(e).setY(dest.getY());
						
						System.out.println(x+" "+y+" | "+dest.getX()+" "+dest.getY());
						
						//Init Sprite Move (Tween)
						if(spriteLocationComp.has(e)){
							Tween.to(spriteLocationComp.get(e).getLocation(), 0, 1f).target(dest.getX()*TILE_SIZE+TILE_SIZE*0.5f, dest.getY()*TILE_SIZE).ease(Linear.INOUT).start(TWEEN_MANAGER);
						}
						
						//TODO: Send Event To Sprite Movement System
						//TODO: Send Event To Map Collision Update System
						
					}
				}
			}
		}
	}

}
