package ants;

import java.util.ArrayList;

public class ColonyParamSet extends ParamSet {
	public Param explorationPreference;
	public ColonyParamSet() {
		ArrayList<Param> arrayQueue = new ArrayList<Param>();
													//  isBool, isList, isROnly,Min,    Max,        Default,    Description
		arrayQueue.add(explorationPreference= new Param(false,	false,	false,	0,		1d,			0,			"Exploration Preference"));

		params = (Param[]) arrayQueue.toArray(new Param[arrayQueue.size()]);
	}
}
