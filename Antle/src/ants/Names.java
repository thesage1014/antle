package ants;

import java.util.Random;
import java.util.Vector;

public class Names {
	static final String[] names = new String[]{
		"Groundation",
		"Sufjan Stevens",
		"Nat King Cole",
		"Fats Waller",
		"Little Dragon",
		"Regina Spektor",
		"Coheed",
		"Cambria",
		"Coconut Records",
		"Portico Quartet",
		"Chairlift",
		"Reptar",
		"Broken Bells",
		"Tally Hall",
		"Courtney Marie Andrews",
		"World's End Girlfriend",
		"The Divine Comedy",
		"Beach House",
		"St. Vincent",
		"Tears for Fears",
		"Kaki King"
	};
	
	public static String getName() {
		return names[new Random().nextInt(names.length)];
	}
	
}
