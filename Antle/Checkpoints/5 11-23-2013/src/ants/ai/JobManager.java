package ants.ai;

import ants.Ant;

public class JobManager {
	public Job job, temporarilyDisabledJob;
	public JobManager(Job initialJob) {
		job = initialJob;
	}
}
