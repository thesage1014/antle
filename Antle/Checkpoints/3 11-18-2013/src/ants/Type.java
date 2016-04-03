package ants;

public class Type {
	public int ID, maxHealth;
	public String name;
	public boolean isSolid, isEntity, isBreakable;
	public TileColor color;
	public Type(int inID, String inname, boolean inisSolid, boolean inisEntity, boolean inisBreakable, int inmaxHealth, TileColor incolor) {
		ID = inID;
		name = inname;
		isSolid = inisSolid;
		isEntity = inisEntity;
		isBreakable = inisBreakable;
		maxHealth = inmaxHealth;
		color = incolor;
	}
}
