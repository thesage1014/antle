package ants;

import java.util.Random;

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
	public static Random rand;
	public MLInterface(AntsPanel inpanel) {
		panel = inpanel;
		// Call prepare scene for ML
		panel.ps.debugEventManager.raiseInitMLEvent(this);
		tickThread = panel.tickThread;
		tickThread.attachMLInterface(this);
		// Initialize rl4j
		antLearningCore = new AntLearningCore();
		rand = new Random(antLearningCore.RANDOM_SEED);
		// Begin step()ping
		antLearningCore.attachNewBrain(this);
	}
	public static final AntStateData buildDummyState() {
		double[] dummyObvs = new double[AntML.stateScanSize*AntML.stateScanSize];
		for(int i=0; i<dummyObvs.length; i++) {
			dummyObvs[i] = rand.nextInt(6);
		}
		return new AntStateData(dummyObvs ,0,false,0);
	}
	public AntStateData mlStep(Integer a) {
		panel.ps.debugAnt.mlStep(a);
		tickThread.step();
		AntStateData state = panel.ps.debugAnt.buildMLState();
		return state;
	}
	public AntStateData reset() {
		panel.restart();
		return buildDummyState();
	}
}
