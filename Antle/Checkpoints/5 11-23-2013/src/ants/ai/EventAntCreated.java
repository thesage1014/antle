package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntCreated extends EventObject {
	public Ant ant;
	public EventAntCreated(Object source, Ant inant) {
		super(source);
		ant = inant;
	}

}
