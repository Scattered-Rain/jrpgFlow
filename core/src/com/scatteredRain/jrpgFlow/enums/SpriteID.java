package com.scatteredRain.jrpgFlow.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SpriteID{
	GENTLEMAN("elderlyGentleman", 4, 4),
	STRAWHAT("strawhatBoy", 4, 4),
	MAD_SCIENTIST("madScientist", 4, 4),
	CHEST("chest", 2, 1);
	
	@Getter
	private String path;
	@Getter
	private int height;
	@Getter
	private int width;
}
