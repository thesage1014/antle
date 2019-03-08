package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import ants.ai.JobCollectFood;
import ants.ml.JobML;

public class Colony {
	ParamSetManager pSM;
	public ParamSetColony colonyParams;
	Vector<Ant> ants;
	public AntleMap map;
	public int[] color;
	public String name;
	public Scent scent;
	public int x, y;

	public Colony(ParamSetManager inpSM, int inx, int iny, String inname, AntleMap inmap, int[] incolor,
			int inscentIndex) {
		pSM = inpSM;
		name = inname;
		colonyParams = new ParamSetColony(this);
		pSM.addParamSet(colonyParams);
		map = inmap;
		color = incolor;
		scent = new Scent(name, incolor, inscentIndex, map.w, map.h);
		ants = new Vector<Ant>();
	}

	public boolean addAnt(int x, int y) {
		if (map.InBounds(x, y)) {
			Tile tile = map.get(x, y);
			if (tile.getType() != TileTypes.ANT) {
				Ant newAnt = new Ant(tile, this);
				ants.add(newAnt);
			}
			return true;
		} else {
			return false;
		}

	}

	public AntML spawnMLAnt() {
		int x = map.w / 2;
		int y = map.h / 2;
		if (map.InBounds(x, y)) {
			Tile tile = map.get(x, y);
			AntML newAnt = new AntML(tile, this);
			newAnt.jobManager.setJob(this, new JobML(newAnt));
			if (tile.getType() != TileTypes.ANT) {
				tile.setToEntity(newAnt);
			}
			ants.add(newAnt);
			return newAnt;
		} else {
			System.err.println("ant already there");
			return null;
		}
	}

	public void tickAnts() {
		if (colonyParams.allFindFood.buttonPressed()) {
			for (Ant a : ants) {
				a.jobManager.setJob(this, new JobCollectFood(a));
			}
		}
		Random rand = new Random();
		for (Ant a : ants) {
			if (rand.nextFloat() < a.inv.slownessTotal) {
				a.jobManager.moveAnt();
			}
		}
	}

	public void kill() {
		for(Ant a : ants) {
			a.kill();
		}
		ants.clear();
		pSM.getParamSets().remove(colonyParams);
	}
}
