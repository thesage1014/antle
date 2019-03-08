package ants;

import java.awt.Color;


public class TypePermanent extends Type {

	public TypePermanent() {
//		ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(5,"PERMANENT"	,true	,false	,false	,false		,999999999	,new Color(0,0,0)		,new NoItem());
	}
	
}
