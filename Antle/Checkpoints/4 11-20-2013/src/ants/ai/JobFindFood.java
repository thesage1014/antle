package ants.ai;

import ants.Ant;

public class JobFindFood extends Job {

	public JobFindFood(Ant inant) {
		super(inant);
		manager = new MoverFindFood(inant);
	}
	
}
