package com.scatteredRain.jrpgFlow.action;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Textboxing implements Action{
	
	private String text;

	@Override
	public void act() {
		System.out.println(text);
	}

}
