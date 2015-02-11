package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MapID{
	
	DEBUG_FIRST("maps/first.tmx"),
	DEBUG_CAVE("maps/cave.tmx");
	
	@Getter
	private String path;
	
}
