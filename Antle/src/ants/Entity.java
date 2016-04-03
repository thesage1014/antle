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
//		System.out.println(uniqueID + " " + Types.get(blockID).ID);
	}
	public void moveEntity(int inx, int iny) {
		tile.setToEntity(null);
		tile = map.get(inx, iny);
		map.get(inx, iny).setToEntity(this);
	}
	public boolean pickupItem(Item item) {
		return inv.addItem(item);
	}
	public Color getColor() {
		return new Color(0);
	}
}
