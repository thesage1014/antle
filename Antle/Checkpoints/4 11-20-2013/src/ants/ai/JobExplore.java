package ants.ai;

import ants.Ant;

public class JobExplore extends Job {

	public JobExplore(Ant inant) {
		super(inant);
		manager = new MoverRandExploreDirt(inant);
	}
	
}
