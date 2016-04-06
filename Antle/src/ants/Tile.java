package ants;

import java.awt.Color;

public class Tile {
	public int x, y;
	public Color color;
	private Type type;
	Entity entity;
	public Tile(int inx, int iny) {
		x = inx;
		y = iny;
		type = Types.EMPTY;
		resetColor();
	}
	void resetColor() {
		color = type.color;
	}
	public void setTo(Type intype) {
		type = intype;
		setToEntity(null);
		resetColor();
//		System.out.println(entity);
	}
	public Type getType() {
		return type;
	}
	public boolean setToEntity(Entity e) {
		if(!type.isEntity) {
			entity = e;
//			System.out.println(e);
			if(e == null) {
				entity = null;
			} else {
				e.tile = this;
				type = e.type;
			}
			
			return true; 
		} else {
			System.out.println("UNABLE TO SET " + this.toString() + "to" + e + " // " + entity + " -- Can't move?");
			return false;
		}
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void clear() {
		setTo(Types.EMPTY);
	}
}
