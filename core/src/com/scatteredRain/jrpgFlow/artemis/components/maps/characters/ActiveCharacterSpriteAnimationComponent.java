package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Delegate;
import lombok.Getter;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scatteredRain.jrpgFlow.general.Animation;

public class ActiveCharacterSpriteAnimationComponent extends Component{
	
	@Getter
	private Animation animation;
	
	
	/** Constructs New Component, Given Animation Will Be Reset */
	public ActiveCharacterSpriteAnimationComponent(Animation animation){
		this.animation = animation;
		animation.reset();
	}
	
	
	/** Sets A New Animation, Restets This Animation As Well */
	public void setNewAnimation(Animation animation){
		this.animation = animation;
		animation.reset();
	}

}
