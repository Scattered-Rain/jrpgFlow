package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import com.artemis.Component;

@Data
public class CameraFocusComponent extends Component{
	
	public static final float GENERIC_REFOCUS_DURATION = 1f;
	
	/** If True The Systems Will Run logic To Refocus The Camera Automatically */
	private boolean refocus;
	private float refocusDuration;
	
	public CameraFocusComponent(){
		this.refocus = true;
		this.refocusDuration = 0;
	}
	
	public void refocus(){
		this.refocus = true;
		this.refocusDuration = GENERIC_REFOCUS_DURATION;
	}
	
	public void instantRefocus(){
		this.refocus = true;
		this.refocusDuration = 0;
	}
	
}
