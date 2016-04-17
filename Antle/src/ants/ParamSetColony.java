package ants;

import java.util.ArrayList;

public class ParamSetColony extends ParamSet {
	public Param dirtExplorationPreference, allFindFood;
	public Colony colony;
	public ParamSetColony(Colony incolony) {
		colony = incolony;
		name = incolony.name;
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
																//  type, isROnly,Min,    Max,    Default,    Description
		arrayQueue.add(dirtExplorationPreference = 	new Param(Param.SLIDER,	false,	0,		1d,			1,			"Dirt Exploration Preference"));
		arrayQueue.add(allFindFood =				new Param(Param.BUTTON,	false,	0,		1,			0,			"Set all ants to find food"));
		
		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
