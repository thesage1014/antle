package ants;

import java.util.Random;

public final class WorldBehavior {
	boolean backwardTick = false;
//	ParamSet ps;
	public WorldBehavior(ParamSet inParams) {
//		ps = inParams;
	}
	public void antTick(AntsMap map, ParamSet inParams) {
//		System.out.println("Tick");
		Random rand = new Random();
		int antCount = 0;
		if(backwardTick) {
			for(int x=map.w-1;x>=0;x--) {
				for(int y=map.h-1;y>=0;y--) {
					tickOperations(map,x,y,rand,antCount,inParams);
				}
			}
		} else {
			for(int x=0;x<map.w;x++) {
				for(int y=0;y<map.h;y++) {
					tickOperations(map,x,y,rand,antCount,inParams);
				}
			}
		}
		backwardTick = !backwardTick;
//		System.out.println(antCount);
		
	}
	void tickOperations(AntsMap map, int x, int y, Random rand, int antCount, ParamSet ps) {
		if (map.getOld(x, y) == Type.ant) {
			antCount++;
			map.heatMap[x+y*map.w] += rand.nextInt(ps.erosionPerStep.i())+1;
//			 Erosion can be optimized if we stop cutting out blocks on
			int heat = map.heatMap[x+y*map.w];
			if(x>1 && x<map.w-2 && y>1 && y<map.h-2 && heat > 255) {
				
				map.heatMap[x+y*map.w] = 255;
				if(ps.altErosionPattern.b()) {
					map.heatMap[x+y*map.w + 1] *= ps.erosionPersistance.value;
					map.heatMap[x+y*map.w - 1] *= ps.erosionPersistance.value;
					map.heatMap[x+y*map.w + map.w] *= ps.erosionPersistance.value;
					map.heatMap[x+y*map.w - map.w] *= ps.erosionPersistance.value;
				}
				map.map[x+y*map.w] = Type.ant;
				if(map.get(x+1, y)==Type.block) map.set(x+1,y, 0);
				if(map.get(x-1, y)==Type.block) map.set(x-1,y, 0);
				if(map.get(x, y+1)==Type.block) map.set(x,y+1, 0);
				if(map.get(x, y-1)==Type.block) map.set(x,y-1, 0);
				if(rand.nextBoolean()) {
					if(ps.altErosionPattern.b()) {
						map.heatMap[x+y*map.w + 1 + map.w] *= ps.erosionPersistance.value;
						map.heatMap[x+y*map.w - 1 + map.w] *= ps.erosionPersistance.value;
						map.heatMap[x+y*map.w + 1 - map.w] *= ps.erosionPersistance.value;
						map.heatMap[x+y*map.w - 1 - map.w] *= ps.erosionPersistance.value;
					}
					if(map.get(x+1, y+1)==Type.block) map.set(x+1,y+1, 0);
					if(map.get(x-1, y-1)==Type.block) map.set(x-1,y-1, 0);
					if(map.get(x-1, y+1)==Type.block) map.set(x-1,y+1, 0);
					if(map.get(x+1, y-1)==Type.block) map.set(x+1,y-1, 0);
				}
			}
//			Ant.move(x, y, -1, 0, map);
			int currentDir = rand.nextInt(8)*2;
			Ant.move(x, y, Util.dirs8[currentDir], Util.dirs8[currentDir+1], map);
		}
	}
}
