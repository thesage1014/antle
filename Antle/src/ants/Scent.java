package ants;

import java.awt.Color;

public class Scent {
	final static int FOODindex = 0; //IDs of basic scents that don't change IDs
	public String name;
	public int index;
	int[] color;
	float hue;
	public float[][] values;
	public Scent(String inname, int[] incolor, int inindex, int inw, int inh) {
		values = new float[inw][inh];
		name = inname;
		color = incolor;
		index = inindex;
		hue = Color.RGBtoHSB(color[0], color[1], color[2],null)[0];
	}
	public float getHue() {
		return hue;
	}
	public int[] getColor() {
		return color;
	}
}
