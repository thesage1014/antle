package ants.ai;

import ants.*;

public class Job {
	public String name;
	public MovementManager manager;
	Ant ant;
	public Job(Ant inant) {
		ant = inant;
		manager = new MoverStandStill(inant);
	}
	
}
