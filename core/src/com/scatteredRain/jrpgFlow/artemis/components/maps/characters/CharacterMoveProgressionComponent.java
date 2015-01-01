package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.Data;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.util.TweenTimer;
import static com.scatteredRain.jrpgFlow.Constants.*;


//TODO: Implement!
/** Keeps Track Of The Movement Progression Of A Character From One Tile To An Adjacent One */
public class CharacterMoveProgressionComponent extends Component{
	
	/** 0 Means The Movement Has Just Started, 1 Means The Movement Has Already Ended */
	private TweenTimer timer;
	
	public CharacterMoveProgressionComponent(){
		this.timer = new TweenTimer();
		this.timer.setFinished();
	}
	
	public boolean isMoving(){
		return !timer.isFinished();
	}
	
	public void setMoving(float moveDuration){
		this.timer.reset();
		Tween.to(timer, 0, 1f).target(1).ease(Linear.INOUT).start(TWEEN_MANAGER);
	}
	
	/** Returns Progression Of Movement, 0 Means No Progression, 1 Means Move Finished And Currently Stationary */
	public float getProgression(){
		return timer.getTime();
	}

}
