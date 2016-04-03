package ants;

import java.util.ArrayList;

public class ParamSetColony extends ParamSet {
	public Param dirtExplorationPreference;
	public ParamSetColony() {
		name = "Colony";
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
													//  isBool, isList, isROnly,Min,    Max,        Default,    Description
		arrayQueue.add(dirtExplorationPreference= new Param(false,	false,	false,	0,		1d,			1,			"Dirt Exploration Preference"));

		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
