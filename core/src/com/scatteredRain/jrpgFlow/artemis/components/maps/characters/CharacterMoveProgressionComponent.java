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
		timer.start(moveDuration);
	}
	
	/** Returns Progression Of Movement, 0 Means No Progression, 1 Means Move Finished And Currently Stationary */
	public float getProgression(){
		return timer.getTime();
	}
	
	public boolean justStarted(){
		return timer.getTime()==0;
	}
	
	public float getTotalNeededTime(){
		return timer.getDuration();
	}
	
}
