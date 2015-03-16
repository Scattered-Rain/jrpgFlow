package com.scatteredRain.jrpgFlow.general.aiMovement;

import static com.scatteredRain.jrpgFlow.Constants.*;

import java.util.List;

import com.artemis.Entity;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent.Waypoint;
import com.scatteredRain.jrpgFlow.artemis.systems.map.PassiveCharacterActionSystem;
import com.scatteredRain.jrpgFlow.general.ActiveWorldList;
import com.scatteredRain.jrpgFlow.util.Point;

public abstract class AIMovement {
	
	/** Returns The Desired Direction */
	public abstract int desiredDirection();
	
	/** Updates The AI after a successful movement completion */
	public abstract void moveCompletionUpdate(int x, int y);
	
	protected List<Waypoint> getWaypoint(int id){
		Entity map = GlobalVariables.globalActiveWorldsList.getActiveWorlds()[ActiveWorldList.MAP_WORLD].getSystem(PassiveCharacterActionSystem.class).getMap();
		List<Waypoint> waypoints = GlobalVariables.globalActiveWorldsList.getActiveWorlds()[ActiveWorldList.MAP_WORLD].getSystem(PassiveCharacterActionSystem.class).getMapCharListComp().get(map).getWaypointList().get(id);
		return waypoints;
	}
	
	protected Point getLocation(Entity e){
		CharacterLocationComponent loc = GlobalVariables.globalActiveWorldsList.getActiveWorlds()[ActiveWorldList.MAP_WORLD].getSystem(PassiveCharacterActionSystem.class).getLocationComp().get(e);
		Point p = new Point(loc.getX(), loc.getY());
		return p;
	}
	
}
