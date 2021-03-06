package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import ants.ai.JobBeStinky;

public class MapGenerator {
	public MapGenerator() {
		
	}
	public void generateEarlyTestMap(AntleMap map, ParamSetManager inpSM) {
		Random rand = new Random();
		map.colonies = new Vector<Colony>();
		ParamSetGlobal ps = inpSM.getGlobal();
		double noiseZ = rand.nextDouble()*1023;
		
		for(int x=0;x<map.w;x++) {
			for(int y=0;y<map.h;y++) {
				double curNoise = KenPerlin.noise((ps.earthNoiseScale.value*x),(ps.earthNoiseScale.value*y),noiseZ);
				double rockNoise = KenPerlin.noise((ps.earthNoiseScale.value*x),(ps.earthNoiseScale.value*y),noiseZ-0.3);
				if(rockNoise > ps.stoneLayerCutoff.value) {
					map.get(x, y).setTo(TileTypes.STONE);
				} else if(curNoise > ps.earthDensity.value) {
					map.get(x, y).setTo(TileTypes.DIRT);
					Color dirtColor = new Color((int)(-curNoise*25+51),(int)(-curNoise*15)+41,(int)(-curNoise*5+15));
					map.get(x, y).color = dirtColor;
				} else {
					double curNoise2 = KenPerlin.noise((ps.earthNoiseScale.value*x),(ps.earthNoiseScale.value*y),noiseZ + 10);
					if(curNoise2 >= ps.foodSpawnAmount.value) {
						map.get(x, y).setTo(TileTypes.FOOD);
					}
				}
			}
		}
		
		// Fence off the map
		for(int x=0; x<map.w; x++) {
			map.get(x,0).setTo(TileTypes.PERMANENT);
			map.get(x,1).setTo(TileTypes.PERMANENT);
			map.get(x,map.h-1).setTo(TileTypes.PERMANENT);
			map.get(x,map.h-2).setTo(TileTypes.PERMANENT);
			map.get(x,map.h-3).setTo(TileTypes.PERMANENT);
		}
		for(int y=0; y<map.h; y++) {
			map.get(0,y).setTo(TileTypes.PERMANENT);
			map.get(1,y).setTo(TileTypes.PERMANENT);
			map.get(map.w-2,y).setTo(TileTypes.PERMANENT);
			map.get(map.w-1,y).setTo(TileTypes.PERMANENT);
		}
		for(int i=0;i<inpSM.getGlobal().numColonies.i();i++) {
			int antSpawnBoxSize = ps.antSpawnBoxSize.i();
			int distX = map.w/(inpSM.getGlobal().numColonies.i()+1)*(i+1);
			map.addColony(inpSM, distX, map.h, Names.getName(), new int[] {rand.nextInt(255),rand.nextInt(50),rand.nextInt(255)}); // TODO Ant indexes need to be applied properly in the map
			Colony colony = map.colonies.get(i);
			if(!ps.useMachineLearning.bool()) {
				for(int x=distX-antSpawnBoxSize/2;x<distX+antSpawnBoxSize/2;x++) {
					for(int y=colony.y+4-antSpawnBoxSize/2;y<colony.y+4+antSpawnBoxSize/2;y++) {
						if(map.InBounds(x, y) && map.get(x, y).getType() != TileTypes.PERMANENT)
							colony.addAnt(x,y);
					}
				}
			}
//			Ant stinkyAnt = colony.ants.get(0);
//			stinkyAnt.jobManager.setJob(colony, new JobBeStinky(stinkyAnt));
		}
		
	}
}
