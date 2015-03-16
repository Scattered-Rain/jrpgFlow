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
	
	public FollowWaypointsMovement(Entity owner, int waypoints){
		this.owner = owner;
		this.waypoints = waypoints;
		this.progression = -1;
	}

	@Override
	public int desiredDirection() {
		List<Waypoint> wPoints = super.getWaypoint(waypoints);
		int dir = STATIONARY;
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
					dir = UP;
				}
				else{
					dir = DOWN;
				}
			}
		}
		return dir;
	}

	@Override
	public void moveCompletionUpdate(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
