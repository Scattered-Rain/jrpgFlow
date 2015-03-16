package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;

import com.artemis.Component;
import static com.scatteredRain.jrpgFlow.Constants.*;

/** Component Attached To Characters that Desires To Walk To The Given Direction */
@AllArgsConstructor
public class DesiredCharacterMovementComponent extends Component{
	
	
	
	private int desiredMoveDirection;
	
	public DesiredCharacterMovementComponent(){
		this.desiredMoveDirection = STATIONARY;
	}
	
	public boolean desiresToMove(){
		return (desiredMoveDirection!=STATIONARY);
	}
	
	public int getDesiredDirection(){
		return desiredMoveDirection;
	}
	
	public void setDesiredDirection(int direction){
		this.desiredMoveDirection = direction;
	}
	
	/** Sets The Desired Move Direction To Be Stationary, i.e. no desired movement */
	public void setStationary(){
		this.desiredMoveDirection = STATIONARY;
	}
	
}
