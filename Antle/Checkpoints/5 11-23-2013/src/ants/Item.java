package ants;

import java.awt.Color;

public abstract class Item {
	public int maxStacked = 1;
	public String name = "Undefined item";
	public float slowdownAmount = 1;
	public Color color = new Color(0);
}
