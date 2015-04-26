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
	
	/** Returns Whether This Set Is The Requested Set */
	public boolean matchesId(int id, String type, MapID map){
		return (this.id==id && this.type==type && this.map.getId()== map.getId());
	}
	
}
