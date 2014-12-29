package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Delegate;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scatteredRain.jrpgFlow.general.Animation;

@Data
@AllArgsConstructor
public class ActiveCharacterSpriteAnimationComponent extends Component{
	
	private Animation animation;

}
