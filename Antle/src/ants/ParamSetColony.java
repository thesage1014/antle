package ants;

import java.util.ArrayList;

public class ParamSetColony extends ParamSet {
	public Param dirtExplorationPreference;
	public Colony colony;
	public ParamSetColony(Colony incolony) {
		colony = incolony;
		name = incolony.name;
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
													//  isBool, isList, isROnly,Min,    Max,        Default,    Description
		arrayQueue.add(dirtExplorationPreference= new Param(false,	false,	false,	0,		1d,			1,			"Dirt Exploration Preference"));

		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
