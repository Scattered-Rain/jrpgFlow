package com.scatteredRain.jrpgFlow.artemis.systems;

import static com.scatteredRain.jrpgFlow.Constants.TILE_SIZE;
import static com.scatteredRain.jrpgFlow.Constants.TWEEN_MANAGER;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CameraFocusComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;

/** Focuses The Camera On The Character The The Camera Focus Component Is Attached To */
@Wire
public class MapPlayerCameraFocusSystem extends EntitySystem{
	
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	ComponentMapper<CharacterLocationComponent> locationComp;
	ComponentMapper<CharacterMoveProgressionComponent> moveComp;
	ComponentMapper<CameraFocusComponent> cameraFocusComp;

	public MapPlayerCameraFocusSystem() {
		super(Aspect.getAspectForOne(OrthographicCameraComponent.class, CameraFocusComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		OrthographicCamera camera = null;
		for(Entity e : entities){
			if(cameraComp.has(e)){
				camera = cameraComp.get(e).getCamera();
			}
		}
		for(Entity e : entities){
			if(cameraFocusComp.has(e) && locationComp.has(e) && moveComp.has(e)){
				if(moveComp.get(e).justStarted()){
					Tween.to(camera, 0, moveComp.get(e).getTotalNeededTime())
							.target(locationComp.get(e).getX()*TILE_SIZE+TILE_SIZE*0.5f, locationComp.get(e).getY()*TILE_SIZE+TILE_SIZE*0.5f)
							.ease(Linear.INOUT)
							.start(TWEEN_MANAGER);
				}
			}
		}
	}

}
