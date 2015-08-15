package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpriteID{
	//DEBUG
	GENTLEMAN("elderlyGentleman", 4, 4),
	STRAWHAT("strawhatBoy", 4, 4),
	MAD_SCIENTIST("madScientist", 4, 4),
	FEMALE_STUDENT("femaleStudent", 4, 4),
	CHEST("chest", 2, 1),
	
	//Sprites
	SPRITE_DR_J("sprite_drJay", 4, 4),
	SPRITE_AGENT("sprite_agent", 4, 4),
	SPRITE_DUDE("sprite_tech", 4, 4),
	SPRITE_PSY("sprite_psy", 4, 4),
	
	//Objects
	OBJ_BOOK("object_book", 1, 1),
	
	;
	
	@Getter
	private String path;
	@Getter
	private int height;
	@Getter
	private int width;
}
