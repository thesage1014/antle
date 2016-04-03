package ants;

import java.util.Vector;

public interface ParamSetManager {
	public void paramUpdate(Param param);
	public ParamSet getParamSet(int i);
	public Vector<ParamSet> getParamSets();
	public ParamSetGlobal getGlobal();
	public void addParamSet(ParamSet inparamSet);
}
