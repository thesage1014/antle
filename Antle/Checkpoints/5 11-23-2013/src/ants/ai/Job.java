package ants.ai;

import ants.*;

public class Job implements AntListener {
	public String name;
	public MovementManager moveManager;
	public JobManager jobManager;
	Ant ant;
	public Job(Ant inant, JobManager injobManager) {
		ant = inant;
		jobManager = injobManager;
		inant.addAntListener(this);
		moveManager = new MoverStandStill(inant);
	}
	@Override
	public void antMoved(EventAntMoved e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antCreated(EventAntCreated e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antDestroyed(EventAntDestroyed e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antFoundFriendInNeed(EventAntFoundFriendInNeed e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antRecievedItem(EventAntRecievedItem e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void antChangedJobs(EventAntJobChanged e) {
		// TODO Auto-generated method stub
		
	}
	
}
