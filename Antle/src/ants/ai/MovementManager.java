package ants.ai;

import java.util.Random;
import java.util.Vector;

import ants.*;

public abstract class MovementManager {
	Ant ant;
	Colony colony;
	AntsMap map;
	Random rand;
	float[][] DEBUGscentMap = new float[5][5];

	public MovementManager(Ant inant) {
		ant = inant;
		colony = inant.colony;
		map = colony.map;
		rand = new Random();
	}

	public final boolean move() {
		Tile tile = ant.tile;
		boolean moved = beMoved();
		if (moved) {
			ant.raiseEvent(new EventAntMoved(this, ant, tile));
		}
		return moved;
	}
	/**
	 * Move Tick
	 * @return returns weather or not move was successful
	 */
	abstract boolean beMoved();
//	public boolean canMove(int x, int y) {
//		return false;
//	}
//	LocalScanResults scanAdjacentTiles() { // Remove eventually if unused
//		LocalScanResults scan = new LocalScanResults();
//		boolean[] passable = new boolean[8];
//		int passableSpaces = 0;
//		for (int i=0; i<8; i++) {
//			Type tileType = map.get(Util.dirs8[i*2]+ant.tile.x, Util.dirs8[i*2+1]+ant.tile.y).getType();
//			if (tileType == Types.EMPTY) {
//				passable[i] = true;
//				passableSpaces++;
//			} else if (tileType == Types.FOOD) {
//				scan.foodCount++;
//			} else if (tileType == Types.DIRT) {
//				scan.dirtCount++;
//			} else if (tileType == Types.ANT) {
//				scan.antCount++;
//			}
//			if (tileType.isBreakable) {
//				scan.breakableCount++;
//			}
//		}
//		scan.passableCount = passableSpaces;
//		int[] isPassable = new int[passableSpaces];
//		if(passableSpaces != 0) {
//			int curIsIndex = 0;
//			for(int i=0; i<8; i++) {
//				if(passable[i]) {
//					isPassable[curIsIndex] = i*2;
//					curIsIndex++;
//				}
//			}
//		}
//		scan.passables = isPassable;
//		return scan;
//	}

	Float3D[][] scanForScent() {
		int antx = ant.tile.x;
		int anty = ant.tile.y;
		Float3D[][] scan = new Float3D[6][5]; // Highest and lowest tiles are stored in the very right column

		Vector<Float3D> highests = new Vector<Float3D>();
		Vector<Float3D> lowests = new Vector<Float3D>();
		Float3D highest = new Float3D(-1, -1, -1);
		Float3D lowest = new Float3D(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
		// This stores scan[0][0] ... scan[4][4]
		int scanWidth = 2;
		for (int x = antx - scanWidth; x <= antx + scanWidth; x++) {
			for (int y = anty - scanWidth; y <= anty + scanWidth; y++) {
				float value = ant.colony.scent.values[x][y];
				scan[x - antx + scanWidth][y - anty + scanWidth] = new Float3D(x, y, value);
				if (value > highest.z) {
					highests.clear();
					highest.x = x;
					highest.y = y;
					highest.z = value;
					highests.add(highest);
				} else if (value == highest.z) {
					highests.add(new Float3D(x, y, value));
				}
				if (value < lowest.z) {
					lowests.clear();
					lowest.x = x;
					lowest.y = y;
					lowest.z = value;
					lowests.add(lowest);
				} else if (value == lowest.z) {
					lowests.add(new Float3D(x, y, value));
				}

			}
		}
		// These are scan[5][0](highest) ... scan[5][1](lowest)
		Random rand = new Random();
		if (highest.x == -1) {
			int sx = rand.nextInt(3) + antx - 1;
			int sy = rand.nextInt(3) + anty - 1;
			scan[5][0] = new Float3D(sx, sy, ant.colony.scent.values[sx][sy]);
		} else {
			scan[5][0] = highests.get(rand.nextInt(highests.size()));
		}
		if (lowest.x == Float.MAX_VALUE) {
			int sx = rand.nextInt(3) + antx - 1;
			int sy = rand.nextInt(3) + anty - 1;
			scan[5][1] = new Float3D(sx, sy, ant.colony.scent.values[sx][sy]);
		} else {
			scan[5][1] = lowests.get(rand.nextInt(lowests.size()));
		}
		return scan;
	}

	Tile[] scanForType(int inType) {
		Vector<Tile> tiles = new Vector<Tile>();
		Tile scannedTile;
		for (int x = ant.tile.x - 1; x < ant.tile.x + 1; x++) {
			for (int y = ant.tile.y - 1; y < ant.tile.y + 1; y++) {
				scannedTile = map.get(x, y);
				if (scannedTile.getType().ID == inType) {
					tiles.add(scannedTile);
				}
			}
		}
		Tile[] result = new Tile[tiles.size()];
		tiles.toArray(result);
		return result;
	}

	int[] oldScanForType(int inType) { // Returns an array of indexes that correspond to x values in Util.dirs8. Y
										// values are index+1
		boolean[] passable = new boolean[8];
		int passableSpaces = 0;
		for (int i = 0; i < 8; i++) {
			if (map.get(Util.dirs8[i * 2] + ant.tile.x, Util.dirs8[i * 2 + 1] + ant.tile.y).getType().ID == inType) {
				passable[i] = true;
				passableSpaces++;
			}
		}
		int[] isPassable = new int[passableSpaces];
		if (passableSpaces != 0) {
			int curIsIndex = 0;
			for (int i = 0; i < 8; i++) {
				if (passable[i]) {
					isPassable[curIsIndex] = i * 2;
					curIsIndex++;
				}
			}
		}
		return isPassable;
	}

	boolean moveTowards(int dx, int dy) {
//		double ang = Math.atan2(ant.tile.x-dx, ant.tile.y-dy);
//		int x = (int) Math.min(Math.max(Math.round(Math.cos(ang))*1.5,1),-1)+ant.tile.x;
//		int y = (int) Math.min(Math.max(Math.round(Math.sin(ang))*1.5,1),-1)+ant.tile.y;
		int x = (Math.min(Math.max(dx - ant.tile.x, -1), 1)) + ant.tile.x;
		int y = (Math.min(Math.max(dy - ant.tile.y, -1), 1)) + ant.tile.y;
		return tryMove(x, y);
	}

	boolean tryMove(int dx, int dy) { // Returns weather or not the move was successful
		int x = ant.tile.x;
		int y = ant.tile.y;
//		System.out.println(Math.abs(x-dx) + " " + dx + " " + x + " " + Math.abs(y-dy) + " " + dy + " " + y);
		if (Math.abs(x - dx) <= 1 && Math.abs(y - dy) <= 1) {
			if (map.get(dx, dy).getType() == TileTypes.EMPTY) {
				if (map.get(dx, dy).setToEntity(ant)) {
					map.get(x, y).clear();
					return true;
				}
			}
			map.attackBlock(dx, dy, ant);
		} else {
			System.out.println(x + " " + (x - dx) + " | " + y + " " + (y - dy));
		}
		return false;
	}
}
