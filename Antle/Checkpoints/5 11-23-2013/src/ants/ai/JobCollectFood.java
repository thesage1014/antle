package ants.ai;

import ants.Ant;
import ants.FoodItem;

public class JobCollectFood extends Job {

	public JobCollectFood(Ant inant, JobManager injobManager) {
		super(inant, injobManager);
		moveManager = new MoverFindFood(inant);
	}
}
