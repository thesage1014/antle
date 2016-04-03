package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

public class MapGenerator {
	public MapGenerator() {
		
	}
	public void generateEarlyTestMap(AntsMap map, ParamSetManager inpSM) {
		Random rand = new Random();
		map.colonies = new Vector<Colony>();
//		map.colonies.add(new Colony(inpSM, map, new Color(82,36,41)));
//		map.colonies.add(new Colony(inpSM, map, new Color(20,40,20)));
		for(int i=0;i<inpSM.getGlobal().numColonies.i();i++) {
			map.colonies.add(new Colony(inpSM, map, new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)),i)); // TODO Ant indexes need to be applied properly in the map
		}
		ParamSetGlobal ps = inpSM.getGlobal();
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
						map.get(x, y).setTo(Types.STONE);
					} else {
						map.get(x, y).setTo(Types.DIRT);
					}
				} else {
					if(rand.nextInt(ps.foodSpawnAmount.i()) == 0) {
						map.get(x, y).setTo(Types.FOOD);
					}
				}
			}
		}
//		map.set(map.w/2,map.h/2,Type.ant);
//		map.set(map.w/2,map.h/2+1,Type.ant);
		
		// Fence off the map
		for(int x=0; x<map.w; x++) {
			map.get(x,0).setTo(Types.PERMANENT);
			map.get(x,1).setTo(Types.PERMANENT);
			map.get(x,map.h-1).setTo(Types.PERMANENT);
			map.get(x,map.h-2).setTo(Types.PERMANENT);
			map.get(x,map.h-3).setTo(Types.PERMANENT);
		}
		for(int y=0; y<map.h; y++) {
			map.get(0,y).setTo(Types.PERMANENT);
			map.get(1,y).setTo(Types.PERMANENT);
			map.get(map.w-2,y).setTo(Types.PERMANENT);
			map.get(map.w-1,y).setTo(Types.PERMANENT);
		}
		for(int i=0;i<map.colonies.size();i++) {
			Colony colony = map.colonies.get(i);
			final int antSpawnBoxSize = ps.antSpawnBoxSize.i();
			int distX = map.w/(map.colonies.size()+1)*(i+1);
			for(int x=distX-map.w/antSpawnBoxSize;x<distX+map.w/antSpawnBoxSize;x++) {
				for(int y=map.h/2-map.h/antSpawnBoxSize;y<map.h/2+map.h/antSpawnBoxSize;y++) {
					if(map.get(x, y).getType() != Types.PERMANENT)
						colony.addAnt(x,y);
				}
			}
		}
		
	}
}
