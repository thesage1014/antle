package ants;

public final class TickThread implements Runnable {
	final Tickable tickable;
	final int tickType;
	
	int delay;
	public boolean paused = false;
	public TickThread(Tickable inTickable, int delayInMilliseconds, int inTickType) {
		tickable = inTickable;
		delay = delayInMilliseconds;
		tickType = inTickType;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				if(!paused) {
					tickable.tick(tickType);
					Thread.sleep(delay);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void kill() {
		
	}
}
