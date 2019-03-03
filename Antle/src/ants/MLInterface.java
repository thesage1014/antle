package ants;

import ants.ml.AntStateData;
import lombok.Data;

public class MLInterface {
	public Tickable tickTarget;
	public static AntStateData buildDummyState() {
		return new AntStateData(new double[Ant.scanSize*Ant.scanSize],0);
	}
}
