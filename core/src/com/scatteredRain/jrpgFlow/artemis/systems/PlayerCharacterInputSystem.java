package com.scatteredRain.jrpgFlow.artemis.systems;

import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCharacterListComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerInteractionComponent;
import com.scatteredRain.jrpgFlow.util.Point;

import static com.scatteredRain.jrpgFlow.Constants.*;

@Wire
public class PlayerCharacterInputSystem extends EntitySystem{

	ComponentMapper<PlayerCharacterComponent> playerComp;
	ComponentMapper<DesiredCharacterMovementComponent> desiredDirComp;
	
	ComponentMapper<CharacterLocationComponent> localComp;
	ComponentMapper<CharacterDirectionComponent> dirComp;
	
	ComponentMapper<MapCharacterListComponent> charListComp;
	
	ComponentMapper<PlayerInteractionComponent> interactComp;
	
	//Indicates Whether The Currently Registered Action Button Press Was Already Used For Triggering Something Else
	private boolean actionUsed;
	
	
	public PlayerCharacterInputSystem() {
		super(Aspect.getAspectForOne(PlayerCharacterComponent.class, MapCharacterListComponent.class));
		this.actionUsed = false;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		MapCharacterListComponent charList = null;
		for(Entity e : entities){
			if(charListComp.has(e)){
				charList = charListComp.get(e);
			}
		}
		for(Entity e : entities){
			if(playerComp.has(e)){
				PlayerCharacterComponent player = playerComp.get(e);
				//Player Movement
				if(player.getInput().hasDirection()){
					desiredDirComp.get(e).setDesiredDirection(player.getInput().getDirection());
				}
				else{
					desiredDirComp.get(e).setStationary();
				}
				//Player Action Button
				if(player.getInput().getAction() && !actionUsed){
					actionUsed = true;
					checkFront(localComp.get(e).getX(), localComp.get(e).getY(), dirComp.get(e).getDirection(), charList);
				}
				else if(!player.getInput().getAction()){
					actionUsed = false;
				}
			}
		}
	}
	
	/** Checks For Character Interaction For The Front Of The Character, Returns Whether It Triggered Or Not */
	private boolean checkFront(int x, int y, int dir, MapCharacterListComponent charList){
		Point target = calcTarget(x, y, dir);
		List<Entity> entities = charList.getEntitiesAt(target.getX(), target.getY());
		for(Entity e : entities){
			if(interactComp.has(e)){
				//TODO: Init Actual Interaction Parts Here
				System.out.println(interactComp.get(e).getAction());
				return true;
			}
		}
		return false;
	}

}
