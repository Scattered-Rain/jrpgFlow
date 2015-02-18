package com.scatteredRain.jrpgFlow.artemis.systems.map;

import java.util.List;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.MapCharacterListComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.map.TileMapRenderComponent;

@Wire
public class CharacterSpriteRenderSystem extends EntitySystem{
	
	ComponentMapper<MapCharacterListComponent> charListComp;
	
	ComponentMapper<ActiveCharacterSpriteComponent> spriteComp;
	ComponentMapper<CharacterSpriteLocationComponent> locationComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	
	private OrthographicCamera camera = null;
	private MapCharacterListComponent charList = null;
	private SpriteBatch batch;

	public CharacterSpriteRenderSystem() {
		super(Aspect.getAspectForOne(ActiveCharacterSpriteComponent.class, CharacterSpriteLocationComponent.class, OrthographicCameraComponent.class, MapCharacterListComponent.class));
		this.batch = new SpriteBatch();
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		//Begin Drawing Process
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//Render Sprites According To Position On Map (Y Ordered)
		for(int cy=0; cy<charList.getHeight(); cy++){
			for(int cx=0; cx<charList.getWidth(); cx++){
				int xPos = cx;
				int yPos = charList.getHeight()-cy-1;
				List<Entity> entitiesOnTile = charList.getEntitiesAt(xPos, yPos);
				for(Entity e : entitiesOnTile){
					if(spriteComp.has(e) && locationComp.has(e)){
						TextureRegion sprite = spriteComp.get(e).getActiveSprite();
						float x = locationComp.get(e).getX();
						float y = locationComp.get(e).getY();
						x = (int)(x - (((float)sprite.getRegionWidth())/2f));
						y = (int)y;
						batch.draw(sprite, x, y);
					}
				}
			}
		}
		batch.end();
	}
	
	@Override
	protected void inserted(Entity e){
		if(cameraComp.has(e)){
			//NOTE: Assertion That Both Camera And Sprite Batch are attached to the same Entity
			camera = cameraComp.get(e).getCamera();
			charList = charListComp.get(e);
		}
		if(charListComp.has(e)){
			charList = charListComp.get(e);
		}
	}
	
	@Override
	protected void dispose(){
		super.dispose();
		batch.dispose();
	}

}
