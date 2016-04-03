package ants;

public class Scent {
	final static int ALLANTS = 0, FOOD = 1;
	public String name;
	public int ID;
	public Colony colony;
	public float[][] values;
	public Scent(String inname, Colony incolony, int inID, int inw, int inh) {
		values = new float[inw][inh];
		colony = incolony;
		name = inname;
		ID = inID;
	}
}
