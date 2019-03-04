package ants;

import ants.ai.JobML;
import ants.ml.AntStateData;

public class AntML extends Ant {
	Integer action;
	double reward=0;
	public AntML(Tile intile, Colony incolony) {
		super(intile, incolony);
	}
	public AntStateData buildMLState() {
		AntsMap map = colony.map;
		double[] values = new double[scanSize * scanSize];
		int x = 0;
		for(int i=-scanSize/2;i<=scanSize/2;i++) {
			int y = 0;
			for(int j=-scanSize/2;j<=scanSize/2;j++) {
//				System.out.print("(" +i + "," + j + ")");
				values[x+y*scanSize] = (map.get(i+tile.x, j+tile.y).getType().isSolid)?-1:0;
				y++;
			}
			x++;
//			System.out.println();
		}
		return new AntStateData(values,mLSteps,false);
	}
	public void SetAction(Integer a) {
		action = a;
	}
	public Integer GetAction() {
		return action;
	}
	public void AddReward(double r, Object source) {
		// TODO Auto-generated method stub
		reward += r;
	}
}
