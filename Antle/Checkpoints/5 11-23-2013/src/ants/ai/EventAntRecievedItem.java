package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntRecievedItem extends EventObject {
	public Ant ant;
	public Item item;
	public EventAntRecievedItem(Object source, Ant inant, Item initem) {
		super(source);
		ant = inant;
		item = initem;
	}

}
