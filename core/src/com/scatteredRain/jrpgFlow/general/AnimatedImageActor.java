package com.scatteredRain.jrpgFlow.general;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.scatteredRain.jrpgFlow.util.TweenTimer;

/** An Animated Image Acor */
public class AnimatedImageActor extends Image{
	
	private static final float ANIMATION_SPEED = 0.8f;
	
	private Drawable[] frames;
	private int currentFrame;
	private TweenTimer timer;
	
	public AnimatedImageActor(String name, Skin skin){
		super();
		Array<AtlasRegion> regions = skin.getAtlas().findRegions(name);
		this.frames = new Drawable[regions.size];
		for(int c=0; c<frames.length; c++){
			frames[c] = new TextureRegionDrawable(regions.get(c));
		}
		this.timer = new TweenTimer();
		timer.setLoop(true);
		timer.start(ANIMATION_SPEED);
		this.currentFrame = -1;
	}
	
	public void act(float delta){
		super.act(delta);
		if(isVisible()){
			int actualCurrentFrame = (int)(timer.getCurrentAnimationTime()*frames.length);
			if(actualCurrentFrame!=currentFrame){
				super.setDrawable(frames[actualCurrentFrame]);
				this.currentFrame = actualCurrentFrame;
			}
		}
	}

}
