package com.scatteredRain.jrpgFlow.tween;

import com.scatteredRain.jrpgFlow.util.TweenTimer;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenTimerTweener implements TweenAccessor<TweenTimer>{

	@Override
	public int getValues(TweenTimer timer, int arg1, float[] returnValues) {
		returnValues[0] = timer.getTime();
		return 1;
	}

	@Override
	public void setValues(TweenTimer timer, int arg1, float[] newValues) {
		timer.setTime(newValues[0]);
	}

}
