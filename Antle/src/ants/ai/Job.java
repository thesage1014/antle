package ants.ai;

import ants.*;

public abstract class Job implements AntListener {
	public String name;
	public MovementManager moveManager;
	public JobManager jobManager;
	Ant ant;
	public Job(Ant inant) {
		ant = inant;
		jobManager = ant.jobManager;
		inant.addAntListener(this);
		moveManager = new MoverStandStill(inant);
	}
	
	@Override
	public void antMoved(EventAntMoved e) {}
	@Override
	public void antCreated(EventAntCreated e) {}
	@Override
	public void antDestroyed(EventAntDestroyed e) {}
	@Override
	public void antRecievedItem(EventAntRecievedItem e) {}
	@Override
	public void antChangedJobs(EventAntJobChanged e) {}
	
}
