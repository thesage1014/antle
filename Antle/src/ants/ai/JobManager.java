package ants.ai;

import ants.Ant;

public class JobManager {
	Job job, lastJob;
	public JobManager(Ant inant) {
		job = new JobCollectFood(inant);
	}
	public void setJob(Object source, Job injob) {
		job.antChangedJobs(new EventAntJobChanged(source, job.ant, injob));
		job = injob;
	}
	public boolean moveAnt() {
		return job.moveManager.move();
	}
}
