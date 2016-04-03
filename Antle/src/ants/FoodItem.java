package ants;

import java.awt.Color;

public class FoodItem extends Item {
	public FoodItem() {
		name = "Food";
		slowdownAmount = .1f;
//		color = Types.FOOD.color;
		color = new Color(135,163,44);
	}
}
