package ants;

public class Tile {
	public int x, y;
	private Type type;
	public int[] scents = new int[Scents.scents.length];
	Entity entity;
	public Tile(int inx, int iny) {
		x = inx;
		y = iny;
		type = Types.EMPTY;
	}
	public void setTo(Type intype) {
		type = intype;
		setToEntity(null);
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
			System.out.println("UNABLE TO MOVE " + e + " to " + entity);
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
