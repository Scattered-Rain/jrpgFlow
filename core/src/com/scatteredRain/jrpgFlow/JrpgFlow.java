package com.scatteredRain.jrpgFlow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class JrpgFlow extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		TextureAtlas a = new TextureAtlas(Gdx.files.internal("img/packed/test.atlas"));
		AtlasRegion t = a.findRegion("portal");
		img = t.split(t.getRegionWidth()/2, t.getRegionHeight()/1)[0][0];
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
