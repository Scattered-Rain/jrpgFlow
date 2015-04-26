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
		boolean neededFilling = false;
		while(vars.size()<=index){
			vars.add(DEFAULT_INT);
			neededFilling = true;
		}
		return neededFilling;
	}
	
	private boolean fillUpBoolean(int index){
		boolean neededFilling = false;
		while(switches.size()<=index){
			switches.add(DEFAULT_BOOLEAN);
			neededFilling = true;
		}
		return neededFilling;
	}
	
}
