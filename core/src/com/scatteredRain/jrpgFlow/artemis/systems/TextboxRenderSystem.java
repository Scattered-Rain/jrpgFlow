package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class TextboxRenderSystem extends EntitySystem{
	
	/** The Game's Default Font */
	private BitmapFont font;
	
	private SpriteBatch batch;

	public TextboxRenderSystem() {
		super(Aspect.getAspectForOne());
		this.font = defaultFont;
		this.batch = new SpriteBatch();
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		batch.begin();
		font.draw(batch, "Hello", 2, 7);
		batch.end();
	}

}
