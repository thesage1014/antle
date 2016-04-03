package ants;

import java.awt.Color;


public abstract class Type {
	public int ID, maxHealth;
	public String name;
	public boolean isSolid, isEntity, isBreakable, givesItem;
	public Color color;
	public Item itemOnBreak;
	public Type(int inID, String inname, boolean inisSolid, boolean inisEntity, boolean inisBreakable, boolean ingivesItem, int inmaxHealth, Color incolor, Item initemOnBreak) {
		ID = inID;
		name = inname;
		isSolid = inisSolid;
		isEntity = inisEntity;
		isBreakable = inisBreakable;
		givesItem = ingivesItem;
		maxHealth = inmaxHealth;
		color = incolor;
		itemOnBreak = initemOnBreak;
	}
}
