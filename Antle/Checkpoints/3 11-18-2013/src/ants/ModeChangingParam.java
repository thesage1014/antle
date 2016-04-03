package ants;

public class ModeChangingParam extends Param {

	public ModeChangingParam(boolean inIsCheckbox, boolean isDropDown, boolean inReadOnly, 
			double inMin, double inMax, double inStart, String inName) {
		super(inIsCheckbox, isDropDown, inReadOnly, inMin, inMax, inStart, inName);
		
	}
	public ModeChangingParam(boolean inIsCheckbox, boolean isDropDown, boolean inReadOnly, 
			double inMin, double inMax, double inStart, String inName, int[] in) {
		super(inIsCheckbox, isDropDown, inReadOnly, inMin, inMax, inStart, inName);
		
	}
}
