package com.skilldistillery.app.impl;

import com.skilldistillery.app.model.Jet;
import com.skilldistillery.app.model.Trainer;

public class TrainerJet extends Jet implements Trainer {
	
	public TrainerJet(String model, double speed, double range, int cost) {
		super(model, speed, range, cost);
	}

	@Override
	public void train() {
		System.out.println("Training " + getModel() + "...");
	}

}
