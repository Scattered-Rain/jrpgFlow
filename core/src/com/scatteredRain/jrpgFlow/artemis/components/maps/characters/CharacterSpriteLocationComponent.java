package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import aurelienribon.tweenengine.TweenAccessor;

import com.artemis.Component;
import com.scatteredRain.jrpgFlow.util.FloatPoint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterSpriteLocationComponent extends Component{
	
	private FloatPoint location;
	
	public CharacterSpriteLocationComponent(float x, float y){
		this.location = new FloatPoint(x, y);
	}
	
	public float getX(){
		return location.getX();
	}
	
	public float getY(){
		return location.getY();
	}
	
	public void setX(float x){
		this.location.setX(x);
	}
	
	public void setY(float y){
		this.location.setY(y);
	}

}
