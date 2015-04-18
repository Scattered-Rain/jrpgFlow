package com.scatteredRain.jrpgFlow.general.aiMovement;

import java.util.List;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent.Waypoint;
import com.scatteredRain.jrpgFlow.util.Point;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class FollowWaypointsMovement extends AIMovement{
	
	private Entity owner;
	private int waypoints;
	//The Waypoints that have already been reached
	private int progression;
	//Looping
	private boolean looping = false;
	
	public FollowWaypointsMovement(Entity owner, int waypoints){
		this.owner = owner;
		this.waypoints = waypoints;
		this.progression = 0;
	}
	
	public FollowWaypointsMovement(Entity owner, int waypoints, boolean looping){
		this(owner, waypoints);
		this.looping = looping;
	}

	@Override
	public int desiredDirection() {
		List<Waypoint> wPoints = super.getWaypoint(waypoints);
		int dir = STATIONARY;
		if(looping){
			if(!(wPoints.size()>progression)){
				this.progression = 0;
			}
		}
		if(wPoints.size()>progression){
			Waypoint w = wPoints.get(progression);
			Point l = super.getLocation(owner);
			int wx = w.getX();
			int wy = w.getY();
			int lx = l.getX();
			int ly = l.getY();
			if(wx==lx && wy==ly){
				progression++;
			}
			else if(wx!=lx){
				if(wx<lx){
					dir = LEFT;
				}
				else{
					dir = RIGHT;
				}
			}
			else if(wy!=ly){
				if(wy<ly){
					dir = DOWN;
				}
				else{
					dir = UP;
				}
			}
		}
		else{
			if(completionListener!=null){
				//Call Completion Listener As Action Is Now Completed
				completionListener.completed();
				//Set Completion Listener To null Once Its Been Called
				completionListener = null;
			}
		}
		return dir;
	}
	
	public boolean moveComplete(){
		List<Waypoint> wPoints = super.getWaypoint(waypoints);
		//Note: This Will Never Return True If Looping Is Turned On
		return !(wPoints.size()>progression);
	}

	@Override
	public void moveCompletionUpdate(int x, int y) {}

}
