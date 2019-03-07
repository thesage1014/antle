package ants;

import ants.ai.JobML;
import ants.ml.AntStateData;

public class AntML extends Ant {
	Integer action;
	double reward=0;
	int mLSteps = 0;
	static final int stateScanSize = 5;
	public AntML(Tile intile, Colony incolony) {
		super(intile, incolony);
	}
	public AntStateData buildMLState() {
		mLSteps++;
		AntsMap map = colony.map;
		double[] values = new double[stateScanSize * stateScanSize];
		int x = 0;
		for(int i=-stateScanSize/2;i<=stateScanSize/2;i++) {
			int y = 0;
			for(int j=-stateScanSize/2;j<=stateScanSize/2;j++) {
//				System.out.print("(" +i + "," + j + ")");
				Tile targetTile = map.get(i+tile.x, j+tile.y);
				values[x+y*stateScanSize] = targetTile.getType().ID;
				y++;
			}
			x++;
//			System.out.println();
		}
		return new AntStateData(values,mLSteps,mLSteps > 100,reward);
	}
	public void mlStep(Integer a) {
		action = a;
		reward = a==4?0:10;
	}
	public Integer GetAction() {
		return action;
	}
	public void AddReward(double r, Object source) {
		reward += r;
	}
}
