package ants;

import java.awt.Color;

public class TypeFood extends Type {

	public float scentValue = 1;

	public TypeFood() {
//		ID,Name	,isSolid,isEntiy,breakable,givesItem,MaxHP,color,Item on break
		super(3, "FOOD", true, false, true, true, 15, new Color(135, 163, 44), new FoodItem());
	}

}
