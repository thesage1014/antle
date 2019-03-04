package ants;

import ants.ml.AntStateData;
import lombok.Data;

public class MLInterface {
	public Tickable tickTarget;
	public TickThread tickThread;
	public MLInterface(TickThread intickThread) {
		tickThread = intickThread;
	}
	public static AntStateData buildDummyState() {
		return new AntStateData(new double[Ant.scanSize*Ant.scanSize],0,false);
	}
}
