package com.scatteredRain.jrpgFlow.general.gameVariables;

import com.scatteredRain.jrpgFlow.enums.MapID;

public class CharacterVariableSet extends VariableSet{
	
	private int id;
	private String type;
	private MapID map;
	
	public CharacterVariableSet(int id, String type, MapID map){
		super();
		this.id = id;
		this.type = type;
		this.map = map;
	}
	
}
