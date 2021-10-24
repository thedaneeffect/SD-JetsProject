package com.skilldistillery.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Airfield {

	private final List<Jet> jets;
	
	public Airfield() {
		// each airfield comes with a complimentory set of jets.
		jets = new ArrayList<>();
	}

	public List<Jet> getJets() {
		return jets;
	}
	
	public void addJet(String type, String model, double speed, double range, int cost) throws IOException {
		Jet jet;

		switch (type) {
		case "passenger":
			jet = new PassengerJet(model, speed, range, cost);
			break;
		case "fighter":
			jet = new FighterJet(model, speed, range, cost);
			break;
		case "trainer":
			jet = new TrainerJet(model, speed, range, cost);
			break;
		default:
			throw new IOException("unknown type: " + type);
		}

		jets.add(jet);
	}
	
}
