package ants.ai;

import java.util.Random;
import java.util.Vector;

import ants.*;

public class MoverReturnHome extends MovementManager {

	public MoverReturnHome(Ant inant) {
		super(inant);
	}
	@Override
	protected boolean managedMove() {
		// Code modified from MovementManager
		int antx = ant.tile.x;
		int anty = ant.tile.y;
		Float3D[][] scan = new Float3D[6][5]; // Highest tiles are stored in the very right column
		
		Vector<Float3D> highests = new Vector<Float3D>();
		Float3D highest = new Float3D(-1,-1,-1);
		//This stores scan[0][0] ... scan[4][4]
		int scanWidth = 2;
		for(int x=antx-scanWidth;x<=antx+scanWidth;x++) {
			for(int y=anty-scanWidth;y<=anty+scanWidth;y++) {
				if(x == antx-scanWidth || y == anty-scanWidth || x == antx+scanWidth || y == anty+scanWidth) {
					float value = ant.colony.scent.values[x][y];
					scan[x-antx+scanWidth][y-anty+scanWidth] = new Float3D(x,y,value);
					if(value > highest.z) {
						highests.clear();
						highest.x = x;
						highest.y = y;
						highest.z = value;
						highests.add(highest);
					} else if(value == highest.z) {
						highests.add(new Float3D(x,y,value));
					}
				}
			}
		}
		//These are scan[5][0](highest)
		Random rand = new Random();
		if(highest.x == -1) {
			int sx = rand.nextInt(3) + antx -1;
			int sy = rand.nextInt(3) + anty -1;
			scan[5][0] = new Float3D(sx,sy,ant.colony.scent.values[sx][sy]);
		} else {
			scan[5][0] = highests.get(rand.nextInt(highests.size()));
		}
		
		return moveTowards((int)scan[5][0].x, (int)scan[5][0].y);
	}
	
}
