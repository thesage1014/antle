package ants;

import java.awt.Color;


public final class TileTypes {
	private final static Type[] types = new Type[] {
		new TypeEmpty		(),
		new TypeAnt			(),
		new TypeDirt		(),
		new TypeFood		(),
		new TypeStone		(),
		new TypePermanent	()
	};
	public final static TypeEmpty EMPTY = (TypeEmpty) types[0];
	public final static TypeAnt ANT = (TypeAnt) types[1];
	public final static TypeDirt DIRT = (TypeDirt) types[2];
	public final static TypeFood FOOD = (TypeFood) types[3];
	public final static TypeStone STONE = (TypeStone) types[4];
	public final static TypePermanent PERMANENT = (TypePermanent) types[5];
}
