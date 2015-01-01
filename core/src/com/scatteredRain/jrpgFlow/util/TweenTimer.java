package com.scatteredRain.jrpgFlow.util;

import static com.scatteredRain.jrpgFlow.Constants.TWEEN_MANAGER;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Timer Supposed To Return TRUE once time has reached 1, FALSE for any value below */
@Data
@AllArgsConstructor
public class TweenTimer {
	
	private float time;
	
	public TweenTimer(){
		this.time = 0;
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
		Tween.to(this, 0, duration).target(1).ease(Linear.INOUT).start(TWEEN_MANAGER);
	}

}
