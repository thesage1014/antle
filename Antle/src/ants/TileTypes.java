package ants;

import java.awt.Color;


public final class TileTypes {
	private final static Type[] types = new Type[] {
//				ID,Name			,isSolid,isEntiy,breakable,givesItem,MaxHP,color						,Item on break
		new TypeEmpty		(),
		new TypeAnt			(),
		new TypeDirt		(),
		new TypeFood		(),
		new TypeStone		(),
		new TypePermanent	()
	};
//	public final static int EMPTYID = 0;
//	public final static int ANTID = 1;
//	public final static int DIRTID = 2;
//	public final static int FOODID = 3;
//	public final static int STONEID = 4;
//	public final static int PERMANENTID = 5;
	public final static TypeEmpty EMPTY = (TypeEmpty) types[0];
	public final static TypeAnt ANT = (TypeAnt) types[1];
	public final static TypeDirt DIRT = (TypeDirt) types[2];
	public final static TypeFood FOOD = (TypeFood) types[3];
	public final static TypeStone STONE = (TypeStone) types[4];
	public final static TypePermanent PERMANENT = (TypePermanent) types[5];
//	public static Type get(int i) {
//		return types[i];
//	}
}

//byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
//byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
//byte[] cb = new byte[]{(byte) 44,	22,		14,		   44};
