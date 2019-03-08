package ants;

import java.awt.Color;


public class TypeEmpty extends Type {

	public TypeEmpty() {
//		ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(0,"EMPTY"		,false	,false	,false	,false		,0		,new Color(163,132,44)	,new NoItem());
	}

}
