package ants;

import java.awt.Color;


public class TypeDirt extends Type {

	public TypeDirt() {
//		ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(2,"DIRT"		,true	,false	,true	,false		,20		,new Color(51,41,14)	,new NoItem());
	}

}
