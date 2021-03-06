package ants;

import ants.ml.AntStateData;
import ants.ml.JobML;

public class AntML extends Ant {
	Integer action;
	double reward=0;
	int mLSteps = 0;
	static final int stateScanSize = 3;
	public AntML(Tile intile, Colony incolony) {
		super(intile, incolony);
	}
	public AntStateData buildMLState() {
		mLSteps++;
		AntleMap map = colony.map;
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
		return new AntStateData(values,mLSteps,mLSteps > 100,action==3?10000:0);
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
