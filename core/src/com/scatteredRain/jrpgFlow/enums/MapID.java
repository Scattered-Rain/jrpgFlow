package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MapID{
	
	DEBUG_FIRST("maps/first.tmx", 0),
	DEBUG_CAVE("maps/cave.tmx", 1),
	MOCK_SMALL_LAB("maps/smallLab.tmx", 2);
	
	@Getter
	private String path;
	@Getter
	private int id;
	
}
