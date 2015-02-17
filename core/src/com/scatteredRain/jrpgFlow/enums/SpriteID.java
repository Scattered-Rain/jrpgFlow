package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpriteID{
	GENTLEMAN("elderlyGentleman"),
	STRAWHAT("strawhatBoy"),
	MAD_SCIENTIST("madScientist");
	
	@Getter
	private String path;
}
