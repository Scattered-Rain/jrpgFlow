package com.scatteredRain.jrpgFlow.artemis.components.maps.map;

import java.util.ArrayList;
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
		
		//TODO: Fix Whatever Causes The Issue Here
		
		//Properly Set Up Waypoint List
		int maxIndex = -1;
		for(Waypoint w : setupWaypointList){
			if(w.getId()>maxIndex){
				maxIndex = w.getId();
			}
		}
		for(int c=0; c<=maxIndex; c++){
			this.waypointList.add(new ArrayList<Waypoint>());
			int subCounter = 0;
			for(int c2=0; c2<setupWaypointList.size(); c2++){
				Waypoint w = setupWaypointList.get(c2);
				if(w.getId()==c){
					if(w.getSubId()==subCounter){
						subCounter++;
						this.waypointList.get(c).add(w);
						setupWaypointList.remove(c2);
						c2=0;
					}
				}
			}
		}
		if(setupWaypointList.size()>0){
			System.out.println("Waypoints In This Map Not Properly Set Up!");
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
			int val = id-other.getId();
			if(id==other.getId()){
				val = subId-other.getSubId();
			}
			return val;
		}
	}
	
}
