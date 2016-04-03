package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

public class MapGenerator {
//	GlobalParamSet ps;
//	ParamSetManager pSM;
	public MapGenerator() {
		
	}
	public void generateEarlyTestMap(AntsMap map, ParamSetManager inpSM) {
		Random rand = new Random();
		map.colonies = new Vector<Colony>();
		map.colonies.add(new Colony(inpSM, map, new Color(82,36,41)));
		map.colonies.add(new Colony(inpSM, map, new Color(36,82,41)));
		ParamSetGlobal ps = inpSM.global();
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
					if(curNoise > ps.stoneLayerCutoff.value) {
						map.set(x, y, Types.STONE);
					} else {
						map.set(x, y, Types.DIRT);
					}
				} else {
					if(rand.nextInt(ps.foodSpawnAmount.i()) == 0) {
						map.set(x, y, Types.FOOD);
					}
				}
			}
		}
//		map.set(map.w/2,map.h/2,Type.ant);
//		map.set(map.w/2,map.h/2+1,Type.ant);
		
		// Fence off the map
		for(int x=0; x<map.w; x++) {
			map.set(x,0,Types.PERMANENT);
			map.set(x,1,Types.PERMANENT);
			map.set(x,map.h-1,Types.PERMANENT);
			map.set(x,map.h-2,Types.PERMANENT);
			map.set(x,map.h-3,Types.PERMANENT);
		}
		for(int y=0; y<map.h; y++) {
			map.set(0,y,Types.PERMANENT);
			map.set(1,y,Types.PERMANENT);
			map.set(map.w-2,y,Types.PERMANENT);
			map.set(map.w-1,y,Types.PERMANENT);
		}
		for(int i=0;i<map.colonies.size();i++) {
			Colony colony = map.colonies.get(i);
			final int antSpawnBoxSize = ps.antSpawnBoxSize.i();
			int distX = map.w/(map.colonies.size()+1)*(i+1);
			for(int x=distX-map.w/antSpawnBoxSize;x<distX+map.w/antSpawnBoxSize;x++) {
				for(int y=map.h/2-map.h/antSpawnBoxSize;y<map.h/2+map.h/antSpawnBoxSize;y++) {
					if(Types.types[map.get(x, y)].ID != Types.PERMANENT)
						colony.addAnt(x,y);
				}
			}
		}
		
	}
}
