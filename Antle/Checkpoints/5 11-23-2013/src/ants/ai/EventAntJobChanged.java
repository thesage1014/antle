package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntJobChanged extends EventObject {
	public Job oldJob;
	public Ant ant;
	public EventAntJobChanged(Object source, Ant inant, Job injob) {
		super(source);
		ant = inant;
		oldJob = injob;
	}
}