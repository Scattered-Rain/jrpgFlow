package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.scatteredRain.jrpgFlow.util.mapFactory.GenericCharacterFactory.*;

import com.artemis.Entity;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.characterAction.Smalltalk;
import com.scatteredRain.jrpgFlow.action.coreAction.Teleport;
import com.scatteredRain.jrpgFlow.action.coreAction.Textboxing;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.PlayerInteractionComponent;

public class CharacterFactory {
	
	/** Enum Of All Character Factories */
	private static enum CharFactory{
		
		SIGN(new CharConstruct(){
			public Entity build(Entity e, int x, int y, RectangleMapObject object, MapProperties properties){
				String text = properties.get(AttKey.TEXT.key(), String.class);
				//Process---
				addExistence(e, x, y, 2, true);
				Action talking = new Textboxing(text);
				e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.TALKING, talking));
				return e;
			}
		}),
		
		WARP(new CharConstruct(){
			public Entity build(Entity e, int x, int y, RectangleMapObject object, MapProperties properties){
				String map = properties.get(AttKey.MAP.key(), String.class);
				String trigger = properties.get(AttKey.TRIGGER.key(), String.class);
				int enter = Integer.parseInt(properties.get(AttKey.TARGET.key(), String.class));
				//Process---
				Action warping = new Teleport(MapID.valueOf(map), enter);
				if(trigger==null || trigger.equals(AttVal.TRIGGER_TALK.val())){
					addExistence(e, x, y, 2, true);
					e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.TALKING, warping));
				}
				else if(trigger.equals(AttVal.TRIGGER_LOOK.val())){
					addExistence(e, x, y, 2, true);
					e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.LOOKING, warping));
				}
				else if(trigger.equals(AttVal.TRIGGER_TOUCH.val())){
					addExistence(e, x, y, 2, false);
					e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.TOUCHING, warping));
				}
				else if(trigger.equals(AttVal.TRIGGER_PUSH.val())){
					addExistence(e, x, y, 2, true);
					e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.PUSHING, warping));
				}
				return e;
			}
		}),
		
		SMALLTALK(new CharConstruct(){
			public Entity build(Entity e, int x, int y, RectangleMapObject object, MapProperties properties){
				int dir = Integer.parseInt(properties.get(AttKey.DIRECTION.key(), String.class));
				String text = properties.get(AttKey.TEXT.key(), String.class);
				String spriteName = properties.get(AttKey.SPRITE.key(), String.class);
				//Process---
				addExistence(e, x, y, dir, true);
				addSprite(e, x, y, SpriteID.valueOf(spriteName), dir);
				addMovabililty(e);
				Action smalltalk = new Smalltalk(e, text);
				e.addComponent(new PlayerInteractionComponent(PlayerInteractionComponent.TALKING, smalltalk));
				return e;
			}
		});
		
		
		
		/** The Character Constructor Method Of The Character Factory In Question */
		private CharConstruct charConstructor;
		/** Constructor */
		private CharFactory(CharConstruct charConstructor){
			this.charConstructor = charConstructor;
		}
		/** Construct New Character */
		public Entity build(Entity e, int x, int y, RectangleMapObject object, MapProperties properties){
			return charConstructor.build(e, x, y, object, properties);
		}
	}
	
	
	@AllArgsConstructor
	/** Enum Of All Keys Of The Attributes Used By CharFactory */
	public static enum AttKey{
		
		ID("ID"),
		SUB_ID("SUB_ID"),
		DIRECTION("DIR"),
		TEXT("TEXT"),
		MAP("MAP"),
		TARGET("TARGET"),
		SPRITE("SPRITE"),
		TRIGGER("TRIGGER");
		
		/** The String Representing The Attribute In Question */
		private String key;
		/** Retruns Key String */
		public String key(){
			return key;
		}
	}
	
	
	@AllArgsConstructor
	/** Enum Of All The SPECIFIC Values That Are Used For Certain Processes By The CharFactory */
	public static enum AttVal{
		
		TRIGGER_TALK("TALK"),
		TRIGGER_LOOK("LOOK"),
		TRIGGER_TOUCH("TOUCH"),
		TRIGGER_PUSH("PUSH");
		
		/** The String Representing The Attribute In Question */
		private String val;
		/** Retruns Key String */
		public String val(){
			return val;
		}
	}
	
	
	
	/** Returns Entity Of Character Of Given Tiled Object */
	public static Entity buildCharacter(Entity e, String type, int x, int y, RectangleMapObject object, MapProperties properties){
		e = CharFactory.valueOf(type).build(e, x, y, object, properties);
		return e;
	}
	
	/** Container Class For Actual Entity Constructor Method */
	private static abstract class CharConstruct{
		public abstract Entity build(Entity e, int x, int y, RectangleMapObject object, MapProperties properties);
	}
	
}
