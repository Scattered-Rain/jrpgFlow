package com.scatteredRain.jrpgFlow.util.mapFactory;

import static com.scatteredRain.jrpgFlow.GlobalVariables.*;
import static com.scatteredRain.jrpgFlow.Constants.*;
import static com.scatteredRain.jrpgFlow.util.mapFactory.CharacterFactory.DIRECTION;

import com.artemis.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.scatteredRain.jrpgFlow.Constants;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.Teleport;
import com.scatteredRain.jrpgFlow.action.Textboxing;
import com.scatteredRain.jrpgFlow.action.characterAction.Smalltalk;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterDirectionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.MapCharacterAnimationSetComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerInteractionComponent;
import com.scatteredRain.jrpgFlow.enums.MapID;
import com.scatteredRain.jrpgFlow.enums.SpriteID;

public class CharacterFactory {
	
	//General Terms----------------------------------
	//Type Of The Character
	public static final String TYPE = "type";
	
	//Types-------------------------------------------
	//Entrance For Player
	public static final String ENTER = "enter";//This Will Is Used In MapFactory And Will Not Be Translated Into A Character
	//Sign
	public static final String SIGN = "sign";
	//Teleporter
	public static final String TELEPORT = "warp";
	//Smalltalker
	public static final String SMALLTALK = "smalltalk";
	
	//Keys-------------------------------------------
	//ID, If Needed (ID+Type Should Equal Unique Identification Of Character)
	public static final String ID = "id";
	//Direction The Character Is (Initially) Facing
	public static final String DIRECTION = "dir";
	//Text A Character Will Say
	public static final String TEXT = "text";
	//Map
	public static final String MAP = "map";
	//Generic ID
	public static final String GENERIC_ID = "genid";
	//Sprite
	public static final String SPRITE = "sprite";
	
	//Specified Key For Showing Player Interaction Type
	public static final String TRIGGER = "trigger";
	//Interaction Type: Player has 'talks' to character
	public static final String TRIGGER_TALK = "talk";
	//Interaction Type: Player has 'looks' to character
	public static final String TRIGGER_LOOK = "look";
	//Interaction Type: Player has 'touches' to character
	public static final String TRIGGER_TOUCH = "touch";
	//Interaction Type: Player has 'pushes' to character
	public static final String TRIGGER_PUSH = "push";
	
	
	
	
	
	
	
	
	
	
	//Generic Access Points------------------------------
	/**Generic Factory Class Accessed By Map Factory */
	public static Entity readObject(Entity e, String type, int x, int y, RectangleMapObject object, MapProperties properties){
		if(type.equals(SIGN)){
			e = createSign(e, x, y, properties.get(TEXT, String.class));
		}
		else if(type.equals(TELEPORT)){
			e = createTeleport(e, x, y, properties.get(MAP, String.class), Integer.parseInt(properties.get(GENERIC_ID, String.class)), properties.get(TRIGGER, String.class));
		}
		else if(type.equals(SMALLTALK)){
			e = createSmalltalk(e, x, y, Integer.parseInt(properties.get(DIRECTION, String.class)), properties.get(TEXT, String.class), properties.get(SPRITE, String.class));
		}
		return e;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//Specific Types-----------------
	/** Adds A Sign */
	private static Entity createSign(Entity e, int x, int y, String pTEXT){
		addExistence(e, x, y, 2, true);
		Action talking = new Textboxing(pTEXT);
		e.addComponent(new PlayerInteractionComponent(talking, null, null, null));
		return e;
	}
	
	/** Adds A Teleport */
	private static Entity createTeleport(Entity e, int x, int y, String map, int enter, String trigger){
		Action warping = new Teleport(MapID.valueOf(map), enter);
		if(trigger==null || trigger.equals(TRIGGER_TALK)){
			addExistence(e, x, y, 2, true);
			e.addComponent(new PlayerInteractionComponent(warping, null, null, null));
		}
		else if(trigger.equals(TRIGGER_LOOK)){
			addExistence(e, x, y, 2, true);
			e.addComponent(new PlayerInteractionComponent(null, warping, null, null));
		}
		else if(trigger.equals(TRIGGER_TOUCH)){
			addExistence(e, x, y, 2, false);
			e.addComponent(new PlayerInteractionComponent(null, null, warping, null));
		}
		else if(trigger.equals(TRIGGER_PUSH)){
			addExistence(e, x, y, 2, true);
			e.addComponent(new PlayerInteractionComponent(null, null, null, warping));
		}
		return e;
	}
	
	/** Adds A Smalltalker */
	private static Entity createSmalltalk(Entity e, int x, int y, int dir, String text, String spriteName){
		addExistence(e, x, y, dir, true);
		addSprite(e, x, y, SpriteID.valueOf(spriteName).getPath(), dir);
		Action smalltalk = new Smalltalk(e, text);
		e.addComponent(new PlayerInteractionComponent(smalltalk, null, null, null));
		return e;
	}
	
	
	
	
	
	
	
	
	
	
	
	//Helper Methods, Add Common Stuff Here!----------------
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
	
}
