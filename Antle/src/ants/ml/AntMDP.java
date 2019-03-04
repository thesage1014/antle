package ants.ml;

import java.util.Random;

import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.ArrayObservationSpace;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.json.JSONObject;

import ants.Ant;
import ants.MLInterface;
import ants.TickThread;
import lombok.Getter;

public class AntMDP implements MDP<AntStateData, Integer, DiscreteSpace> {
	final private static int MAX_STEP = 20;
	final private static int SEED = 1234;
	final private static int ACTION_SIZE = 9;
	AntStateData antState = MLInterface.buildDummyState();
	@Getter
	private ObservationSpace<AntStateData> observationSpace = new ArrayObservationSpace<AntStateData>(new int[] { antState.getValues().length });
	private TickThread tickThread;
	boolean attachedToGame = false;
	@Getter
	private DiscreteSpace actionSpace = new DiscreteSpace(ACTION_SIZE);
	public AntMDP(MLInterface mli) {
		tickThread = mli.tickThread;
		attachedToGame = true;
	}
	public AntMDP() {}
	
	@Override
	public AntStateData reset() {
		return antState = MLInterface.buildDummyState();
	}

	@Override
	public void close() {
	}
	public StepReply<AntStateData> step(Integer a) {
		double reward = 0;
//		System.out.println(a);
		
		antState = tickThread.step(a);
		if(antState == null) {
			antState = MLInterface.buildDummyState();
		}
		System.out.println(antState);
//		System.out.print(reward);
		return new StepReply<AntStateData>(antState, reward, isDone(), new JSONObject("{}"));
	}

	@Override
	public boolean isDone() {
		return antState == null;
	}

	@Override
	public MDP<AntStateData, Integer, DiscreteSpace> newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

//	public static AntState[] genTestStates(int size, int seed) {
//
//		Random rd = new Random(seed);
//		AntState[] hardToyStates = new AntState[size];
//		for (int i = 0; i < size; i++) {
//
//			double[] values = new double[ACTION_SIZE];
//			int reward = 5;// rd.nextInt(ACTION_SIZE-1)+1;
//			values[0] = reward;
//			System.out.print(values[0] + ", ");
//			for (int j = 1; j < ACTION_SIZE; j++) {
//				values[j] = (j==reward)?1:0;
//				System.out.print(values[j] + ", ");
//			}
//			hardToyStates[i] = new AntState(values, i);
//			System.out.println();
//		}
//
//		return hardToyStates;
//	}
}

//public class HardDeteministicToy implements MDP<HardToyState, Integer, DiscreteSpace> {
//
//    final private static int MAX_STEP = 20;
//    final private static int SEED = 1234;
//    final private static int ACTION_SIZE = 10;
//    final private static HardToyState[] states = genToyStates(MAX_STEP, SEED);
//    @Getter
//    private DiscreteSpace actionSpace = new DiscreteSpace(ACTION_SIZE);
//    @Getter
//    private ObservationSpace<HardToyState> observationSpace = new ArrayObservationSpace(new int[] {ACTION_SIZE});
//    private HardToyState hardToyState;
//
//    public static void printTest(IDQN idqn) {
//        INDArray input = Nd4j.create(MAX_STEP, ACTION_SIZE);
//        for (int i = 0; i < MAX_STEP; i++) {
//            input.putRow(i, Nd4j.create(states[i].toArray()));
//        }
//        INDArray output = Nd4j.max(idqn.output(input), 1);
//        Logger.getAnonymousLogger().info(output.toString());
//    }
//
//    public static int maxIndex(double[] values) {
//        double maxValue = -Double.MIN_VALUE;
//        int maxIndex = -1;
//        for (int i = 0; i < values.length; i++) {
//            if (values[i] > maxValue) {
//                maxValue = values[i];
//                maxIndex = i;
//            }
//        }
//        return maxIndex;
//    }
//
//    public StepReply<HardToyState> step(Integer a) {
//        double reward = 0;
//        if (a == maxIndex(hardToyState.getValues()))
//            reward += 1;
//        hardToyState = states[hardToyState.getStep() + 1];
//        return new StepReply(hardToyState, reward, isDone(), new JSONObject("{}"));
//    }
//
//    public HardDeteministicToy newInstance() {
//        return new HardDeteministicToy();
//    }
//
//}
