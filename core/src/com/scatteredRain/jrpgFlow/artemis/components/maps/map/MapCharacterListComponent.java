package com.scatteredRain.jrpgFlow.artemis.components.maps.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import com.artemis.Component;
import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.util.mapFactory.MapFactory;

/** Component Contains References To All Characters On The Map, As Well As Their Location */
public class MapCharacterListComponent extends Component{
	
	@Getter
	private int width;
	@Getter
	private int height;
	
	/** List Of All Entrances On This Map */
	@Getter private List<Entrance> entranceList;
	
	/** List Of All Entrances On This Map */
	@Getter private List<List<Waypoint>> waypointList;
	//Setup List For waypointList
	private List<Waypoint> setupWaypointList;
	
	/** 2D Array Of Array Lists Storing Each Character's Entity */
	private List<Entity>[][] characterList;
	
	/** Creates Character List For Map, Empty */
	public MapCharacterListComponent(int width, int height){
		this.width = width;
		this.height = height;
		this.waypointList = new ArrayList<List<Waypoint>>();
		this.setupWaypointList = new ArrayList<Waypoint>();
		this.entranceList = new ArrayList<Entrance>();
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
	
	
	/** Adds New Entrance */
	public void addEntrance(Entrance entrance){
		this.entranceList.add(entrance);
	}
	
	/** Adds New Waypoint (Note: This Will Lead To Errors When Done After completeInitialSetup() was called) */
	public void addWaypoint(Waypoint waypoint){
		this.setupWaypointList.add(waypoint);
	}
	
	/** Called When The Initial Setup Of (As Done By the MapFactory) Is Finished Up */
	public void completeInitialSetup(){
		Collections.sort(setupWaypointList);
		int maxId = -1;
		for(int c=0; c<setupWaypointList.size(); c++){
			Waypoint w = setupWaypointList.get(c);
			if(w.getId()>maxId){
				maxId = w.getId();
			}
		}
		for(int c=0; c<=maxId; c++){
			ArrayList<Waypoint> newList = new ArrayList<Waypoint>();
			int nextSubId = 0;
			for(int d=0; d<setupWaypointList.size(); d++){
				Waypoint w = setupWaypointList.get(d);
				if(w.getId()==d && w.getSubId()==nextSubId){
					nextSubId++;
					newList.add(w);
					setupWaypointList.remove(c);
					d--;
				}
			}
			waypointList.add(newList);
		}
		
		if(setupWaypointList.size()>0){
			System.out.println("Waypoints Not Correctly Processed");
		}
		
	}
	
	
	@Data
	@AllArgsConstructor
	public static class Entrance implements Comparable<Entrance>{
		private int x;
		private int y;
		private int dir;
		private int id;
		
		@Override public int compareTo(Entrance other) {
			return id-other.getId();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Waypoint implements Comparable<Waypoint>{
		private int x;
		private int y;
		private int id;
		private int subId;
		
		@Override public int compareTo(Waypoint other) {
			int val = (id-other.getId())*1024 + (subId-other.getSubId());
			return val;
		}
	}
	
}
