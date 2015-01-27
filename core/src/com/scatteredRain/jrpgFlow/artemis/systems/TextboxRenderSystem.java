package com.scatteredRain.jrpgFlow.artemis.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.scatteredRain.jrpgFlow.GlobalVariables.*;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class TextboxRenderSystem extends EntitySystem{
	
	/** The Table The Textbox Uses To Render */
	private Table table;
	/** The Stage That Is To Be Used */
	private Stage stage;
	
	
	private ScrollPane[] panes;
	private Label[] labels;
	private Group[] group;
	
	
	public TextboxRenderSystem() {
		super(Aspect.getAspectForOne());
		
		float labelPadding = 5;
		float generalPadding = 4;
		float fontScale = 1f;
		
		this.panes = new ScrollPane[3];
		this.group = new Group[3];
		this.labels = new Label[3];
		
		//Define All Labels
		for(int c=0; c<group.length; c++){
			//Pane
			ScrollPane pane = new ScrollPane(new Label("", globalSkin), globalSkin);
			pane.setScrollingDisabled(true, true);
			pane.setFillParent(true);
			this.panes[c] = pane;
			//Pane Table
			Table paneTable = new Table();
			paneTable.add(pane).expand().fill();
			paneTable.pack();
			paneTable.setFillParent(true);
			//Label
			Label label = new Label("", globalSkin);
			label.setFontScale(fontScale);
			label.setWrap(true);
			label.setAlignment(Align.topLeft);
			this.labels[c] = label;
			//Label Table
			Table labelTable = new Table();
			labelTable.add(label).expand().fill().padLeft(labelPadding).padRight(generalPadding).padTop(generalPadding).padBottom(generalPadding);
			labelTable.pack();
			labelTable.setFillParent(true);
			//Group
			this.group[c] = new Group();
			group[c].addActor(paneTable);
			group[c].addActor(labelTable);
		}
		
		//Create Table & Stage & Add Everything
		this.table = new Table();
		for(int c=0; c<group.length; c++){
			table.add(group[c]).expand().fill().padLeft(generalPadding).padRight(generalPadding).padTop(generalPadding).padBottom(generalPadding);
			table.row();
		}
		table.pack();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float vW = ZOOM;
		float vH = h/w*ZOOM;
		OrthographicCamera camera = new OrthographicCamera(1, h/w);
		camera.zoom = ZOOM;
		camera.position.x = (vW/2);
		camera.position.y = (vH/2);
		camera.update();
		this.stage = new Stage();
		stage.setViewport(new FitViewport(vW, vH, camera));
		
		stage.addActor(table);
		table.setFillParent(true);
		table.validate();
		//stage.setDebugAll(true);
		
		
		panes[0].setVisible(false);
		panes[1].setVisible(false);
		labels[2].setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		stage.act(world.getDelta());
		stage.draw();
	}

}
