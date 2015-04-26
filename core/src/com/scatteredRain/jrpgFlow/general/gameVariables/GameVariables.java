package com.scatteredRain.jrpgFlow.general.gameVariables;

import java.util.ArrayList;
import java.util.List;

import com.scatteredRain.jrpgFlow.enums.MapID;

/** This Class Stores All Game Variables (Variables Used By All Things Map Characters) */
public class GameVariables {
	
	/** List That Contains The Variables Of All Characters, First Layer Map Id, Ordered; Second Layer Charcters, Unordered */
	private List<List<CharacterVariableSet>> characterVariables;
	
	/** Constructs New */
	public GameVariables(){
		this.characterVariables = new ArrayList<List<CharacterVariableSet>>();
	}
	
	
	/** Set Given Character's Integer Variable At Given Index */
	public void setCharacterVariable(MapID map, String type, int id, int variableIndex, int variableValue){
		CharacterVariableSet set = requestCharacterVariableSet(map, type, id);
		set.setVar(variableIndex, variableValue);
	}
	
	/** Set Given Character's Boolean Variable At Given Index */
	public void setCharacterBoolean(MapID map, String type, int id, int variableIndex, boolean variableValue){
		CharacterVariableSet set = requestCharacterVariableSet(map, type, id);
		set.setSwitch(variableIndex, variableValue);
	}
	
	/** Returns Given Character's Integer Variable At Given Index */
	public int getCharacterVariable(MapID map, String type, int id, int variableIndex){
		CharacterVariableSet set = requestCharacterVariableSet(map, type, id);
		return set.getVar(variableIndex);
	}
	
	/** Returns Given Character's Boolean Variable At Given Index */
	public boolean getCharacterBoolean(MapID map, String type, int id, int variableIndex){
		CharacterVariableSet set = requestCharacterVariableSet(map, type, id);
		return set.getSwitch(variableIndex);
	}
	
	
	
	/** Internally Retrieve, And If Not Existent Yet, Create CharacterVariableSet Of Given Identification */
	private CharacterVariableSet requestCharacterVariableSet(MapID map, String type, int id){
		//Fill The List Up To Contain The Given Index If Not Already Contained
		while(characterVariables.size()<=map.getId()){
			characterVariables.add(new ArrayList<CharacterVariableSet>());
		}
		List<CharacterVariableSet> mapList = characterVariables.get(map.getId());
		//Find Set
		CharacterVariableSet requestedSet = null;
		for(CharacterVariableSet set : mapList){
			if(set.matchesId(id, type, map)){
				requestedSet = set;
			}
		}
		//Create And Add Set If It Couldn't Be Found
		if(requestedSet==null){
			requestedSet = new CharacterVariableSet(id, type, map);
			mapList.add(requestedSet);
		}
		//Return Set
		return requestedSet;
	}
	
}
