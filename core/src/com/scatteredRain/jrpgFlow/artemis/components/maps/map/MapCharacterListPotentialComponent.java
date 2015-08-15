package com.scatteredRain.jrpgFlow.artemis.components.maps.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory;
import com.scatteredRain.jrpgFlow.enums.CharacterFactory.AttKey;
import com.scatteredRain.jrpgFlow.enums.MapID;

/** Holds The Basic Information Of All Characters on the map, used for updating & reseting Characters; Especially for Character version control */
public class MapCharacterListPotentialComponent{
	
	/** List of Collections of Potential Character Data */
	private List<PotentialCharacterCollection> potCharCollections;
	
	
	/** Constructor */
	public MapCharacterListPotentialComponent(){
		this.potCharCollections = new ArrayList<PotentialCharacterCollection>();
	}
	
	/** Returns a list of Potential Character Data of all potential Characters that are legal with the highest possible SubId */
	public List<PotentialCharacter> getHighestLegalForAll(){
		List<PotentialCharacter> chars = new ArrayList<PotentialCharacter>();
		for(PotentialCharacterCollection coll : potCharCollections){
			PotentialCharacter current = getHighestLegalPotChar(coll.getId(), coll.getType());
			if(current!=null){
				chars.add(current);
			}
		}
		return chars;
	}
	
	
	/** Add new potential character to the map, if character spawn is valid it will then be added to the map later on */
	public void setPotentialCharacter(String type, int x, int y, RectangleMapObject object, MapProperties properties){
		//Retrieve Id and SubId
		int id = -1;
		int subId = -1;
		if(properties.containsKey(AttKey.ID.key())){
			id = Integer.parseInt(properties.get(AttKey.ID.key(), String.class));
		}
		if(properties.containsKey(AttKey.SUB_ID.key())){
			subId = Integer.parseInt(properties.get(AttKey.SUB_ID.key(), String.class));
		}
		//Set Potential Character
		PotentialCharacter potChar = new PotentialCharacter(subId, id, type, x, y, object, properties);
		addPotChar(potChar, id, type);
	}
	
	/** Adds PotChar To list, adds collection if not already in list */
	private void addPotChar(PotentialCharacter potChar, int id, String type){
		boolean wasInList = false;
		for(PotentialCharacterCollection coll : potCharCollections){
			if(coll.isChar(id, type)){
				wasInList = true;
				coll.addPotChar(potChar);
			}
		}
		if(!wasInList){
			PotentialCharacterCollection coll = new PotentialCharacterCollection(id, type);
			coll.addPotChar(potChar);
			this.potCharCollections.add(coll);
		}
	}
	
	/** Returns The Data of all the Potential Characters of the given id and type (Returns an Empty list if it couldn't find it) */
	public List<PotentialCharacter> getPotentialCharacterData(int id, String type){
		for(PotentialCharacterCollection coll : potCharCollections){
			if(coll.isChar(id, type)){
				return coll.getCharacters();
			}
		}
		return new ArrayList<PotentialCharacter>();
	}
	
	/** Returns Character of given id and type with the highest subId which fullfills all given conditions (null if there is no such character) */
	public PotentialCharacter getHighestLegalPotChar(int id, String type){
		List<PotentialCharacter> coll = getPotentialCharacterData(id, type);
		//Note: We traverse the list from the back, as highest subId has the highest priority
		for(int c=0; c<coll.size(); c++){
			int index = coll.size()-1 - c;
			if(coll.get(index).isLegal()){
				return coll.get(index);
			}
		}
		return null;
	}
	
	/** Completes the initial setup for this component, orders all potential character collection's Potential Character Lists */
	public void completeInitialSetup(){
		for(PotentialCharacterCollection coll : potCharCollections){
			coll.sort();
		}
	}
	
	
	@Data
	/** Stores All Potential Characters With identical type as well as id */
	private class PotentialCharacterCollection{
		
		/** Id */
		private int id;
		private String type;
		
		/** List Of Potential Characters As defined by their subIds */
		private List<PotentialCharacter> characters;
		
		/** Construct New */
		public PotentialCharacterCollection(int id, String type){
			this.id = id;
			this.type = type;
			this.characters = new ArrayList<PotentialCharacter>();
		}
		
		/** Add Potential Character to list */
		public void addPotChar(PotentialCharacter potChar){
			this.characters.add(potChar);
		}
		
		/** Returns whether this collection is the requested collection */
		public boolean isChar(int id, String type){
			return (this.id==id && this.type.equals(type));
		}
		
		/** Sorts The Character List According to the subId */
		public void sort(){
			Collections.sort(characters);
		}
		
	}
	
	@Data
	@AllArgsConstructor
	/** Keeps track of the potential character's data */
	public class PotentialCharacter implements Comparable<PotentialCharacter>{
		
		/** Sub Id */
		private int subId;
		private int id;
		private String type;
		
		/** The Coordinates */
		private int x;
		private int y;
		
		/** Conditions */
		//TODO: Add Conditions!
		
		/** Values */
		private RectangleMapObject object;
		private MapProperties properties;
		
		@Override
		public int compareTo(PotentialCharacter potChar) {
			return subId - potChar.getSubId();
		}
		
		//TODO: Add Boolean Checking for conditions
		public boolean isLegal(){
			boolean allow = true;
			boolean forbid = false;
			if(properties.containsKey(AttKey.SPAWN_COND_ALLOW.key())){
				String condAllow = properties.get(AttKey.SPAWN_COND_ALLOW.key(), String.class);
				allow = testVar(condAllow);
			}
			if(properties.containsKey(AttKey.SPAWN_COND_FORBID.key())){
				String condForbid = properties.get(AttKey.SPAWN_COND_FORBID.key(), String.class);
				forbid = testVar(condForbid);
			}
			return (allow & !forbid);
		}
		
		//tests conditions
		private boolean testVar(String condition){
			int varValue = 0;
			String comparator = "";
			int compareValue = 0;
			Scanner scan = new Scanner(condition);
			String cod = scan.next();
			scan.close();
			if(cod.equals("GAME_VAR")){//GAME_VAR 13 = 4
				Scanner s = new Scanner(condition);
				s.next();
				int index = s.nextInt();
				varValue = GlobalVariables.globalGameVariables.getGameVariable(index);
				comparator = s.next();
				compareValue = s.nextInt();
				s.close();
			}
			else if(cod.equals("SELF_VAR")){//SELF_VAR 13 = 4
				Scanner s = new Scanner(condition);
				s.next();
				int index = s.nextInt();
				varValue = GlobalVariables.globalGameVariables.getCharacterVariable(GlobalVariables.currentMapID, type, id, index);
				comparator = s.next();
				compareValue = s.nextInt();
				s.close();
			}
			if(comparator.equals("==") && varValue==compareValue){
				return true;
			}
			else if(comparator.equals(">") && varValue>compareValue){
				return true;
			}
			else if(comparator.equals("<") && varValue<compareValue){
				return true;
			}
			else if(comparator.equals(">=") && varValue>=compareValue){
				return true;
			}
			else if(comparator.equals("<") && varValue<=compareValue){
				return true;
			}
			else if(comparator.equals("!=") && varValue!=compareValue){
				return true;
			}
			else{
				return false;
			}
		}
		
	}
	
}
