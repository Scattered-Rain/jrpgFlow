package com.scatteredRain.jrpgFlow;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.scatteredRain.jrpgFlow.tween.CameraTweener;
import com.scatteredRain.jrpgFlow.tween.FloatPointTweener;
import com.scatteredRain.jrpgFlow.tween.TweenTimerTweener;
import com.scatteredRain.jrpgFlow.util.FloatPoint;
import com.scatteredRain.jrpgFlow.util.TweenTimer;
import com.scatteredRain.jrpgFlow.util.WorldFactory;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class JrpgFlow extends ApplicationAdapter {
	
	/** Array Of All The Worlds Currently Active */
	private ArrayList<World> activeWorlds;
	
	@Override
	public void create () {
		SPRITE_ATLAS = new TextureAtlas(Gdx.files.internal("img/packed/sprites.atlas"));
		
		activeWorlds = new ArrayList<World>();
		activeWorlds.add(WorldFactory.buildMapWorld());
		
		setupTween();
	}
	
	private void setupTween(){
		TWEEN_MANAGER = new TweenManager();
		Tween.registerAccessor(FloatPoint.class, new FloatPointTweener());
		Tween.registerAccessor(TweenTimer.class, new TweenTimerTweener());
		Tween.registerAccessor(OrthographicCamera.class, new CameraTweener());
	}

	@Override
	public void render () {
		long t = System.currentTimeMillis();
		Gdx.gl.glClearColor(12f/255f, 12f/255f, 12f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();
		for(World world : activeWorlds){
			TWEEN_MANAGER.update(delta);
			world.setDelta(delta);
			world.process();
		}
		//System.out.println(System.currentTimeMillis()-t);
	}
	
	@Override
	public void dispose(){
		for(World world : activeWorlds){
			world.dispose();
		}
	}
	
}
