package com.scatteredRain.jrpgFlow.artemis.components.maps.characters;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.scatteredRain.jrpgFlow.general.Animation;

/** Holds All Animations Of A Character Used On A Normal Map */
@Data
@AllArgsConstructor
public class MapCharacterAnimationSetComponent extends Component{
	
	//Integer Defining The Currently Active Set Of The Animation Set, -1 implies non existence of that set
	private int activeSingle;
	private int activeStanding;
	private int activeWalking;
	
	//Passive Animation Set
	private Animation[] single;
	private Animation[][] standing;
	private Animation[][] walking;
	
	
	/** Sets Up New Set Using A 4x4 Directional Sprite Sheet */
	public MapCharacterAnimationSetComponent(TextureAtlas atlas, String textureName){
		AtlasRegion atlasRegion = atlas.findRegion(textureName);
		TextureRegion[][] regions = atlasRegion.split(atlasRegion.getRegionWidth()/4, atlasRegion.getRegionHeight()/4);
		this.single = null;
		this.standing = new Animation[1][4];
		this.walking = new Animation[1][4];
		for(int c=0; c<4; c++){
			this.standing[0][c] = new Animation(new TextureRegion[]{regions[c][0]}, Animation.LOOP);
			this.walking[0][c] = new Animation(regions[c], Animation.LOOP);
		}
		this.activeSingle = -1;
		this.activeStanding = 0;
		this.activeWalking = 0;
	}
	
	
	public boolean hasSingle(){
		return single!=null;
	}
	
	public boolean hasStanding(){
		return standing!=null;
	}
	
	public boolean hasWalking(){
		return walking!=null;
	}
	
	public Animation getActiveSingle(){
		if(activeSingle==-1){
			return null;
		}
		return single[activeSingle];
	}
	
	public Animation getActiveStanding(int direction){
		if(activeStanding==-1){
			return null;
		}
		return standing[activeStanding][direction];
	}
	
	public Animation getActiveWalking(int direction){
		if(activeWalking==-1){
			return null;
		}
		return walking[activeWalking][direction];
	}

}
