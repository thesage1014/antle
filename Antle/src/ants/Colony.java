package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import ants.ai.JobCollectFood;


public class Colony {
	ParamSetManager pSM;
	public ParamSetColony params;
	Vector<Ant> ants;
	public AntsMap map;
	public int[] color;
	public String name;
	public Scent scent;
	public int x,y;
	public Colony(ParamSetManager inpSM, int inx, int iny, String inname, AntsMap inmap, int[] incolor, int inscentIndex) {
		pSM = inpSM;
		name = inname;
		params = new ParamSetColony(this);
		pSM.addParamSet(params);
		map = inmap;
		color = incolor;
		scent = new Scent(name,incolor,inscentIndex,map.w,map.h);
		ants = new Vector<Ant>();
	}
	public boolean addAnt(int x, int y) {
		if(map.InBounds(x,y)) {
			Tile tile = map.get(x, y);
			if(tile.getType() != TileTypes.ANT) {
				Ant newAnt = new Ant(tile,this);
				ants.add(newAnt);
			}
			return true;
		} else {
			return false;
		}
			
	}
	public Ant spawnMLAnt() {
		int x = map.w/2;
		int y = map.h/2;
		System.out.println(x + " " + y);
		if(map.InBounds(x,y)) {
			Tile tile = map.get(x, y);
			if(tile.getType() != TileTypes.ANT) {

			}
			Ant newAnt = new Ant(tile,this);
			ants.add(newAnt);
			return newAnt;
		}
		else {
			System.err.println("ant already there");
			return null;
		}
	}
	public void tickAnts() {
		if(params.allFindFood.buttonPressed()) {
			for(Ant a: ants) {
				a.jobManager.setJob(this, new JobCollectFood(a));
			}
		}
		Random rand = new Random();
		for(Ant a: ants) {
			if(rand.nextFloat() < a.inv.slownessTotal) {
				a.jobManager.moveAnt();
			}
		}
	}
	public void kill() {
		pSM.getParamSets().remove(params);
	}
}
