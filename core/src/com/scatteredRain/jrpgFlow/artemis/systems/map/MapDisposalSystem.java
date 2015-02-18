package com.scatteredRain.jrpgFlow.artemis.systems.map;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.TileMapRenderComponent;

/** This System Disposes Of Everything On A Map Change */
public class MapDisposalSystem extends EntitySystem{
	
	ComponentMapper<ActiveCharacterSpriteComponent> spriteComp;
	ComponentMapper<ActiveCharacterSpriteAnimationComponent> animComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	ComponentMapper<MapCharacterAnimationSetComponent> animSetComp;
	ComponentMapper<TileMapRenderComponent> mapRenderComp;

	public MapDisposalSystem() {
		super(Aspect.getAspectForOne());
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		System.out.println("Hello");
	}
	
	protected void dispose(){
		super.dispose();
		
	}

}
