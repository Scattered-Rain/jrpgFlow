package com.scatteredRain.jrpgFlow.general;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalActiveWorldsList;

import java.util.ArrayList;

import lombok.Getter;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.scatteredRain.jrpgFlow.GlobalVariables;
import com.scatteredRain.jrpgFlow.action.Action;
import com.scatteredRain.jrpgFlow.action.coreAction.Teleport;
import com.scatteredRain.jrpgFlow.artemis.systems.TextboxRenderSystem;
import com.scatteredRain.jrpgFlow.enums.MapID;
import com.scatteredRain.jrpgFlow.util.WorldFactory;

public class ActiveWorldList {
	
	public static final int MAP_WORLD = 0;
	public static final int TEXTBOX_WORLD = 1;
	
	public static final int TOTAL_WORLDS = 2;
	
	private ArrayList<AwlRequest> requests;
	
	/** Reference to the worlds list used by the main loop */
	@Getter
	private World[] activeWorlds;
	
	public ActiveWorldList(World[] activeWorlds){
		this();
		this.activeWorlds = activeWorlds;
	}
	
	public ActiveWorldList(){
		this.requests = new ArrayList<AwlRequest>();
	}
	
	public void setAciveWorlds(World[] activeWorlds){
		this.activeWorlds = activeWorlds;
	}
	
	public World getWorld(int index){
		return activeWorlds[index];
	}
	
	public void setWorld(int index, World world){
		this.activeWorlds[index] = world;
	}
	
	/** Returns Input Processor Of Specified World */
	public InputProcessor getInput(int world){
		return ((InputMultiplexer)Gdx.input.getInputProcessor()).getProcessors().get(ActiveWorldList.TOTAL_WORLDS-world-1);
	}
	
	/** Updates All Worlds */
	public void update(float delta){
		for(World world : getActiveWorlds()){
			world.setDelta(delta);
			world.process();
		}
		handleRequests();
	}
	
	/** Handles All Requests That Have Been Send To The Lsit, Like Teleporting or Initiating Textboxes */
	private void handleRequests(){
		for(AwlRequest request : requests){
			request.doRequest();
		}
		requests.clear();
	}
	
	/** Adds A New Request To Be Executed After This Processing Cycle */
	public void sendRequest(AwlRequest request){
		this.requests.add(request);
	}
	
	public void switchMaps(MapID map, int enter){
		GlobalVariables.currentMapID = map;
		if(activeWorlds!=null && activeWorlds[MAP_WORLD] != null){
			activeWorlds[MAP_WORLD].dispose();
		}
		setWorld(MAP_WORLD, WorldFactory.buildMapWorld(map, enter));
	}
	
	public void startTextbox(String text, int position, boolean box){
		((TextboxRenderSystem)activeWorlds[TEXTBOX_WORLD].getSystem(TextboxRenderSystem.class)).initText(text, position, box);
	}
	
	public void startTextbox(String text, int position, boolean box, CompletionListener completionListener){
		((TextboxRenderSystem)activeWorlds[TEXTBOX_WORLD].getSystem(TextboxRenderSystem.class)).initText(text, position, box, completionListener);
	}

}
