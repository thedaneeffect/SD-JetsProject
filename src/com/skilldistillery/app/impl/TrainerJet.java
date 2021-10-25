package com.skilldistillery.app.impl;

import com.skilldistillery.app.model.Jet;
import com.skilldistillery.app.model.Trainer;

public class TrainerJet extends Jet implements Trainer {
	
	public TrainerJet(String model, double speedMPH, double rangeMiles, int cost) {
		super(model, speedMPH, rangeMiles, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void train() {
		System.out.println("Training " + getModel() + "...");
	}

}
