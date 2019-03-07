package ants;

import ants.ml.AntLearningCore;
import ants.ml.AntStateData;
import lombok.Data;
import lombok.Getter;

public class MLInterface {
	@Getter
	TickThread tickThread;
	@Getter
	AntsPanel panel;
	AntLearningCore antLearningCore;
	public MLInterface(AntsPanel inpanel) {
		panel = inpanel;
		// Call prepare scene for ML
		panel.ps.debugEventManager.raiseInitMLEvent(this);
		tickThread = panel.tickThread;
		tickThread.attachMLInterface(this);
		// Initialize rl4j
		antLearningCore = new AntLearningCore();
		// Begin step()ping
		antLearningCore.attachNewBrain(this);
	}
	public static final AntStateData buildDummyState() {
		return new AntStateData(new double[AntML.stateScanSize*AntML.stateScanSize],0,false,0);
	}
	public AntStateData step(Integer a) {
		panel.ps.debugAnt.SetAction(a);
		tickThread.step();
		AntStateData state = panel.ps.debugAnt.buildMLState();
		return state;
	}
	public AntStateData reset() {
		return buildDummyState();
	}
}
