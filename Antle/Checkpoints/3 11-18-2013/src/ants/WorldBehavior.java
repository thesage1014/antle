package ants;

import java.util.Random;

public final class WorldBehavior {
	boolean backwardTick = false;
	public WorldBehavior() {
	}
	public void antTick(AntsMap map, GlobalParamSet inParams) {
//		System.out.println("Tick");
		Random rand = new Random();
		int antCount = 0;
		
		if(backwardTick) {
			for(int i=map.colonies.size()-1;i>=0;i--) {
				map.colonies.get(i).moveAnts();
			}
			for(int x=map.w-1;x>=0;x--) {
				for(int y=map.h-1;y>=0;y--) {
					tickOperations(map,x,y,rand,antCount,inParams);
				}
			}
		} else {
			for(int i=0;i<map.colonies.size();i++) {
				map.colonies.get(i).moveAnts();
			}
			for(int x=0;x<map.w;x++) {
				for(int y=0;y<map.h;y++) {
					tickOperations(map,x,y,rand,antCount,inParams);
				}
			}
		}
		backwardTick = !backwardTick;
//		System.out.println(antCount);
		
	}
	void tickOperations(AntsMap map, int x, int y, Random rand, int antCount, GlobalParamSet ps) {
//		System.out.println("asdfASDFSDF");
//		if (map.getOld(x, y) == Type.ant) {
//			antCount++;
//			map.heatMap[x+y*map.w] += rand.nextInt(ps.erosionPerStep.i())+1;
//			int heat = map.heatMap[x+y*map.w];
//			if(x>1 && x<map.w-2 && y>1 && y<map.h-2 && heat > 255) {
//				
//				map.heatMap[x+y*map.w] = 255;
//				if(ps.altErosionPattern.b()) {
//					map.heatMap[x+y*map.w + 1] *= ps.erosionPersistance.value;
//					map.heatMap[x+y*map.w - 1] *= ps.erosionPersistance.value;
//					map.heatMap[x+y*map.w + map.w] *= ps.erosionPersistance.value;
//					map.heatMap[x+y*map.w - map.w] *= ps.erosionPersistance.value;
//				}
//				map.map[x+y*map.w] = Type.ant;
//				if(map.get(x+1, y)==Type.block) map.set(x+1,y, Type.empty);
//				if(map.get(x-1, y)==Type.block) map.set(x-1,y, Type.empty);
//				if(map.get(x, y+1)==Type.block) map.set(x,y+1, Type.empty);
//				if(map.get(x, y-1)==Type.block) map.set(x,y-1, Type.empty);
//				if(rand.nextBoolean()) {
//					if(ps.altErosionPattern.b()) {
//						map.heatMap[x+y*map.w + 1 + map.w] *= ps.erosionPersistance.value;
//						map.heatMap[x+y*map.w - 1 + map.w] *= ps.erosionPersistance.value;
//						map.heatMap[x+y*map.w + 1 - map.w] *= ps.erosionPersistance.value;
//						map.heatMap[x+y*map.w - 1 - map.w] *= ps.erosionPersistance.value;
//					}
//					if(map.get(x+1, y+1)==Type.block) map.set(x+1,y+1, Type.empty);
//					if(map.get(x-1, y-1)==Type.block) map.set(x-1,y-1, Type.empty);
//					if(map.get(x-1, y+1)==Type.block) map.set(x-1,y+1, Type.empty);
//					if(map.get(x+1, y-1)==Type.block) map.set(x+1,y-1, Type.empty);
//				}
//			}
//			Ant.move(x, y, map);
//		}
	}
}
