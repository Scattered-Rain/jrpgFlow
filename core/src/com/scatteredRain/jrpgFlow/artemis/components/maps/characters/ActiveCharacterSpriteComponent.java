package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** The Sprite Of The Character That Is Being Rendered */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveCharacterSpriteComponent extends Component{
	
	private TextureRegion activeSprite;

}
