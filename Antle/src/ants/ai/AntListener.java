package ants.ai;

public interface AntListener {
	public void antMoved(EventAntMoved e);
	public void antCreated(EventAntCreated e);
	public void antDestroyed(EventAntDestroyed e);
	public void antFoundFriendInNeed(EventAntFoundFriendInNeed e);
	public void antRecievedItem(EventAntRecievedItem e);
	public void antChangedJobs(EventAntJobChanged e);
}
