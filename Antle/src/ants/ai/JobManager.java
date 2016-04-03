package ants.ai;

import ants.Ant;

public class JobManager {
	public Job job, temporarilyDisabledJob;
	public JobManager(Job initialJob) {
		job = initialJob;
	}
	public void setJob(Object source, Job injob) {
		job.antChangedJobs(new EventAntJobChanged(source, job.ant, injob));
		job = injob;
	}
}
