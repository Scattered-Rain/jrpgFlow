package com.scatteredRain.jrpgFlow.artemis.systems;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import com.scatteredRain.jrpgFlow.util.TweenTimer;

import static com.scatteredRain.jrpgFlow.GlobalVariables.*;
import static com.scatteredRain.jrpgFlow.Constants.*;

public class TextboxRenderSystem extends EntitySystem{
	
	private static final float LABEL_PADDING = 6;
	private static final float GENERAL_PADDING = 4;
	
	//Scroll Time Per Character
	private static final float SCROLL_TIME = 0.05f;
	
	/** The Table The Textbox Uses To Render */
	private Table table;
	/** The Stage That Is To Be Used */
	private Stage stage;
	
	/** Reference To Teh Actual Elements */
	private ScrollPane[] panes;
	private Label[] labels;
	private Group[] group;
	
	/** Timer for Text Scroll */
	private TweenTimer timer;
	
	/** Strings Representing An Entire Box */
	private List<String> boxStrings = null;
	/** The Currently Active Box String */
	private int currentBoxString = -1;
	/** Settings For Current Textboxing */
	private boolean box = false;
	private int place = -1;
	
	/** Had Just Been Inputed */
	private boolean input;
	
	
	public TextboxRenderSystem() {
		super(Aspect.getAspectForOne());
		
		this.timer = new TweenTimer();
		this.input = false;
		
		float labelPadding = LABEL_PADDING;
		float generalPadding = GENERAL_PADDING;
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
		
		float topGeneralPadding[] = new float[]{generalPadding, 0, 0};
		float lowGeneralPadding[] = new float[]{0, 0, generalPadding};
		
		//Create Table & Stage & Add Everything
		this.table = new Table();
		for(int c=0; c<group.length; c++){
			table.add(group[c]).expand().fill().padLeft(generalPadding).padRight(generalPadding).padTop(topGeneralPadding[c]).padBottom(lowGeneralPadding[c]);
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
		
		for(int c=0; c<3; c++){
			panes[c].setVisible(false);
			labels[c].setText("");
		}
		
		//stage.setDebugAll(true);
		
	}
	
	/** Starts New Textbox */
	public void initText(String text, int place, boolean box){
		
		float maxTextWidth = group[place].getWidth()-(LABEL_PADDING*2);
		float maxTextHeight = group[place].getHeight()-(LABEL_PADDING*2);
		Label label = labels[place];
		ArrayList<String> textLines = new ArrayList<String>();
		
		//Figure Out All The Lines
		Scanner lineScanner = new Scanner(text);
		while(lineScanner.hasNextLine()){
			Scanner wordScanner = new Scanner(lineScanner.nextLine());
			String line = "";
			String tokenLine = "";
			String nextWord = "";
			line = wordScanner.next();
			while(wordScanner.hasNext()){
				nextWord = wordScanner.next();
				tokenLine = line+" "+nextWord;
				if(label.getStyle().font.computeVisibleGlyphs(tokenLine, 0, tokenLine.length(), maxTextWidth) < tokenLine.length()){
					textLines.add(line);
					line = nextWord;
				}
				else{
					line = tokenLine;
				}
			}
			textLines.add(line);
		}
		
		//Estimate Height Of The Available Textspace (Very Crudely)
		float fontHeight = label.getStyle().font.getCapHeight();
		int lines = (int)(((maxTextHeight-fontHeight)/(fontHeight*1.8)))+1;
		
		//Add All Lines Of One Box Into A Single String Representing The Box
		ArrayList<String> boxes = new ArrayList<String>();
		int counter = 0;
		while(counter<textLines.size()){
			String boxText = "";
			for(int c=0; c<lines; c++){
				if(counter<textLines.size()){
					boxText += textLines.get(counter)+"\n";
					counter++;
				}
			}
			boxes.add(boxText);
		}
		
		//Init Textboxing
		this.boxStrings = boxes;
		this.currentBoxString = -1;
		this.box = box;
		this.place = place;
		//newBox(boxStrings.get(currentBoxString), place, box);
		advanceBox();
	}
	
	
	/** Creates New Box With The Given Parameters, Should Be Called From Inside This Class */
	private void newBox(String text, int place, boolean box){
		//Set Labels And Box If Applicable
		labels[place].setText(text);
		if(box){
			panes[place].setVisible(true);
		}
	}
	
	/** Advances Current Textbox, Gets Rid Of It If There Is Nothing More To Advance */
	public void advanceBox(){
		if(boxStrings!=null){
			if(currentBoxString+1<boxStrings.size()){
				this.currentBoxString++;
				String curBoxString = boxStrings.get(currentBoxString);
				newBox(curBoxString, place, box);
				this.timer = new TweenTimer();
				timer.start(curBoxString.length()*SCROLL_TIME);
			}
			else{
				this.currentBoxString = -1;
				this.boxStrings = null;
				//Reset Everything
				for(int c=0; c<3; c++){
					panes[c].setVisible(false);
					labels[c].setText("");
				}
				this.timer = new TweenTimer();
			}
		}
	}
	
	public boolean isActive(){
		return boxStrings!=null && currentBoxString!=-1;
	}
	
	public void input(){
		this.input = true;
	}
	
	/** Returns Whether The Text Is Currently Scrolling */
	private boolean isScrolling(){
		if(isActive()){
			if(!timer.isFinished()){
				return true;
			}
		}
		return false;
	}
	
	private String getScrollText(){
		String text = boxStrings.get(currentBoxString);
		if(isScrolling()){
			return text.substring(0, (int)(text.length()*timer.getTime()));
		}
		else{
			return text;
		}
	}
	
	private void setScrollText(){
		labels[place].setText(getScrollText());
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		stage.act(world.getDelta());
		if(input){
			if(isScrolling()){
				this.timer = new TweenTimer();
				this.timer.setFinished();
			}
			else{
				advanceBox();
			}
			this.input = false;
		}
		if(isActive()){
			setScrollText();
		}
		stage.draw();
	}
	
}
