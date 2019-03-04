package ants.ai;

import java.util.Random;

import ants.Ant;
import ants.TileTypes;
import ants.Util;

public class MoverRandExploreDirt extends MovementManager {
	
	public MoverRandExploreDirt(Ant inant) {
		super(inant);
	}
	
	boolean beMoved() {
		Random rand = new Random();
		int dirIndex = rand.nextInt(8)*2;
		int dirx = Util.dirs8[dirIndex];
		int diry = Util.dirs8[dirIndex+1];
		for(int i=0; i<8; i++) {
			int dirIndex2 = rand.nextInt(8)*2;
			int dirx2 = Util.dirs8[dirIndex2];
			int diry2 = Util.dirs8[dirIndex2+1];
			if(ant.inv.slownessTotal == 1 && map.get(ant.tile.x+dirx2, ant.tile.y+diry2).getType() == TileTypes.FOOD) {
				dirx = dirx2;
				diry = diry2;
			} else if(map.get(ant.tile.x+dirx2, ant.tile.y+diry2).getType() == TileTypes.DIRT) {
				if(colony.colonyParams.dirtExplorationPreference.value > rand.nextDouble() ) {
					dirx = dirx2;
					diry = diry2;
				}
			}
		}
		int nx = ant.tile.x+dirx;
		int ny = ant.tile.y+diry;
		map.attackBlock(nx, ny, ant);
		return tryMove(nx,ny);
	}
	
}
