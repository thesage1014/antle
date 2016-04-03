package ants;

public final class Types {
	public final static int EMPTY = 0;
	public final static int ANT = 1;
	public final static int DIRT = 2;
	public final static int FOOD = 3;
	public final static int STONE = 4;
	final static Type[] types = new Type[]{
//				ID,Name			,isSolid,isEntiy,breakable,MaxHP,color
		new Type(0,"EMPTY"		,false	,false	,false	,0		,new TileColor(163,132,44)),
		new Type(1,"ANT"		,true	,true	,false	,0		,new TileColor(82,36,41)),
		new Type(2,"DIRT"		,true	,false	,true	,20		,new TileColor(51,41,14)),
		new Type(3,"FOOD"		,true	,false	,true	,1		,new TileColor(135,163,44)),
		new Type(4,"STONE"		,true	,false	,true	,250	,new TileColor(50,55,30))
	}; 
}

//byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
//byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
//byte[] cb = new byte[]{(byte) 44,	22,		14,		   44};
