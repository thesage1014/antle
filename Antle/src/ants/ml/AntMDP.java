package ants.ml;

import java.util.Random;

import org.apache.commons.lang.NotImplementedException;
import org.deeplearning4j.gym.StepReply;

import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.ArrayObservationSpace;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.json.JSONObject;

import ants.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AntMDP implements MDP<AntStateData, Integer, DiscreteSpace> {
	final private static int ACTION_SIZE = 9;
	AntStateData antState = MLInterface.buildDummyState();
	@Getter
	private ObservationSpace<AntStateData> observationSpace = new ArrayObservationSpace<AntStateData>(
			new int[] { antState.getValues().length });
	MLInterface mlInterface;
	@Getter
	private DiscreteSpace actionSpace = new DiscreteSpace(ACTION_SIZE);

	public AntMDP(MLInterface mli) {
		mlInterface = mli;
	}

	@Override
	public AntStateData reset() {
		System.out.println("reset");
		return antState = mlInterface.reset();
	}

	@Override
	public void close() {
		log.info("closed");
	}

	public StepReply<AntStateData> step(Integer a) {
		antState = mlInterface.mlStep(a);
		if (antState == null) {
			antState = MLInterface.buildDummyState();
		}
//		log.info(antState.toString());
		return new StepReply<AntStateData>(antState, antState.getReward(), antState.isLastStep(), new JSONObject("{}"));
	}

	@Override
	public boolean isDone() {
		return antState.isLastStep();
	}

	@Override
	public MDP<AntStateData, Integer, DiscreteSpace> newInstance() {
		log.error("new instance not implemented");
		throw new NotImplementedException();
//		return null;
	}
}
