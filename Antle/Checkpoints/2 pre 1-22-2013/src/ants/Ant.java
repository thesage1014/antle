package ants;

import java.util.Random;

public class Ant {
	static Random rand = new Random();
	public static void move(int x, int y, int dx, int dy, AntsMap map) {
		int targetType = map.get(x+dx,y+dy);
		
		if (targetType == Type.empty) {
			map.set(x+dx,y+dy,Type.ant);
			map.set(x,y,Type.empty);
		}
		if (targetType == Type.food) {
			reportEating(map);
			map.set(x+dx,y+dy,Type.ant);
			map.set(x,y,Type.empty);
		}
	}
	public static void moveTowardUnvisited(int x, int y, AntsMap map) {
		int dirx=0, diry=0;
		for(int i=0; i<2; i++) {
			int dirIndex = rand.nextInt(8)*2;
			dirx = Util.dirs8[dirIndex];
			diry = Util.dirs8[dirIndex]+1;
			dirIndex = rand.nextInt(8)*2;
			int dirx2 = Util.dirs8[dirIndex];
			int diry2 = Util.dirs8[dirIndex]+1;
			if(map.get(dirx, diry) > map.get(dirx2, diry2)) {
				
			}
		}
		int targetType = map.get(dirx, diry);
		if (targetType == Type.empty) {
			map.set(x+dirx,y+diry,Type.ant);
			map.set(x,y,Type.empty);
		}
		if (targetType == Type.food) {
			reportEating(map);
			map.set(x+dirx,y+diry,Type.ant);
			map.set(x,y,Type.empty);
		}
	}
	private static void reportEating(AntsMap map) {
		map.foodLeft--;
//		System.out.println(map.foodLeft + " blocks of food left.");
	}
	
}
