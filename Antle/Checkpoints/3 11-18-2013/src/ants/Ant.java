package ants;

import java.util.Random;

public class Ant {
	static Random rand = new Random();
	Colony colony;
	public int x, y;
	public Ant(int inx, int iny, Colony inmind) {
		colony = inmind;
		x = inx;
		y = iny;
	}
	public void move() {
		int dirIndex = rand.nextInt(8)*2;
		int dirx = Util.dirs8[dirIndex];
		int diry = Util.dirs8[dirIndex+1];
		for(int i=0; i<8; i++) {
			int dirIndex2 = rand.nextInt(8)*2;
			int dirx2 = Util.dirs8[dirIndex2];
			int diry2 = Util.dirs8[dirIndex2+1];
			if(colony.map.getHeat(x+dirx, y+diry) > colony.map.getHeat(x+dirx2, y+diry2)) {
				if(colony.params.explorationPreference.value > rand.nextDouble() ) {
					dirx = dirx2;
					diry = diry2;
				}
			}
		}
		int nx = x+dirx;
		int ny =  y+diry;
		int targetType = colony.map.get(nx, ny);
		if (targetType == Types.EMPTY) {
			colony.map.set(x+dirx,y+diry,Types.ANT);
			colony.map.set(x,y,Types.EMPTY);
			x = nx;
			y = ny;
		}
//		colony.map.erode(x, y);
		colony.map.erode(nx, ny);
	}
	
}
