package com.scatteredRain.jrpgFlow.util.mapFactory;

import static com.scatteredRain.jrpgFlow.GlobalVariables.*;
import static com.scatteredRain.jrpgFlow.Constants.*;

import com.artemis.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.scatteredRain.jrpgFlow.Constants;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.characterAction.Smalltalk;
import com.scatteredRain.jrpgFlow.action.coreAction.Teleport;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterIdComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerInteractionComponent;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory;
import com.scatteredRain.jrpgFlow.enums.MapID;
import com.scatteredRain.jrpgFlow.enums.SpriteID;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory.AttKey;

public class GenericCharacterFactory {
	
	
	/**Generic Factory Class Accessed By Map Factory */
	public static Entity readObject(Entity e, String type, int x, int y, RectangleMapObject object, MapProperties properties){
		e = CharacterFactory.buildCharacter(e, type, x, y, object, properties);
		e = addID(e, type, properties);
		return e;
	}
	
	
	//Utility Methods For Character Creation
	/** Adds Base Of EVERY Character, Includes Location, Direction And Collision */
	public static Entity addExistence(Entity e, int x, int y, int dir, boolean coll){
		e.addComponent(new CharacterLocationComponent(x, y));
		e.addComponent(new CharacterDirectionComponent(dir));
		e.addComponent(new CharacterCollisionComponent(coll));
		return e;
	}
	
	/** Adds The Capability Of Moving To The Character */
	public static Entity addMovabililty(Entity e){
		e.addComponent(new DesiredCharacterMovementComponent());
		e.addComponent(new CharacterMoveProgressionComponent());
		return e;
	}
	
	/** Adds Sprite Components To The Entity, Using Texture Of Main Atlas */
	public static Entity addSprite(Entity e, int x, int y, String spriteName, int dir){
		MapCharacterAnimationSetComponent mapCharAniComp = new MapCharacterAnimationSetComponent(globalSpriteAtlas, spriteName);
		e.addComponent(mapCharAniComp);
		e.addComponent(new ActiveCharacterSpriteAnimationComponent(mapCharAniComp.getActiveWalking(dir)));
		e.addComponent(new ActiveCharacterSpriteComponent(mapCharAniComp.getActiveWalking(dir).currentFrame()));
		e.addComponent(new CharacterSpriteLocationComponent(TILE_SIZE*x+TILE_SIZE*0.5f, TILE_SIZE*y));
		return e;
	}
	
	/** Adds CharacterID Component To The Entity, So it Is Uniquely Identifiable On A Map */
	public static Entity addID(Entity e, String type, MapProperties properties){
		int id = -1;
		if(properties.containsKey(AttKey.ID.key())){
			id = Integer.parseInt(properties.get(AttKey.ID.key(), String.class));
		}
		e.addComponent(new CharacterIdComponent(type, id));
		return e;
	}
	
}
