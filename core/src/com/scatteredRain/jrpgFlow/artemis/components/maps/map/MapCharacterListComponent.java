package com.scatteredRain.jrpgFlow.artemis.components.maps.map;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.artemis.Component;
import com.artemis.Entity;

/** Component Contains References To All Characters On The Map, As Well As Their Location */
public class MapCharacterListComponent extends Component{
	
	@Getter
	private int width;
	@Getter
	private int height;
	
	/** 2D Array Of Array Lists Storing Each Character's Entity */
	private List<Entity>[][] characterList;
	
	/** Creates Character List For Map, Empty */
	public MapCharacterListComponent(int width, int height){
		this.width = width;
		this.height = height;
		this.characterList = new ArrayList[height][width];
		for(int cy=0; cy<characterList.length; cy++){
			for(int cx=0; cx<characterList[cy].length; cx++){
				characterList[cy][cx] = new ArrayList<Entity>();
			}
		}
	}
	
	/** Returns Whether Entity Has Been Removed, Assumes That Only One Identical Entity Is Stored */
	public boolean removeEntityAt(int x, int y, Entity e){
		List<Entity> list = characterList[y][x];
		for(int c=0; c<list.size(); c++){
			if(list.get(c).equals(e)){
				list.remove(c);
				return true;
			}
		}
		return false;
	}
	
	public void addEntity(int x, int y, Entity e){
		List<Entity> list = characterList[y][x];
		list.add(e);
	}
	
	public List<Entity> getEntitiesAt(int x, int y){
		return characterList[y][x];
	}
	
	public void relocateEntity(int fromX, int fromY, Entity e, int toX, int toY){
		if(removeEntityAt(fromX, fromY, e)){
			addEntity(toX, toY, e);
		}
	}
	
}
