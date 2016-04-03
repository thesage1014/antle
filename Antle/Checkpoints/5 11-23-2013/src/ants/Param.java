package ants;

public class Param {
	public String name = "Unnamed";
	public double min, max, start, value;
	public final boolean isToggleOnly, isReadOnly;
	public Param(boolean inIsCheckbox, boolean isDropDown, boolean inReadOnly, double inMin, double inMax, double inStart, String inName) {
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
	public void toggle() {
		value = value==1?0:1;
	}
	public void setToggle(boolean inValue) {
		value = inValue?0:1;
	}
}