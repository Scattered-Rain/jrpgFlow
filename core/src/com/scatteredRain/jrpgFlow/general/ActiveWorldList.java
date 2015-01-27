package com.scatteredRain.jrpgFlow.general;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalActiveWorldsList;
import lombok.Getter;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.scatteredRain.jrpgFlow.Constants.MapID;
import com.scatteredRain.jrpgFlow.artemis.systems.TextboxRenderSystem;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

public class ActiveWorldList {
	
	public static final int MAP_WORLD = 0;
	public static final int TEXTBOX_WORLD = 1;
	
	public static final int TOTAL_WORLDS = 2;
	
	/** Reference to the worlds list used by the main loop */
	@Getter
	private World[] activeWorlds;
	
	public ActiveWorldList(World[] activeWorlds){
		this.activeWorlds = activeWorlds;
	}
	
	public ActiveWorldList(){}
	
	public void setAciveWorlds(World[] activeWorlds){
		this.activeWorlds = activeWorlds;
	}
	
	public World getWorld(int index){
		return activeWorlds[index];
	}
	
	public void setWorld(int index, World world){
		this.activeWorlds[index] = world;
	}
	
	public InputProcessor getInput(int world){
		return ((InputMultiplexer)Gdx.input.getInputProcessor()).getProcessors().get(ActiveWorldList.TOTAL_WORLDS-world-1);
	}
	
	public void switchMaps(MapID map, int enter){
		setWorld(MAP_WORLD, WorldFactory.buildMapWorld(map, enter));
	}
	
	public void startTextbox(String text, int position, boolean box){
		((TextboxRenderSystem)activeWorlds[TEXTBOX_WORLD].getSystem(TextboxRenderSystem.class)).initText(text, position, box);
	}

}
