package ants;

import java.awt.Color;

/** One square in the game world */
public class Tile {
	public int x, y;
	public Color color;
	private Type type;
	Entity entity;
	public Tile(int inx, int iny) {
		x = inx;
		y = iny;
		type = TileTypes.EMPTY;
		resetColor();
	}
	void resetColor() {
		color = type.color;
	}
	public void setTo(Type intype) {
		type = intype;
		setToEntity(null);
		resetColor();
	}
	public void setTo(Entity e) {
		setToEntity(e);
	}
	public Type getType() {
		return type;
	}
	public boolean setToEntity(Entity e) {
		if(!type.isEntity) {
			entity = e;
			if(e == null) {
				entity = null;
			} else {
				e.tile = this;
				type = e.type;
			}
			
			return true; 
		} else {
			System.out.println("UNABLE TO SET " + this.toString() + "to" + e + " // " + entity + " -- Not entity?");
			return false;
		}
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void clear() {
		setTo(TileTypes.EMPTY);
	}
}
