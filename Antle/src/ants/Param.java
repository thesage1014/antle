package ants;

public class Param {
	public static final int SLIDER = 0, TOGGLE = 1, LIST = 2, BUTTON = 3; // option types
	public String name = "Unnamed";
	public double min, max, start, value;
	public final boolean isReadOnly;
	public final int type;
	public Param(int intype, boolean inReadOnly, double inMin, double inMax, double inStart, String inName) {
		isReadOnly = inReadOnly;
		type = intype;
		min = inMin;
		max = inMax;
		start = inStart;
		name = inName;
		value = inStart;
	}
	public boolean buttonPressed() {
		if(value != 0) {
			value = 0;
			return true;
		}
		value = 0;
		return false;
	}
	public void reset() {
		value = start;
	}
	public int i() {
		return (int)value;
	}
	public boolean bool() {
		return value != 0;
	}
	public void toggle() {
		value = value==1?0:1;
	}
	public void setToggle(boolean inValue) {
		value = inValue?0:1;
	}
}