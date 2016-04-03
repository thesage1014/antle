package ants;

import java.util.Random;
import java.util.Vector;

public class MapGenerator {
	GlobalParamSet ps;
	
	public MapGenerator() {
		
	}
	public void generateEarlyTestMap(AntsMap map, GlobalParamSet inParams) {
		Random rand = new Random();
		ps = inParams;
		map.colonies = new Vector<Colony>();
		map.colonies.add(new Colony(inParams, map));
//		for(int i=0; i<30000; i++) {
//			map[rand.nextInt(map.length)] = Type.block;
//		}
//		for(int i=0; i<10000; i++) { // Randomly distribute ants
////			map.map[rand.nextInt(map.length/10)] = Type.ant;
//			map.set(rand.nextInt(w/2)+w/4,rand.nextInt(map.h/2)+map.h/4,Type.ant);
//		}
		double noiseZ = rand.nextDouble()*1023;
		
		for(int x=0;x<map.w;x++) {
			for(int y=0;y<map.h;y++) {
				double curNoise = KenPerlin.noise((ps.earthNoiseScale.value*x),(ps.earthNoiseScale.value*y),noiseZ);
				if(curNoise > ps.earthDensity.value) {
					if(curNoise > .2) {
						map.set(x, y, Types.STONE);
					} else {
						map.set(x, y, Types.DIRT);
					}
				} else {
					if(rand.nextInt(5) == 0) {
						map.set(x, y, Types.FOOD);
					}
				}
			}
		}
//		map.set(map.w/2,map.h/2,Type.ant);
//		map.set(map.w/2,map.h/2+1,Type.ant);
		
		// Fence off the map
		for(int x=0; x<map.w; x++) {
			map.set(x,0,Types.DIRT);
			map.set(x,1,Types.DIRT);
			map.set(x,map.h-1,Types.DIRT);
			map.set(x,map.h-2,Types.DIRT);
			map.set(x,map.h-3,Types.DIRT);
			map.set(x,map.h-4,Types.DIRT);
			map.set(x,map.h-5,Types.DIRT);
		}
		for(int y=0; y<map.h; y++) {
			map.set(0,y,Types.DIRT);
			map.set(1,y,Types.DIRT);
			map.set(map.w-2,y,Types.DIRT);
			map.set(map.w-1,y,Types.DIRT);
		}
		for(Colony colony : map.colonies) {
			final int antSpawnBoxSize = 30;
			for(int x=map.w/2-map.w/antSpawnBoxSize;x<map.w/2+map.w/antSpawnBoxSize;x++) {
				for(int y=map.h/2-map.h/antSpawnBoxSize;y<map.h/2+map.h/antSpawnBoxSize;y++) {
					colony.addAnt(x,y);
				}
			}
		}
		
	}
}
