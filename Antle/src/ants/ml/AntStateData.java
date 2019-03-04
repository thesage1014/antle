package ants.ml;

import org.deeplearning4j.rl4j.space.Encodable;

import lombok.Value;
@Value
public class AntStateData implements Encodable {
	double[] values;
	int step;
	boolean lastStep;
	@Override
	public double[] toArray() {
		// TODO Auto-generated method stub
		return values;
	}

}
