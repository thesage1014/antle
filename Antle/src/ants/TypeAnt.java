package ants;

import java.awt.Color;

public class TypeAnt extends Type {

	public TypeAnt() {
//			ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(1, "ANT", true, true, false, false, 0, new Color(82, 36, 41), new NoItem());
	}

}
