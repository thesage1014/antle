package ants.ai;

import ants.Ant;

public class JobOldExplore extends Job {

	public JobOldExplore(Ant inant) {
		super(inant);
		moveManager = new MoverRandExploreDirt(inant);
	}
	
}
