package com.scatteredRain.jrpgFlow.artemis.components;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

/** Camera Used For Rendering In The World Of This Component */
@Data
@AllArgsConstructor
public class OrthographicCameraComponent extends Component{
	
	private OrthographicCamera camera;

}
