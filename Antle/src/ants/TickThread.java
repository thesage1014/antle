package ants;

public final class TickThread implements Runnable {
	final Tickable tickable;
	final int tickType;
	int delay;
	public boolean paused = false, alive = true, updating = false;
	
	public TickThread(Tickable inTickable, int delayInMilliseconds, int inTickType) {
		tickable = inTickable;
		delay = delayInMilliseconds;
		tickType = inTickType;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			while(alive && !paused) {
				updating = true;
				tickable.tick(tickType);
				updating = false;
				Thread.sleep(delay); // need to make delay relative to the time it took to update 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void kill() {
		
	}
}
