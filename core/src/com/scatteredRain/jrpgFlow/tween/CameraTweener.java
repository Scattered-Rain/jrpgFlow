package com.scatteredRain.jrpgFlow.tween;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scatteredRain.jrpgFlow.util.FloatPoint;

public class CameraTweener implements TweenAccessor<OrthographicCamera>{
	
	public static final int LOCATION = 0;
	public static final int ZOOM = 1;

	@Override
	public int getValues(OrthographicCamera camera, int type, float[] returnValues) {
		if(type==LOCATION){
			returnValues[0] = camera.position.x;
			returnValues[1] = camera.position.y;
			return 2;
		}
		else if(type==ZOOM){
			returnValues[0] = camera.zoom;
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(OrthographicCamera camera, int type, float[] newValues) {
		if(type==LOCATION){
			camera.position.x = (int)newValues[0];
			camera.position.y = (int)newValues[1];
			camera.update();
		}
		else if(type==ZOOM){
			camera.zoom = newValues[0];
			camera.update();
		}
	}
	
	

}
