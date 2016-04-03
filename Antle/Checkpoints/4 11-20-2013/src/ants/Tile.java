package ants;

public class Tile {
	public int x, y;
	public Type type;
	Entity entity;
	World world;
	public Tile(int inx, int iny, Type intype, World inworld) {
		x = inx;
		y = iny;
		type = intype;
		world = inworld;
	}
	public Entity getEntity() {
		if(type.isEntity) {
			return entity;
		} else {
			return world;
		}
	}
}
