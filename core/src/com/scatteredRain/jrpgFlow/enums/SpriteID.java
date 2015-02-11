package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpriteID{
	GENTLEMAN("elderlyGentleman"),
	STRAWHAT("strawhatBoy");
	
	@Getter
	private String path;
}
