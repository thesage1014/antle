package ants;

import java.awt.Color;

import ants.nouns.FoodItem;
import ants.nouns.NoItem;

public final class Types {
	public final static int EMPTY = 0;
	public final static int ANT = 1;
	public final static int DIRT = 2;
	public final static int FOOD = 3;
	public final static int STONE = 4;
	public final static int PERMANENT = 5;
	final static Type[] types = new Type[]{
//				ID,Name			,isSolid,isEntiy,breakable,givesItem,MaxHP,color						,Item on break
		new Type(0,"EMPTY"		,false	,false	,false	,false		,0		,new Color(163,132,44)	,new NoItem()),
		new Type(1,"ANT"		,true	,true	,false	,false		,0		,new Color(82,36,41)	,new NoItem()),
		new Type(2,"DIRT"		,true	,false	,true	,false		,20		,new Color(51,41,14)	,new NoItem()),
		new Type(3,"FOOD"		,true	,false	,true	,true		,15		,new Color(135,163,44)	,new FoodItem()),
		new Type(4,"STONE"		,true	,false	,true	,false		,250	,new Color(50,55,30)	,new NoItem()),
		new Type(5,"PERMANENT"	,true	,false	,false	,false		,0		,new Color(0,0,0)		,new NoItem())
	};
	public static Type get(int i) {
		return types[i];
	}
}

//byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
//byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
//byte[] cb = new byte[]{(byte) 44,	22,		14,		   44};
