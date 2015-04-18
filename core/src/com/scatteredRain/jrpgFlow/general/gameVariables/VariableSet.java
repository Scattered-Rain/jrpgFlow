package com.scatteredRain.jrpgFlow.general.gameVariables;

import java.util.ArrayList;
import java.util.List;

public class VariableSet {
	
	private static final int DEFAULT_INT = 0;
	private static final boolean DEFAULT_BOOLEAN = false;
	
	private List<Integer> vars;
	private List<Boolean> switches;
	
	
	public VariableSet(){
		this.vars = new ArrayList<Integer>();
		this.switches = new ArrayList<Boolean>();
	}
	
	
	public void setVar(int index, int value){
		fillUpInt(index);
		vars.set(index, value);
	}
	
	public int getVar(int index){
		fillUpInt(index);
		return vars.get(index);
	}
	
	public void setSwitch(int index, boolean value){
		fillUpBoolean(index);
		switches.set(index, value);
	}
	
	public boolean getSwitch(int index){
		fillUpBoolean(index);
		return switches.get(index);
	}
	
	private boolean fillUpInt(int index){
		if(index >= vars.size()){
			for(int c=0; c<index-vars.size()+1; c++){
				vars.add(DEFAULT_INT);
			}
			return true;
		}
		return false;
	}
	
	private boolean fillUpBoolean(int index){
		if(index >= switches.size()){
			for(int c=0; c<index-switches.size()+1; c++){
				switches.add(DEFAULT_BOOLEAN);
			}
			return true;
		}
		return false;
	}
	
}
