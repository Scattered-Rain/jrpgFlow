package com.scatteredRain.jrpgFlow.artemis.systems;

import lombok.Data;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCharacterListComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;

/** This System is supposed to never be actively used, instead it is supposed to be an easily reachable object for accessing Component Mappers */
@Data
@Wire
public class PassiveCharacterActionSystem extends EntitySystem{
	
	/** Component Mappers To Provide Easy Access To Components */
	ComponentMapper<CharacterLocationComponent> locationComp;
	ComponentMapper<CharacterDirectionComponent> directionComp;
	ComponentMapper<MapCharacterListComponent> charListComp;
	ComponentMapper<PlayerCharacterComponent> playerCharComp;
	
	/** Easy Access To Certain Entities */
	private Entity player;
	
	
	/** Constructor */
	public PassiveCharacterActionSystem() {
		super(Aspect.getAspectForOne(PlayerCharacterComponent.class));
		this.setEnabled(false);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
	}
	
	/** Adding All Interesting Entities To Make Them Quickly Available */
	public void inserted(Entity e){
		super.inserted(e);
		if(playerCharComp.has(e)){
			this.player = e;
		}
	}

}
