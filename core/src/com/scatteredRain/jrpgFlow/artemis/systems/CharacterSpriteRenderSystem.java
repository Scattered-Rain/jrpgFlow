package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;

@Wire
public class CharacterSpriteRenderSystem extends EntitySystem{
	
	ComponentMapper<ActiveCharacterSpriteComponent> spriteComp;
	ComponentMapper<CharacterSpriteLocationComponent> locationComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;

	public CharacterSpriteRenderSystem() {
		super(Aspect.getAspectForOne(ActiveCharacterSpriteComponent.class, CharacterSpriteLocationComponent.class, OrthographicCameraComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		// TODO Auto-generated method stub
		
	}

}
