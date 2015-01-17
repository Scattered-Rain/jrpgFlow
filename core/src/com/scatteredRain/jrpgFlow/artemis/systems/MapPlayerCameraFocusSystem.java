package com.scatteredRain.jrpgFlow.artemis.systems;

import static com.scatteredRain.jrpgFlow.Constants.TILE_SIZE;
import static com.scatteredRain.jrpgFlow.Constants.TWEEN_MANAGER;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.equations.Linear;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CameraFocusComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;

/** Focuses The Camera On The Character The The Camera Focus Component Is Attached To */
@Wire
public class MapPlayerCameraFocusSystem extends EntitySystem{
	
	ComponentMapper<OrthographicCameraComponent> cameraComp;
	
	ComponentMapper<MapComponent> mapComp;
	
	ComponentMapper<CharacterLocationComponent> locationComp;
	ComponentMapper<CharacterMoveProgressionComponent> moveComp;
	ComponentMapper<CameraFocusComponent> cameraFocusComp;

	public MapPlayerCameraFocusSystem() {
		super(Aspect.getAspectForOne(OrthographicCameraComponent.class, CameraFocusComponent.class, MapComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		OrthographicCamera camera = null;
		int mapWidth = -1;
		int mapHeight = -1;
		for(Entity e : entities){
			if(cameraComp.has(e)){
				camera = cameraComp.get(e).getCamera();
			}
			if(mapComp.has(e)){
				mapWidth = mapComp.get(e).getWidth();
				mapHeight = mapComp.get(e).getHeight();
			}
		}
		for(Entity e : entities){
			if(cameraFocusComp.has(e) && locationComp.has(e)){
				if(moveComp.has(e) && moveComp.get(e).justStarted()){
					//Tell Component That Refocus Happened
					cameraFocusComp.get(e).setRefocus(false);
					//Do Processing
					float x = locationComp.get(e).getX()*TILE_SIZE+TILE_SIZE*0.5f;
					float y = locationComp.get(e).getY()*TILE_SIZE+TILE_SIZE*0.5f;
					x = readjust(x, camera.zoom, mapWidth);
					float w = Gdx.graphics.getWidth();
					float h = Gdx.graphics.getHeight();
					y = readjust(y, (h/w)*camera.zoom, mapHeight);
					Tween.to(camera, 0, moveComp.get(e).getTotalNeededTime())
							.target(x, y)
							.ease(Linear.INOUT)
							.start(TWEEN_MANAGER);
				}
				else if(cameraFocusComp.get(e).isRefocus()){
					//Tell Component That Refocus Happened
					cameraFocusComp.get(e).setRefocus(false);
					//Reset Camera Location (Even Without Movement, Hence Using 'Arbitrary' Duration
					float refocusTime = cameraFocusComp.get(e).getRefocusDuration();
					//Set To Player
					float x = locationComp.get(e).getX()*TILE_SIZE+TILE_SIZE*0.5f;
					float y = locationComp.get(e).getY()*TILE_SIZE+TILE_SIZE*0.5f;
					x = readjust(x, camera.zoom, mapWidth);
					float w = Gdx.graphics.getWidth();
					float h = Gdx.graphics.getHeight();
					y = readjust(y, (h/w)*camera.zoom, mapHeight);
					Tween.to(camera, 0, refocusTime)
							.target(x, y)
							.ease(Linear.INOUT)
							.start(TWEEN_MANAGER);
				}
			}
		}
	}
	
	//Readjusts Camera Location So That Scrolling Doesn't Go Over Map Edge If Can Be Prevented
	private float readjust(float location, float zoom, int space){
		float zoomSpace = zoom/2;
		//Map Too Small
		if(space*TILE_SIZE < zoom){
			//TODO: Test This
			location = (space*TILE_SIZE)/2;
		}
		//Too Left
		else if((location-zoomSpace) < 0){
			location = 0 + zoomSpace;
		}
		//Too Right
		else if((location+zoomSpace) > space*TILE_SIZE){
			location = space*TILE_SIZE - zoomSpace;
		}
		return location;
	}

}
