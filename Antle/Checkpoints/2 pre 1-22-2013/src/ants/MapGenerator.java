package ants;

import java.util.Random;

public class MapGenerator {
	ParamSet ps;
	public MapGenerator() {
		
	}
	public void generateEarlyTestMap(AntsMap map, ParamSet inParams) {
		Random rand = new Random();
		ps = inParams;
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
				if(KenPerlin.noise((ps.earthNoiseScale.value*x),(ps.earthNoiseScale.value*y),noiseZ) > ps.earthDensity.value) {
					map.set(x, y, Type.block);
				}
			}
		}
		final int antSpawnBoxSize = 32;
		for(int x=map.w/2-map.w/antSpawnBoxSize;x<map.w/2+map.w/antSpawnBoxSize;x++) {
			for(int y=map.h/2-map.h/antSpawnBoxSize;y<map.h/2+map.h/antSpawnBoxSize;y++) {
				map.set(x,y,Type.ant);
			}
		}
//		map.set(map.w/2,map.h/2,Type.ant);
//		map.set(map.w/2,map.h/2+1,Type.ant);
		for(int i=0; i<100; i++) {
			map.map[rand.nextInt(map.map.length)] = Type.food;
		}
		
		// Fence off the map
		for(int x=0; x<map.w; x++) {
			map.set(x,0,Type.block);
			map.set(x,map.h-1,Type.block);
		}
		for(int y=0; y<map.h; y++) {
			map.set(0,y,Type.block);
			map.set(map.w-1,y,Type.block);
		}
		
		
	}
}
