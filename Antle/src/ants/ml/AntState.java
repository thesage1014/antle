package ants.ml;

import org.deeplearning4j.rl4j.space.Encodable;

import lombok.Value;
@Value
public class AntState implements Encodable {
	double[] values;
	int step;
	@Override
	public double[] toArray() {
		// TODO Auto-generated method stub
		return values;
	}

}
