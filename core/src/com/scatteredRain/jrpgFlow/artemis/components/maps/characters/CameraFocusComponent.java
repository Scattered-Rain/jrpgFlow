package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import com.artemis.Component;

@Data
public class CameraFocusComponent extends Component{
	
	/** If True The Systems Will Run logic To Refocus The Camera Automatically */
	private boolean refocus;
	
	public CameraFocusComponent(){
		this.refocus = true;
	}
	
}
