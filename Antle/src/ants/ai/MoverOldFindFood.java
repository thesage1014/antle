package ants.ai;

import ants.Ant;
import ants.*;

public class MoverOldFindFood extends MovementManager {

	public MoverOldFindFood(Ant inant) {
		super(inant);
		
	}
	@Override
	boolean beMoved() {
		int[] surroundings = oldScanForType(Types.FOOD.ID);
		
		Float3D[][] scentScan = scanForScent();
//		System.out.println(map.get((int)scan[5][1].x, (int)scan[5][1].y).getType().name);
		return moveTowards((int)scentScan[5][1].x, (int)scentScan[5][1].y);
	}
	
}
