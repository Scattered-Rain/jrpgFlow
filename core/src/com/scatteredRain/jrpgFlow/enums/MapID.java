package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MapID{
	
	//Debug
	DEBUG_FIRST("maps/first.tmx", 0),
	DEBUG_CAVE("maps/cave.tmx", 1),
	MOCK_SMALL_LAB("maps/smallLab.tmx", 2),
	
	//HeadQuarters
	HQ_REANCHAMBER("maps/hq/reanChamber.tmx", 3), //Starting Zone
	HQ_HALLWAY1("maps/hq/hallway1.tmx", 4), //Starting Zone
	HQ_INTROROOM("maps/hq/introRoom.tmx", 5),
	HQ_MAIN_HALL("maps/hq/mainHall.tmx", 6),
	HQ_LAB_DUNGEON("maps/hq/labDungeon.tmx", 7),
	
	CITY("maps/city/city.tmx", 107),
	;
	
	@Getter
	private String path;
	@Getter
	private int id;
	
}
