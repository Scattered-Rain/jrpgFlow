package com.scatteredRain.jrpgFlow.general;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.scatteredRain.jrpgFlow.artemis.systems.TextboxRenderSystem;
import com.scatteredRain.jrpgFlow.util.TweenTimer;

public class TextboxInput extends InputAdapter{
	
	private static final int KEY_ACTION = Input.Keys.SPACE;
	
	private TextboxRenderSystem reference;
	
	private boolean action;
	
	public TextboxInput(TextboxRenderSystem reference){
		this.action = false;
		this.reference = reference;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		boolean textboxActive = reference.isActive();
		if(keycode==KEY_ACTION){
			if(!action){
				if(textboxActive){
					reference.input();
				}
			}
			this.action = true;
		}
		return textboxActive;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keycode==KEY_ACTION){
			this.action = false;
		}
		return false;
	}
	
	public boolean getAction(){
		return action;
	}
	
}
