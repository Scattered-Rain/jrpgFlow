package com.scatteredRain.jrpgFlow.util;

import static com.scatteredRain.jrpgFlow.GlobalVariables.globalTweenManager;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;
import lombok.AllArgsConstructor;
import lombok.Data;

/** Timer Supposed To Return TRUE once time has reached 1, FALSE for any value below */
@Data
public class TweenTimer {
	
	private float currentAnimationTime;
	private float totalAnimationDuration;
	/** Note: When Loop Is True isFinished() will not work */
	private boolean loop = false;
	
	/** Does Not Interfere in currently running Timer Because Of The TweenEngine itself */
	public void setCurrentAnimationTime(float time){
		currentAnimationTime = time;
	}
	
	public TweenTimer(){
		this.currentAnimationTime = 0;
		this.totalAnimationDuration = -1;
	}
	
	/** Does Not Interfere in currently running Timer Because Of The TweenEngine itself */
	public void reset(){
		this.currentAnimationTime = 0;
	}
	
	/** Does Not Interfere in currently running Timer Because Of The TweenEngine itself */
	public void setFinished(){
		this.currentAnimationTime = 1f;
	}
	
	public boolean isFinished(){
		return (currentAnimationTime>=1f);
	}
	
	public void start(float duration){
		reset();
		this.totalAnimationDuration = duration;
		if(!loop){
			Tween.to(this, 0, duration).target(1).ease(Linear.INOUT).start(globalTweenManager);
		}
		else{
			Tween.to(this, 0, duration).target(1).ease(Linear.INOUT).repeat(Tween.INFINITY, 0).start(globalTweenManager);
		}
	}

}
