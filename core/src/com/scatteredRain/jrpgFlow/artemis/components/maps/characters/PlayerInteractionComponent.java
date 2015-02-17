package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.action.Action;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO: Make This Legit!

@Data
@AllArgsConstructor
public class PlayerInteractionComponent extends Component{
	
	/** The Indexes Of The Possible Triggers */
	public static final int TALKING = 0;
	public static final int LOOKING = 1;
	public static final int TOUCHING = 2;
	public static final int PUSHING = 3;
	
	/** All Actions As Mapped To Their Triggers */
	private Action talking;
	private Action looking;
	private Action touching;
	private Action pushing;
	
	
	/** Constructor Designed To Only Set One Specific Action */
	public PlayerInteractionComponent(int type, Action action){
		setAction(type, action);
	}
	
	/** Constructor Designed So To Not Set Any Actions */
	public PlayerInteractionComponent(){}
	
	
	/** Set Action To Given Action Index/Type */
	public void setAction(int type, Action action){
		if(type==TALKING){
			this.talking = action;
		}
		else if(type==LOOKING){
			this.looking = action;
		}
		else if(type==TOUCHING){
			this.touching = action;
		}
		else if(type==PUSHING){
			this.pushing = action;
		}
	}
	
	
	public boolean hasTalking(){
		return talking != null;
	}
	
	public boolean hasLooking(){
		return looking != null;
	}
	
	public boolean hasTouching(){
		return touching != null;
	}
	
	public boolean hasPushing(){
		return pushing != null;
	}

}
