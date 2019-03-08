package ants;

import ants.ml.AntLearningCore;
import ants.ml.AntStateData;

/**
 * Runs beside the panel and calls tick(tickType) of RENDER, GAME, or
 * PERFORMANCE_COUNTER
 */
public final class TickThread implements Runnable {
	final Tickable tickable;
	final int tickType;
	int delay;
	ParamSetGlobal ps;
	public boolean paused = false, alive = true, updating = false;
	MLInterface mlInterface;

	public TickThread(Tickable inTickable, int delayInMilliseconds, int inTickType, ParamSetGlobal inps) {
		tickable = inTickable;
		tickType = inTickType;
		delay = delayInMilliseconds;
		ps = inps;
		new Thread(this).start();
	}

	public void attachMLInterface(MLInterface mli) {
		mlInterface = mli;
	}

	public void step() {
		if (mlInterface != null && ps.useMachineLearning.bool()) {
			updating = true;
			tickable.tick(tickType);
			updating = false;
		} else {
			System.err.println("Stepped on by detatched MDP?");
		}
	}

	@Override
	public void run() {
		try {
			while (alive && !paused) {
				if (tickType == tickable.GAME && ps.useMachineLearning.bool()) {

				} else {
					updating = true;
					tickable.tick(tickType);
					updating = false;
				}
				Thread.sleep(delay); // need to make delay relative to the time it took to update
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void kill() {

	}

}
