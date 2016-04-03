package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntFoundFriendInNeed extends EventObject {
	public Ant ant;
	public EventAntFoundFriendInNeed(Object source, Ant inant) {
		super(source);
		ant = inant;
	}

}
