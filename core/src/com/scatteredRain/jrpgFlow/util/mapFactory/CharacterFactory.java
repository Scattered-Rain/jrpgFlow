package com.scatteredRain.jrpgFlow.util.mapFactory;

import static com.scatteredRain.jrpgFlow.Constants.SPRITE_ATLAS;
import static com.scatteredRain.jrpgFlow.Constants.TILE_SIZE;
import static com.scatteredRain.jrpgFlow.util.mapFactory.CharacterFactory.DIRECTION;

import com.artemis.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
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

public class CharacterFactory {
	
	//General Terms----------------------------------
	//Type Of The Character
	public static final String TYPE = "type";
	
	//Types-------------------------------------------
	//Entrance For Player
	public static final String ENTER = "enter";//This Will Is Used In MapFactory And Will Not Be Translated Into A Character
	//Sign
	public static final String SIGN = "sign";
	
	//Keys-------------------------------------------
	//ID, If Needed (ID+Type Should Equal Unique Identification Of Character
	public static final String ID = "id";
	//Direction The Character Is (Initially) Facing
	public static final String DIRECTION = "dir";
	//Text A Character Will Say
	public static final String TEXT = "text";
	
	
	
	//Generic Access Points------------------------------
	/**Generic Factory Class Accessed By Map Factory */
	public static Entity readObject(Entity e, String type, int x, int y, RectangleMapObject object, MapProperties properties){
		if(type.equals(SIGN)){
			e = createSign(e, x, y, properties.get(TEXT, String.class));
		}
		return e;
	}
	
	
	
	//Specific Types-----------------
	/** Adds A Sign */
	private static Entity createSign(Entity e, int x, int y, String text){
		addExistence(e, x, y, 2, true);
		e.addComponent(new PlayerInteractionComponent(text));
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
		MapCharacterAnimationSetComponent mapCharAniComp = new MapCharacterAnimationSetComponent(SPRITE_ATLAS, spriteName);
		e.addComponent(mapCharAniComp);
		e.addComponent(new ActiveCharacterSpriteAnimationComponent(mapCharAniComp.getActiveWalking(dir)));
		e.addComponent(new ActiveCharacterSpriteComponent(mapCharAniComp.getActiveWalking(dir).currentFrame()));
		e.addComponent(new CharacterSpriteLocationComponent(TILE_SIZE*x+TILE_SIZE*0.5f, TILE_SIZE*y));
		return e;
	}
	
}
