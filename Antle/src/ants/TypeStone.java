package ants;

import java.awt.Color;


public class TypeStone extends Type {

	public TypeStone() {
//		ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(4,"STONE"		,true	,false	,true	,false		,250	,new Color(50,55,30)	,new NoItem());
	}

}
