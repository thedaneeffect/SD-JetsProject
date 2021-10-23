package comm.skilldistillery.app;

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
	
}
