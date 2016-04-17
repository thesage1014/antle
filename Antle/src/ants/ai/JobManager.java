package ants.ai;

import ants.Ant;

public class JobManager {
	Job job, lastJob;
	public JobManager(Ant inant) {
		job = new JobDoNothing(inant);
	}
	public void setJob(Object source, Job injob) {
		job.antChangedJobs(new EventAntJobChanged(source, job.ant, injob));
		job = injob;
	}
	public void moveAnt() {
		job.moveManager.move();
	}
}
