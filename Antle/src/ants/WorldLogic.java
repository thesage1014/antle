package ants;

import java.util.Random;

public final class WorldLogic {
	boolean backwardTick = false;
	public WorldLogic() {
	}
	public void antTick(AntleMap map, ParamSetManager inpSM) {
		Random rand = new Random();
		int antCount = 0;
		ParamSetGlobal ps = (ParamSetGlobal)inpSM.getParamSet(0);
		if(backwardTick) {
			for(int i=map.colonies.size()-1;i>=0;i--) {
				map.colonies.get(i).tickAnts();
			}
			for(int x=map.w-1;x>=0;x--) {
				for(int y=map.h-1;y>=0;y--) {
					antTickOperations(map,x,y,rand,antCount,inpSM);
				}
			}
		} else {
			for(int i=0;i<map.colonies.size();i++) {
				map.colonies.get(i).tickAnts();
			}
			for(int x=0;x<map.w;x++) {
				for(int y=0;y<map.h;y++) {
					antTickOperations(map,x,y,rand,antCount,inpSM);
				}
			}
		}
		backwardTick = !backwardTick;
	}
	void antTickOperations(AntleMap map, int x, int y, Random rand, int antCount, ParamSetManager inpSH) {
		Tile tile = map.get(x, y);
		antCount = 0;
		if(tile.getType() == TileTypes.ANT && map.get(x, y).getEntity() instanceof Ant) {
			antCount++;
			map.layScent((Ant) map.get(x, y).getEntity());
		} else if(tile.getType() == TileTypes.FOOD) {
			map.layScent(x,y,map.scents.get(Scent.FOODindex),TileTypes.FOOD.scentValue);
		}
	}
}
