package com.scatteredRain.jrpgFlow;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.artemis.World;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.scatteredRain.jrpgFlow.artemis.systems.TextboxRenderSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.TransitionEffectSystem;
import com.scatteredRain.jrpgFlow.enums.MapID;
import com.scatteredRain.jrpgFlow.enums.SpriteID;
import com.scatteredRain.jrpgFlow.general.ActiveWorldList;
import com.scatteredRain.jrpgFlow.general.PlayerCharacterInput;
import com.scatteredRain.jrpgFlow.general.TextboxInput;
import com.scatteredRain.jrpgFlow.general.gameVariables.GameVariables;
import com.scatteredRain.jrpgFlow.tween.CameraTweener;
import com.scatteredRain.jrpgFlow.tween.FloatPointTweener;
import com.scatteredRain.jrpgFlow.tween.TweenTimerTweener;
import com.scatteredRain.jrpgFlow.util.FloatPoint;
import com.scatteredRain.jrpgFlow.util.TweenTimer;
import com.scatteredRain.jrpgFlow.util.WorldFactory;
import com.scatteredRain.jrpgFlow.util.mapFactory.MapFactory;

import static com.scatteredRain.jrpgFlow.Constants.*;
import static com.scatteredRain.jrpgFlow.GlobalVariables.*;

public class JrpgFlow extends ApplicationAdapter {
	
	@Override
	public void create () {
		calcZoom();
		setupTween();
		globalSpriteAtlas = new TextureAtlas(Gdx.files.internal("img/packed/sprites.atlas"));
		GlobalVariables.globalPlayerSkin = SpriteID.MAD_SCIENTIST;
		globalSkin = new Skin(Gdx.files.internal("json/skin.json"), new TextureAtlas(Gdx.files.internal("img/packed/ui.atlas")));
		globalActiveWorldsList = new ActiveWorldList();
		
		InputMultiplexer input = new InputMultiplexer();
		Gdx.input.setInputProcessor(input);
		
		World[] activeWorlds = new World[2];
		
		activeWorlds[ActiveWorldList.TEXTBOX_WORLD] = WorldFactory.buildTextboxWorld();
		input.addProcessor(ActiveWorldList.TOTAL_WORLDS-ActiveWorldList.TEXTBOX_WORLD-1, new TextboxInput(activeWorlds[1].getSystem(TextboxRenderSystem.class)));
		
		input.addProcessor(ActiveWorldList.TOTAL_WORLDS-ActiveWorldList.MAP_WORLD-1, new PlayerCharacterInput());
		activeWorlds[ActiveWorldList.MAP_WORLD] = WorldFactory.buildMapWorld(MapID.DEBUG_FIRST, 0);
		
		
		globalActiveWorldsList.setAciveWorlds(activeWorlds);
		
		globalGameVariables = new GameVariables();
	}
	
	/** Calcualate Zoom Level (Slightly Adjust The Zoom Level According To Screen Size So Pixels Do Not Get All Stretchy) */
	private void calcZoom(){
		float w = Gdx.graphics.getWidth();
		int multiples = ((int)w)/((int)BASE_ZOOM);
		int leftover = (((int)w)%((int)BASE_ZOOM));
		Constants.ZOOM = BASE_ZOOM + (leftover/multiples);
	}
	
	private void setupTween(){
		globalTweenManager = new TweenManager();
		Tween.registerAccessor(FloatPoint.class, new FloatPointTweener());
		Tween.registerAccessor(TweenTimer.class, new TweenTimerTweener());
		Tween.registerAccessor(OrthographicCamera.class, new CameraTweener());
	}

	@Override
	public void render () {
		super.render();
		long t = System.currentTimeMillis();
		Gdx.gl.glClearColor(12f/255f, 12f/255f, 12f/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();
		globalTweenManager.update(delta);
		globalActiveWorldsList.update(delta);
		//System.out.println(System.currentTimeMillis()-t);
	}
	
	@Override
	public void dispose(){
		for(World world : globalActiveWorldsList.getActiveWorlds()){
			world.dispose();
		}
	}
	
}
