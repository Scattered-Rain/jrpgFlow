package com.scatteredRain.jrpgFlow.artemis.systems.map;

import lombok.Data;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterIdComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MovementAIComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapIdComponent;
import com.scatteredRain.jrpgFlow.enums.MapID;

/** This System is supposed to never be actively used, instead it is supposed to be an easily reachable object for accessing Component Mappers */
@Data
@Wire
public class PassiveCharacterActionSystem extends EntitySystem{
	
	/** Component Mappers To Provide Easy Access To Components */
	ComponentMapper<CharacterLocationComponent> locationComp;
	ComponentMapper<CharacterDirectionComponent> directionComp;
	ComponentMapper<MapCharacterListComponent> charListComp;
	ComponentMapper<PlayerCharacterComponent> playerCharComp;
	ComponentMapper<CharacterIdComponent> idComp;
	ComponentMapper<MovementAIComponent> movementAIComp;
	
	ComponentMapper<MapComponent> mapComp;
	ComponentMapper<MapCharacterListComponent> mapCharListComp;
	ComponentMapper<MapIdComponent> mapId;
	
	/** Easy Access To Certain Entities */
	private Entity player;
	private Entity map;
	
	
	/** Constructor */
	public PassiveCharacterActionSystem() {
		super(Aspect.getAspectForOne(PlayerCharacterComponent.class, MapCharacterListComponent.class));
		this.setEnabled(false);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {}
	
	/** Adding All Interesting Entities To Make Them Quickly Available */
	public void inserted(Entity e){
		super.inserted(e);
		if(playerCharComp.has(e)){
			this.player = e;
		}
		if(mapCharListComp.has(e)){
			this.map = e;
		}
	}
	
	/** Retrns The Current Maps Id */
	public MapID getMapId(){
		return mapId.get(map).getId();
	}
	
	//Utility--
	/** Returns The Entity With The Given Type And Id, null If It Doesn't Exist */
	public Entity findEntity(String type, int id){
		//TODO: Make This Method!
		return null;
	}
	
	
	
}
