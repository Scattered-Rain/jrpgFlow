package com.scatteredRain.jrpgFlow.general;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalActiveWorldsList;
import lombok.Getter;

import com.artemis.World;
import com.scatteredRain.jrpgFlow.Constants.MapID;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

public class ActiveWorldList {
	
	public static final int MAP_WORLD = 0;
	
	/** Reference to the worlds list used by the main loop */
	@Getter
	private World[] activeWorlds;
	
	public ActiveWorldList(World[] activeWorlds){
		this.activeWorlds = activeWorlds;
	}
	
	public World getWorld(int index){
		return activeWorlds[index];
	}
	
	public void setWorld(int index, World world){
		this.activeWorlds[index] = world;
	}
	
	public void switchMaps(MapID map, int enter){
		setWorld(MAP_WORLD, WorldFactory.buildMapWorld(map, enter));
	}

}
