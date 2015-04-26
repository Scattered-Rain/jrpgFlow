package com.scatteredRain.jrpgFlow.action.coreAction;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterIdComponent;
import com.scatteredRain.jrpgFlow.artemis.systems.map.PassiveCharacterActionSystem;
import com.scatteredRain.jrpgFlow.enums.MapID;

/** Action Designed To Set A Character Variable */
public class ChangeCharacterVariable implements Action{
	
	/** Whether This Action Sets An Integer Or Boolean, true means integer, false means boolean */
	private boolean setInteger;
	/** The Index Of The Variable That Has To Be Set */
	private int index;
	
	/** The Boolean Value That Has To Be Set */
	private boolean booleanValue;
	/** The Integer Value That Has To Be Set */
	private int integerValue;
	
	/** The Character Whose Variable is Supposed To Be Set */
	private Entity character;
	
	
	public ChangeCharacterVariable(Entity character, int integerValue, int index){
		this.character = character;
		this.integerValue = integerValue;
		this.index = index;
		this.setInteger = true;
	}
	
	public ChangeCharacterVariable(Entity character, boolean booleanValue, int index){
		this.character = character;
		this.booleanValue = booleanValue;
		this.index = index;
		this.setInteger = false;
	}
	
	
	@Override
	public void act() {
		CharacterIdComponent id = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getIdComp().get(character);
		MapID map = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getMapId();
		if(setInteger){
			GlobalVariables.globalGameVariables.setCharacterVariable(map, id.getType(), id.getId(), index, integerValue);
		}
		else{
			GlobalVariables.globalGameVariables.setCharacterBoolean(map, id.getType(), id.getId(), index, booleanValue);
		}
	}
	
	
	
	//Static Utility Methods Following
	/** Utility Method To Return Integer Variable At Given Index Of Given Entity */
	public static int getIntegerVariable(Entity character, int index){
		CharacterIdComponent id = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getIdComp().get(character);
		MapID map = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getMapId();
		return GlobalVariables.globalGameVariables.getCharacterVariable(map, id.getType(), id.getId(), index);
	}
	
	/** Utility Method To Return Boolean Variable At Given Index Of Given Entity */
	public static boolean getBooleanVariable(Entity character, int index){
		CharacterIdComponent id = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getIdComp().get(character);
		MapID map = character.getWorld().getSystem(PassiveCharacterActionSystem.class).getMapId();
		return GlobalVariables.globalGameVariables.getCharacterBoolean(map, id.getType(), id.getId(), index);
	}
	
}
