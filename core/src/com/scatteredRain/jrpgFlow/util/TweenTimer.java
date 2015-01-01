package com.scatteredRain.jrpgFlow.util;

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

}
