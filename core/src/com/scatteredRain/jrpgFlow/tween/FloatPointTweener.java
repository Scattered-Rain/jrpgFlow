package com.scatteredRain.jrpgFlow.tween;

import com.scatteredRain.jrpgFlow.util.FloatPoint;

import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenUtils;

public class FloatPointTweener implements TweenAccessor<FloatPoint>{
	

	@Override
	public int getValues(FloatPoint point, int arg1, float[] returnValues) {
		returnValues[0] = point.getX();
		returnValues[1] = point.getY();
		return 2;
	}

	@Override
	public void setValues(FloatPoint point, int arg1, float[] newValues) {
		point.setX(newValues[0]);
		point.setY(newValues[1]);
	}

}
