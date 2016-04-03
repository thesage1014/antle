package ants.ai;

import ants.Ant;
import ants.*;

public class MoverFindFood extends MovementManager {

	public MoverFindFood(Ant inant) {
		super(inant);
		
	}
//	public void move() {
//		int[] scan = scanForType(Types.FOOD);
//		if(scan.length == 0) {
//			scan
//		}
//		int i = rand.nextInt(scan.passableCount);
//		int nx = ant.x+Util.dirs8[scan.passables[i]];
//		int ny = ant.y+Util.dirs8[scan.passables[i]+1];
////		for(int i=0; i<scan.passables.length;i++) {
////			nx = ant.x+Util.dirs8[scan.passables[i]];
////			ny = ant.y+Util.dirs8[scan.passables[i]+1];
////		}
//		
//		tryMove(nx,ny);
//	}
	@Override
	boolean beMoved() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
