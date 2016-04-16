package ants;

import java.awt.Color;

public class Scent {
	final static int ALLANTS = 0, FOOD = 1; //IDs of basic scents that don't change IDs
	public String name;
	public int ID;
	int[] color;
	float hue;
	public Colony colony;
	public float[][] values;
	public Scent(String inname, Colony incolony, int inID, int inw, int inh) {
		values = new float[inw][inh];
		colony = incolony;
		name = inname;
		ID = inID;
		color = new int[]{colony.color.getRed(),colony.color.getGreen(),colony.color.getBlue()};
		hue = Color.RGBtoHSB(color[0], color[1], color[2],null)[1];
	}
	public float getHue() {
		return hue;
	}
	public int[] getColor() {
		return color;
	}
}
