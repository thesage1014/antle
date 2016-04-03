package ants.ai;

import ants.*;

public class MoverReturnHome extends MovementManager {

	public MoverReturnHome(Ant inant) {
		super(inant);
	}
	@Override
	boolean beMoved() {
		Float3D[][] scan = scanForScent();
//		System.out.println(map.get((int)scan[5][1].x, (int)scan[5][1].y).getType().name);
		return moveTowards((int)scan[5][0].x, (int)scan[5][0].y);
	}
	
}
