package ants.ai;

import ants.Ant;

public class JobOldExplore extends Job {

	public JobOldExplore(Ant inant, JobManager injobmanager) {
		super(inant, injobmanager);
		moveManager = new MoverRandExploreDirt(inant);
	}
	
}
