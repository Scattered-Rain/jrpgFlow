package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.SpriteBatchComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;

@Wire
public class CharacterSpriteRenderSystem extends EntitySystem{
	
	ComponentMapper<ActiveCharacterSpriteComponent> spriteComp;
	ComponentMapper<CharacterSpriteLocationComponent> locationComp;
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	ComponentMapper<SpriteBatchComponent> batchComp;

	public CharacterSpriteRenderSystem() {
		super(Aspect.getAspectForOne(ActiveCharacterSpriteComponent.class, CharacterSpriteLocationComponent.class, OrthographicCameraComponent.class, SpriteBatchComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		//Retrieving Camera And Map
		OrthographicCamera camera = null;
		SpriteBatch batch = null;
		for(Entity e : entities){
			//NOTE: Assertion That Both Camera And Sprite Batch are attached to the same Entity
			if(cameraComp.has(e) && batchComp.has(e)){
				camera = cameraComp.get(e).getCamera();
				batch = batchComp.get(e).getBatch();
				break;
			}
		}
		//Begin Drawing Process
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Entity e : entities){
			if(spriteComp.has(e) && locationComp.has(e)){
				TextureRegion sprite = spriteComp.get(e).getActiveSprite();
				float x = locationComp.get(e).getX();
				float y = locationComp.get(e).getY();
				x = (int)(x - (((float)sprite.getRegionWidth())/2f));
				y = (int)y;
				batch.draw(sprite, x, y);
			}
		}
		batch.end();
	}

}
