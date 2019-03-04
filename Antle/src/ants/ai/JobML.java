package ants.ai;

import ants.*;

public class JobML extends Job {
	public JobML(Ant inant) {
		super(inant);
		moveManager = new MoverMachineLearning(inant);
	}
	@Override
	public void antRecievedItem(EventAntRecievedItem e) {
		super.antRecievedItem(e);
		if(e.item instanceof FoodItem) {
			((AntML)e.ant).AddReward(1000,this);
		}
	}
}
