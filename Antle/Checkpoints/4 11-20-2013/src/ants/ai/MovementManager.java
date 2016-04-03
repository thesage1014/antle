package ants.ai;

import java.util.Random;

import ants.*;

public class MovementManager {
	Ant ant;
	Colony colony;
	AntsMap map;
	Random rand;
	public MovementManager(Ant inant) {
		ant = inant;
		colony = inant.colony;
		map = colony.map;
		rand = new Random();
	}
	
	public void move() {
		
	}
	public boolean canMove(int x, int y) {
//		return (ant.x == x && ant.y == y);
		return false;
	}
	LocalScanResults scanAdjacentTiles() { // Remove eventually if unused
		LocalScanResults scan = new LocalScanResults();
		boolean[] passable = new boolean[8];
		int passableSpaces = 0;
		for (int i=0; i<8; i++) {
			int tileType = map.get(Util.dirs8[i*2]+ant.x, Util.dirs8[i*2+1]+ant.y);
			if (tileType == Types.EMPTY) {
				passable[i] = true;
				passableSpaces++;
			} else if (tileType == Types.FOOD) {
				scan.foodCount++;
			} else if (tileType == Types.DIRT) {
				scan.dirtCount++;
			} else if (tileType == Types.ANT) {
				scan.antCount++;
			}
			if (Types.get(tileType).isBreakable) {
				scan.breakableCount++;
			}
		}
		scan.passableCount = passableSpaces;
		int[] isPassable = new int[passableSpaces];
		if(passableSpaces != 0) {
			int curIsIndex = 0;
			for(int i=0; i<8; i++) {
				if(passable[i]) {
					isPassable[curIsIndex] = i*2;
					curIsIndex++;
				}
			}
		}
		scan.passables = isPassable;
		return scan;
	}
	
	int[] scanForType(int inType) { // Returns an array of indexes that correspond to x values in Util.dirs8. Y values are index+1
		boolean[] passable = new boolean[8];
		int passableSpaces = 0;
		for (int i=0; i<8; i++) {
			if (map.get(Util.dirs8[i*2]+ant.x, Util.dirs8[i*2+1]+ant.y) == inType) {
				passable[i] = true;
				passableSpaces++;
			}
		}
		int[] isPassable = new int[passableSpaces];
		if(passableSpaces != 0) {
			int curIsIndex = 0;
			for(int i=0; i<8; i++) {
				if(passable[i]) {
					isPassable[curIsIndex] = i*2;
					curIsIndex++;
				}
			}
		}
		return isPassable;
	}
	boolean tryMove(int dx, int dy) { // Returns weather or not the move was successful
		if (map.get(dx,dy) == Types.EMPTY) {
			map.set(dx,dy,Types.ANT);
			map.set(ant.x,ant.y,Types.EMPTY);
			ant.x = dx;
			ant.y = dy;
			return true;
		} else {
			return false;
		}
	}
}
