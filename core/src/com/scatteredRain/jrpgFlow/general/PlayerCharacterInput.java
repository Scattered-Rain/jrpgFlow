package com.scatteredRain.jrpgFlow.general;

import static com.scatteredRain.jrpgFlow.Constants.*;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

//TODO: Integrate!

/** Manages Input That Relates To The Player Character (On Map) */
public class PlayerCharacterInput extends InputAdapter{
	
	private static final int KEY_UP = Input.Keys.W;
	private static final int KEY_RIGHT = Input.Keys.D;
	private static final int KEY_DOWN = Input.Keys.S;
	private static final int KEY_LEFT = Input.Keys.A;
	
	private static final int KEY_ACTION = Input.Keys.ENTER;
	
	private ArrayList<Integer> desDirections;
	private boolean action;
	
	
	public PlayerCharacterInput(){
		this.desDirections = new ArrayList<Integer>();
		this.action = false;
	}
	

	@Override
	public boolean keyDown(int keycode) {
		if(addDirection(keycode)){
			return true;
		}
		else if(keycode==KEY_ACTION){
			this.action = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(removeDirection(keycode)){
			return true;
		}
		else if(keycode==KEY_ACTION){
			this.action = false;
			return true;
		}
		return false;
	}
	
	
	public boolean hasDirection(){
		return (desDirections.size()>0);
	}
	
	public int getDirection(){
		return desDirections.get(0);
	}
	
	public boolean getAction(){
		return action;
	}
	
	
	//Adds A Direction To Active Directions based on Keycode (Returns Whether Direction)
	private boolean addDirection(int keycode){
		int dir = -1;
		if(keycode==KEY_UP || keycode==KEY_RIGHT || keycode==KEY_DOWN || keycode==KEY_LEFT){
			if(keycode==KEY_UP){
				dir = UP;
			}
			else if(keycode==KEY_RIGHT){
				dir = RIGHT;
			}
			else if(keycode==KEY_DOWN){
				dir = DOWN;
			}
			else if(keycode==KEY_LEFT){
				dir = LEFT;
			}
			for(int c=0; c<desDirections.size(); c++){
				if(desDirections.get(c)==dir){
					return false;
				}
			}
			desDirections.add(dir);
			return true;
		}
		else{
			return false;
		}
	}
	
	//Removes A Direction To Active Directions based on Keycode (Returns Whether Direction, or legal)
	private boolean removeDirection(int keycode){
		int dir = -1;
		if(keycode==KEY_UP || keycode==KEY_RIGHT || keycode==KEY_DOWN || keycode==KEY_LEFT){
			if(keycode==KEY_UP){
				dir = UP;
			}
			else if(keycode==KEY_RIGHT){
				dir = RIGHT;
			}
			else if(keycode==KEY_DOWN){
				dir = DOWN;
			}
			else if(keycode==KEY_LEFT){
				dir = LEFT;
			}
			for(int c=0; c<desDirections.size(); c++){
				if(desDirections.get(c)==dir){
					desDirections.remove(c);
					return true;
				}
			}
			return false;
		}
		else{
			return false;
		}
	}
	
}
