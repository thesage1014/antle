package ants.ai;

import ants.*;

public class JobBeStinky extends Job {
	public JobBeStinky(Ant inant) {
		super(inant);
//		moveManager = new MoverRandExploreDirt(inant);
		ant.scentValue = 50;
	}
}
