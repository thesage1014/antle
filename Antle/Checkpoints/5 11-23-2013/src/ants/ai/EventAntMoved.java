package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntMoved extends EventObject {
	public Ant ant;
	public Tile oldTile;
	
	public EventAntMoved(Object source,  Ant inant, Tile inoldTile) {
		super(source);
		ant = inant;
		oldTile = inoldTile;
	}

}
