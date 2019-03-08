package ants;

import java.awt.Color;


public abstract class Entity {
	private static long lastUniqueID;
	public Inventory inv;
	public long uniqueID;
	public Type type;
	public Tile tile;
	AntsMap map;
	public Entity(Tile intile, AntsMap inmap, Type intype) {
		inv = new Inventory();
		uniqueID = lastUniqueID++;
		map = inmap;
		type = intype;
		tile = intile;
		tile.setToEntity(this);
	}
	public boolean moveEntity() { // Returns weather successful
		return false;
	}
	public boolean pickupItem(Item item) {
		return inv.addItem(item);
	}
	public int[] getColor() {
		return new int[]{};
	}
}
