package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.scatteredRain.jrpgFlow.GlobalVariables;

public class TransitionEffectSystem extends VoidEntitySystem{
	
	private SpriteBatch batch;
	private AtlasRegion texture;
	
	public TransitionEffectSystem(){
		this.batch = new SpriteBatch();
		this.texture = GlobalVariables.globalSpriteAtlas.findRegions("textDoneToken").get(0);
	}
	
	@Override
	protected void processSystem() {
		batch.begin();
		batch.draw(texture, 0, 0);
		batch.end();
	}
	
}
