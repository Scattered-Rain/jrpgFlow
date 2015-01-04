package com.scatteredRain.jrpgFlow.general;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	public static final float DEFAULT_SPEED = 0.6f;
	
	/** Runs Animation Once And Then Stops, Returns Last Frame Of Animation When Done */
	public static final int NORMAL = 0;
	/** Keeps On Looping The Animation */
	public static final int LOOP = 1;
	
	/** Animation Mode */
	private int mode;
	
	/** Whether The Animation Has Finished (At Least Once) */
	private boolean finished;
	
	/** Frames */
	private TextureRegion[] frames;
	/** Time Animation Takes To Finish */
	private float animationSpeed;
	/** Delta Time Since Start. Note: Will Be Reset With LOOP and Ceiled By NORMAL */
	private float commulativeDelta;
	
	
	/** Creates New Animation With Default Speed */
	public Animation(TextureRegion[] frames, int mode){
		this(frames, mode, DEFAULT_SPEED);
	}
	
	public Animation(TextureRegion[] frames, int mode, float animationSpeed){
		this.frames = frames;
		this.animationSpeed = animationSpeed;
		this.commulativeDelta = 0;
		this.mode = mode;
	}
	
	
	/** Resets Animation */
	public void reset(){
		this.commulativeDelta = 0;
		this.finished = false;
	}
	
	/** Returns Whether The Animation Has Finished At Least Once */
	public boolean getFinished(){
		return finished;
	}
	
	/** Add Delta To This Animation */
	public void addDelta(float delta){
		this.commulativeDelta += delta;
		if(commulativeDelta>=animationSpeed){
			finished = true;
			if(mode==NORMAL){
				//Set Comm Delta within last frame
				this.commulativeDelta = animationSpeed - (animationSpeed/frames.length)/2;
			}
			else if(mode==LOOP){
				//Reset Comm Delta
				this.commulativeDelta = commulativeDelta%(animationSpeed/frames.length);
			}
			else{
				//Invalid Mode
				commulativeDelta = 0;
			}
		}
	}
	
	/** Get Current Frame */
	public TextureRegion currentFrame(){
		return frames[(int)(commulativeDelta/(animationSpeed/frames.length))];
	}
	
	/** Set Mode Of This Animation, Note: Will Inflict Reset */
	public void setMode(int mode){
		this.mode = mode;
		reset();
	}
	
	/** Set Commulative Delta */
	public void setCommulativeDelta(float delta){
		this.commulativeDelta = delta;
		addDelta(0);
	}
	
	/** Set Frame Speed, Will Inflict Reset */
	public void setAnimationSpeed(float animationSpeed){
		this.animationSpeed = animationSpeed;
		reset();
	}
	
	/** Sets Frames, Will Inflict Reset */
	public void setFrames(TextureRegion[] frames){
		this.frames = frames;
		reset();
	}
	
}
