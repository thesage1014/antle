package ants.ai;

import ants.Ant;
import ants.*;

public class MoverFindFood extends MovementManager {

	public MoverFindFood(Ant inant) {
		super(inant);
		
	}
	@Override
	boolean beMoved() {
		Float3D[][] scentScan = scanForScent();
//		System.out.println(map.get((int)scan[5][1].x, (int)scan[5][1].y).getType().name);
		return moveTowards((int)scentScan[5][1].x, (int)scentScan[5][1].y);
	}
	
}
