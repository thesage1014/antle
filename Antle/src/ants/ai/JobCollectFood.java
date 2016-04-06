package ants.ai;

import ants.*;

public class JobCollectFood extends Job {

	public JobCollectFood(Ant inant) {
		super(inant);
		moveManager = new MoverFindFood(inant);
	}
	@Override
	public void antRecievedItem(EventAntRecievedItem e) {
		super.antRecievedItem(e);
		if(e.item instanceof FoodItem) {
			moveManager = new MoverReturnHome(e.ant);
		}
	}
}
