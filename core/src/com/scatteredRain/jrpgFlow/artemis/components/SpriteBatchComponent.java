package com.scatteredRain.jrpgFlow.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Holds Sprite Batch Supposed To Be Used For Rendering This World */
@Data
@AllArgsConstructor
public class SpriteBatchComponent extends Component{
	
	private SpriteBatch batch;
	
}
