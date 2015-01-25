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
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerCharacterComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerInteractionComponent;
import com.scatteredRain.jrpgFlow.util.Point;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

import static com.scatteredRain.jrpgFlow.Constants.*;

@Wire
public class PlayerCharacterInputSystem extends EntitySystem{

	ComponentMapper<PlayerCharacterComponent> playerComp;
	ComponentMapper<DesiredCharacterMovementComponent> desiredDirComp;
	
	ComponentMapper<CharacterLocationComponent> localComp;
	ComponentMapper<CharacterDirectionComponent> dirComp;
	ComponentMapper<CharacterMoveProgressionComponent> moveProgComp;
	
	ComponentMapper<MapCharacterListComponent> charListComp;
	
	ComponentMapper<PlayerInteractionComponent> interactComp;
	
	//Indicates Whether The Currently Registered Action Button Press Was Already Used For Triggering Something Else
	private boolean actionUsed;
	private Point lastPoint;
	
	
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
					if(!moveProgComp.get(e).isMoving()){
						if(desiredDirComp.get(e).getDesiredDirection()==dirComp.get(e).getDirection()){
							//Push
							checkFrontPush(localComp.get(e).getX(), localComp.get(e).getY(), dirComp.get(e).getDirection(), charList);
						}
					}
				}
				else{
					desiredDirComp.get(e).setStationary();
					if(!moveProgComp.get(e).isMoving()){
						//Look
						checkFrontLook(localComp.get(e).getX(), localComp.get(e).getY(), dirComp.get(e).getDirection(), charList);
					}
				}
				//Player Action Button
				if(player.getInput().getAction() && !actionUsed){
					actionUsed = true;
					//Talk
					checkFrontTalk(localComp.get(e).getX(), localComp.get(e).getY(), dirComp.get(e).getDirection(), charList);
				}
				else if(!player.getInput().getAction()){
					actionUsed = false;
				}
				//Player Standing
				if(!moveProgComp.get(e).isMoving()){
					if(lastPoint!=null && 1==Math.abs(lastPoint.getX()-localComp.get(e).getX()+lastPoint.getY()-localComp.get(e).getY())){
						//Standing
						checkStanding(localComp.get(e).getX(), localComp.get(e).getY(), charList);
					}
					lastPoint = new Point(localComp.get(e).getX(), localComp.get(e).getY());
				}
			}
		}
	}
	
	
	
	
	
	
	/** Character Interaction For The Front (Talking) Of The Character, Returns Whether It Triggered Or Not */
	private boolean checkFrontTalk(int x, int y, int dir, MapCharacterListComponent charList){
		Point target = calcTarget(x, y, dir);
		try{
			List<Entity> entities = charList.getEntitiesAt(target.getX(), target.getY());
			for(Entity e : entities){
				if(interactComp.has(e) && interactComp.get(e).hasTalking()){
					interactComp.get(e).getTalking().act();
					return true;
				}
			}
		}catch(Exception ex){}
		return false;
	}
	
	/** Character Interaction For The Front (Pushing) Of The Character, Returns Whether It Triggered Or Not */
	private boolean checkFrontPush(int x, int y, int dir, MapCharacterListComponent charList){
		Point target = calcTarget(x, y, dir);
		try{
			List<Entity> entities = charList.getEntitiesAt(target.getX(), target.getY());
			for(Entity e : entities){
				if(interactComp.has(e) && interactComp.get(e).hasPushing()){
					interactComp.get(e).getPushing().act();
					return true;
				}
			}
		}catch(Exception ex){}
		return false;
	}
	
	/** Character Interaction For The Front (Pushing) Of The Character, Returns Whether It Triggered Or Not */
	private boolean checkFrontLook(int x, int y, int dir, MapCharacterListComponent charList){
		Point target = calcTarget(x, y, dir);
		try{
			List<Entity> entities = charList.getEntitiesAt(target.getX(), target.getY());
			for(Entity e : entities){
				if(interactComp.has(e) && interactComp.get(e).hasLooking()){
					interactComp.get(e).getLooking().act();
					return true;
				}
			}
		}catch(Exception ex){}
		return false;
	}
	
	/** Character Interaction For Touching Of The Character, Returns Whether It Triggered Or Not */
	private boolean checkStanding(int x, int y, MapCharacterListComponent charList){
		Point target = new Point(x, y);
		try{
			List<Entity> entities = charList.getEntitiesAt(target.getX(), target.getY());
			for(Entity e : entities){
				if(interactComp.has(e) && interactComp.get(e).hasTouching()){
					interactComp.get(e).getTouching().act();
					return true;
				}
			}
		}catch(Exception ex){}
		return false;
	}

}
