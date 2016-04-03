package ants;

public final class Param {
	public String name = "Unnamed";
	public double min, max, start, value;
	public final boolean isToggleOnly, isReadOnly;
	public Param(boolean inIsCheckbox, boolean inReadOnly, double inMin, double inMax, double inStart, String inName) {
		isToggleOnly = inIsCheckbox;
		isReadOnly = inReadOnly;
		min = inMin;
		max = inMax;
		start = inStart;
		name = inName;
		value = inStart;
	}
	public void reset() {
		value = start;
	}
	public int i() {
		return (int)value;
	}
	public boolean b() {
		return value == 1;
	}
}