package ants.ai;

import java.util.Random;

import ants.Ant;
import ants.Types;
import ants.Util;

public class MoverRandExploreDirt extends MovementManager {
	
	public MoverRandExploreDirt(Ant inant) {
		super(inant);
	}
	
	public void move() {
		Random rand = new Random();
		int dirIndex = rand.nextInt(8)*2;
		int dirx = Util.dirs8[dirIndex];
		int diry = Util.dirs8[dirIndex+1];
		for(int i=0; i<8; i++) {
			int dirIndex2 = rand.nextInt(8)*2;
			int dirx2 = Util.dirs8[dirIndex2];
			int diry2 = Util.dirs8[dirIndex2+1];
			if(ant.inv.slownessTotal == 1 && map.get(ant.x+dirx2, ant.y+diry2) == Types.FOOD) {
				dirx = dirx2;
				diry = diry2;
			} else if(map.get(ant.x+dirx2, ant.y+diry2) == Types.DIRT) {
				if(colony.params.dirtExplorationPreference.value > rand.nextDouble() ) {
					dirx = dirx2;
					diry = diry2;
				}
			}
		}
		int nx = ant.x+dirx;
		int ny = ant.y+diry;
		tryMove(nx,ny);
		map.erode(nx, ny, ant);
	}
	
}
