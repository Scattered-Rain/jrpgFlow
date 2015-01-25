package com.scatteredRain.jrpgFlow.util;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalTweenManager;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Timer Supposed To Return TRUE once time has reached 1, FALSE for any value below */
@Data
public class TweenTimer {
	
	private float time;
	private float duration;
	
	public TweenTimer(){
		this.time = 0;
		this.duration = -1;
	}
	
	public void reset(){
		this.time = 0;
	}
	
	public void setFinished(){
		this.time = 1f;
	}
	
	public boolean isFinished(){
		return (time>=1f);
	}
	
	public void start(float duration){
		reset();
		this.duration = duration;
		Tween.to(this, 0, duration).target(1).ease(Linear.INOUT).start(globalTweenManager);
	}

}
