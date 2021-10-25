package com.skilldistillery.app.impl;

import com.skilldistillery.app.model.Fighter;
import com.skilldistillery.app.model.Jet;

public class FighterJet extends Jet implements Fighter {

	public FighterJet(String model, double speed, double range, int price) {
		super(model, speed, range, price);
	}
	
	@Override
	public void fight() {
		System.out.println("Fighting " + getModel() + "...");
	}

}
