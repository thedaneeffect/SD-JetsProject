package comm.skilldistillery.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public final class JetsApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		JetsApplication app = new JetsApplication();
		app.loadFromFile("hangar.txt");
		app.launch();
	}

	private final Scanner in;
	private final Airfield airfield;

	private JetsApplication() {
		this.in = new Scanner(System.in);
		this.airfield = new Airfield();
	}

	private void loadFromFile(String filename) throws FileNotFoundException, IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			int lineN = 0;

			while ((line = reader.readLine()) != null) {
				lineN++;

				// skip comments
				if (line.charAt(0) == '#') {
					continue;
				}

				String[] split = line.split(",");

				// skip lines without enough info
				if (split.length < 5) {
					continue;
				}

				try {
					String type = split[0];
					String model = split[1];
					double speed = Double.parseDouble(split[2]);
					double range = Double.parseDouble(split[3]);
					int cost = Integer.parseInt(split[4]);

					airfield.addJet(type, model, speed, range, cost);
				} catch (Exception e) {
					System.out.println(filename + " error line " + lineN + ": " + e);
				}
			}
		}
	}

	private void displayUserMenu() {
		System.out.println("Please select an option:");
		System.out.println("\t1) List fleet");
		System.out.println("\t2) Fly all jets");
		System.out.println("\t3) View fastest jet");
		System.out.println("\t4) View jet with longest range");
		System.out.println("\t5) Train all trainer jets");
		System.out.println("\t6) Dogfight!");
		System.out.println("\t7) Add jet to fleet");
		System.out.println("\t8) Remove jet from fleet");
		System.out.println("\t0) Quit");
	}

	private void launch() {
		// continue input in a while loop until option 0 (Quit) is selected.
		while (true) {
			this.displayUserMenu();

			System.out.print("> ");

			try {
				int option = in.nextInt();
				in.nextLine(); // devour \n

				switch (option) {
				case 1:
					listFleet();
					break;
				case 2:
					flyAllJets();
					break;
				case 3:
					printFastestJet();
					break;
				case 4:
					printLongestRangeJet();
					break;
				case 5:
					trainAllTrainers();
					break;
				case 6:
					dogfight();
					break;
				case 7:
					addJet();
					break;
				case 8:
					removeJet();
					break;
				case 0: // Quit
					return; // returns our method, effectively leaving the while loop.
				default:
					System.out.println("Invalid option: " + option);
					break;
				}
			} catch (Exception e) {
				System.out.println("Illegal option: " + e);
				in.nextLine(); // consume illegal option
			}
		}
	}

	private void listFleet() {
		final String tblFormat = "%-4s%-24s%-16s%-16s%-16s%n";

		System.out.format(tblFormat, "ID", "Model", "Speed (mph)", "Range (mi)", "Cost (USD)");

		int index = 0;
		for (Jet jet : airfield.getJets()) {
			System.out.format(tblFormat, index, jet.getModel(), jet.getSpeed(), jet.getRange(), jet.getCost());
			index++;
		}
	}

	private void flyAllJets() {
		for (Jet jet : airfield.getJets()) {
			jet.fly();
		}
	}

	private void printFastestJet() {
		Jet fastest = null;
		for (Jet jet : airfield.getJets()) {
			if (fastest == null || jet.getSpeed() > fastest.getSpeed()) {
				fastest = jet;
			}
		}
		if (fastest == null) {
			System.out.println("There are no jets!");
		} else {
			System.out.println(fastest);
		}
	}

	private void printLongestRangeJet() {
		Jet longest = null;
		for (Jet jet : airfield.getJets()) {
			if (longest == null || jet.getRange() > longest.getRange()) {
				longest = jet;
			}
		}
		if (longest == null) {
			System.out.println("There are no jets!");
		} else {
			System.out.println(longest);
		}
	}

	private void trainAllTrainers() {
		for (Jet jet : airfield.getJets()) {
			if (jet instanceof Trainer) {
				((Trainer) jet).train();
			}
		}
	}

	private void dogfight() {
		for (Jet jet : airfield.getJets()) {
			if (jet instanceof Fighter) {
				((Fighter) jet).fight();
			}
		}
	}

	private void addJet() {
		int kind;
		String type = "undefined";

		// trap the user until they choose a proper option
		while (true) {
			try {
				System.out.println("What kind of jet?");
				System.out.println("1) Passenger");
				System.out.println("2) Fighter");
				System.out.println("3) Trainer");
				System.out.println("0) Nevermind");
				System.out.print("> ");

				kind = in.nextInt();
				in.nextLine(); // consume newline

				// option outside of range
				if (kind < 0 || kind > 3) {
					throw new IllegalArgumentException();
				}

				if (kind == 1) {
					type = "passenger";
				} else if (kind == 2) {
					type = "fighter";
				} else if (kind == 3) {
					type = "trainer";
				}

				// break out of while(true);
				break;
			} catch (Exception e) {
				System.out.println("Invalid option. Please try again.");
				in.nextLine(); // consume remaining input
			}
		} // end of while(true)

		try {
			System.out.println("What kind of model?");
			System.out.print("> ");
			String model = in.nextLine();

			System.out.println("What is its top speed in mph?");
			System.out.print("> ");
			double speed = in.nextDouble();
			in.nextLine();

			System.out.println("What is its max range in miles?");
			System.out.print("> ");
			double range = in.nextDouble();
			in.nextLine();

			System.out.println("How much does it cost in whole numbers?");
			System.out.print("> $");
			int cost = in.nextInt();
			in.nextLine();

			airfield.addJet(type, model, speed, range, cost);
		} catch (Exception e) {
			System.out.println("There was a problem adding your jet");
			e.printStackTrace();

			in.nextLine(); // consume remaining input
		}
	}

	private void removeJet() {
		listFleet();

		try {
			while (true) {
				System.out.println("Type the index of a jet to delete it, or type \"back\" to cancel.");
				System.out.print("> ");

				String input = in.nextLine();

				if ("back".equalsIgnoreCase(input)) {
					return;
				}

				int index = Integer.parseInt(input);

				if (index < 0 || index >= airfield.getJets().size()) {
					System.out.println("That jet doesn't exist. Try again.");
					continue; // go back to parsing input.
				}

				Jet removed = airfield.getJets().remove(index);
				System.out.println(removed.getModel() + " has been decommissioned.");
				
				return; // exit method (breaks out of while loop)
			}
		} catch (Exception e) {
			System.out.println("Invalid input. Deletion cancelled.");
		}
	}

}
