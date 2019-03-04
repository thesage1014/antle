package ants;

import ants.ml.AntLearningCore;
import ants.ml.AntStateData;

public final class TickThread implements Runnable {
	final Tickable tickable;
	final int tickType;
	int delay;
	ParamSetGlobal ps;
	public boolean paused = false, alive = true, updating = false;
	AntLearningCore antLearningManager = null;
	AntML debugAnt;
	
	public TickThread(Tickable inTickable, int delayInMilliseconds, int inTickType, ParamSetGlobal inps) {
		tickable = inTickable;
		tickType = inTickType;
		delay = delayInMilliseconds;
		ps = inps;
		new Thread(this).start();
	}
	
	public AntStateData step(Integer a) {
		if(antLearningManager != null && ps.useMachineLearning.bool()) {			
			updating = true;
			debugAnt.SetAction(a);
			tickable.tick();
			AntStateData state = debugAnt.buildMLState();
			
			updating = false;
			return state;
		} else {
			System.err.println("Stepped on by detatched MDP?");
			return null;
		}
	}
	public void initMLAnt(AntML ant) {
		debugAnt = ant;
	}
	@Override
	public void run() {
		try {
			while(alive && !paused) {
				if(tickType == tickable.GAME && ps.useMachineLearning.bool()) {
					if(antLearningManager == null) { // TODO Move this to MLInterface
						antLearningManager = new AntLearningCore();
						antLearningManager.attachNewBrain(new MLInterface(this));
					}					
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
