package com.scatteredRain.jrpgFlow.util;

import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import static com.scatteredRain.jrpgFlow.Constants.*;
import com.scatteredRain.jrpgFlow.artemis.components.OrthographicCameraComponent;
import com.scatteredRain.jrpgFlow.artemis.components.SpriteBatchComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapCollisionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.MapComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.TileMapRenderComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteAnimationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.ActiveCharacterSpriteComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterMoveProgressionComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.CharacterSpriteLocationComponent;
import com.scatteredRain.jrpgFlow.artemis.components.maps.characters.DesiredCharacterMovementComponent;
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterMovingSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterSpriteAnimationUpdateSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.CharacterSpriteRenderSystem;
import com.scatteredRain.jrpgFlow.artemis.systems.MapRenderSystem;
import com.scatteredRain.jrpgFlow.general.Animation;

/** Builds And Returns Artemis Worlds */
public class WorldFactory {
	
	/** Build World Concerned With Map Processes */
	public static World buildMapWorld(){
		World world = new World();
		
		//Systems
		world.setSystem(new CharacterMovingSystem(), false);
		world.setSystem(new CharacterSpriteAnimationUpdateSystem(), false);
		world.setSystem(new MapRenderSystem(false), false);
		world.setSystem(new CharacterSpriteRenderSystem(), false);
		world.setSystem(new MapRenderSystem(true), false);
		world.initialize();
		
		//Entities
		Entity generalEntity = world.createEntity();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		OrthographicCamera camera = new OrthographicCamera(1, h/w);
		camera.position.x = TILE_SIZE*16;
		camera.position.y = TILE_SIZE*16;
		camera.zoom = TILE_SIZE*12;
		camera.update();
		generalEntity.addComponent(new OrthographicCameraComponent(camera));
		generalEntity.addComponent(new SpriteBatchComponent(new SpriteBatch()));
		Entity mapEntity = world.createEntity();
		buildMapEntity(mapEntity, "maps/first.tmx");
		Entity testCharacter = world.createEntity();
		buildBaseCharacter(testCharacter, "", 0, 0, 0);
		return world;
	}
	
	/** Loads New Map And Attaches All Map Components To Given Entity */
	private static Entity buildMapEntity(Entity e, String mapFile){
		TmxMapLoader mapLoader = new TmxMapLoader();
		Parameters mapLoadParams = new Parameters();
		TiledMap map = mapLoader.load(mapFile);
		e.addComponent(new MapComponent(map));
		e.addComponent(new TileMapRenderComponent(map));
		e.addComponent(new MapCollisionComponent(map));
		return e;
	}
	
	/** Adds Character To The World, Given X|Y, direction and Name of the Sprite */
	private static Entity buildBaseCharacter(Entity e, String spriteName, int x, int y, int dir){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/packed/sprites.atlas"));
		AtlasRegion atlasRegion = atlas.findRegion("strawhatBoy");
		TextureRegion[][] regions = atlasRegion.split(atlasRegion.getRegionWidth()/4, atlasRegion.getRegionHeight()/4);
		e.addComponent(new ActiveCharacterSpriteComponent(regions[2][0]));
		int xx = 16;
		int yy = 16;
		e.addComponent(new CharacterLocationComponent(xx, yy));
		e.addComponent(new CharacterSpriteLocationComponent(TILE_SIZE*xx+TILE_SIZE*0.5f, TILE_SIZE*yy));
		e.addComponent(new ActiveCharacterSpriteAnimationComponent(new Animation(regions[2], Animation.LOOP, 2)));
		e.addComponent(new DesiredCharacterMovementComponent(0));
		e.addComponent(new CharacterMoveProgressionComponent());
		return e;
	}
	

}
