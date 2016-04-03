package ants.ai;

import java.util.EventObject;

import ants.*;

public class EventAntDestroyed extends EventObject {
	public Tile tile;
	public EventAntDestroyed(Object source, Tile intile) {
		super(source);
		tile = intile;
	}

}
