package ants;

import java.awt.Color;

import ants.nouns.Item;

public abstract class Entity {
	public Inventory inv;
	public int blockID;
	public boolean pickupItem(Item item) {
		return false;
	}
	public Color getColor() {
		return new Color(0);
	}
}
