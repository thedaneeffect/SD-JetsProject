package com.skilldistillery.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.app.impl.FighterJet;
import com.skilldistillery.app.impl.PassengerJet;
import com.skilldistillery.app.impl.TrainerJet;
import com.skilldistillery.app.model.Fighter;
import com.skilldistillery.app.model.Jet;
import com.skilldistillery.app.model.Trainer;

public class Airfield {

	private final List<Jet> jets;

	public Airfield() {
		// each airfield comes with a complimentory set of jets.
		jets = new ArrayList<>();
	}

	public void listFleet() {
		final String tblFormat = "%-4s%-24s%-16s%-16s%-16s%n";

		System.out.format(tblFormat, "ID", "Model", "Speed (mph)", "Range (mi)", "Cost (USD)");

		int index = 0;
		for (Jet jet : jets) {
			System.out.format(tblFormat, index, jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getCost());
			index++;
		}
	}

	public void flyAllJets() {
		for (Jet jet : jets) {
			jet.fly();
		}
	}
	
	public void trainAllTrainers() {
		for (Jet jet : jets) {
			if (jet instanceof Trainer) {
				((Trainer)jet).train();
			}
		}
	}
	
	public void dogfight() {
		for (Jet jet : jets) {
			if (jet instanceof Fighter) {
				((Fighter)jet).fight();
			}
		}
	}

	public Jet getFastestJet() {
		Jet fastest = null;
		for (Jet jet : jets) {
			if (fastest == null || jet.getSpeed() > fastest.getSpeed()) {
				fastest = jet;
			}
		}
		return fastest;
	}

	public Jet getLongestRangedJet() {
		Jet longest = null;
		for (Jet jet : jets) {
			if (longest == null || jet.getRange() > longest.getRange()) {
				longest = jet;
			}
		}
		return longest;
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
