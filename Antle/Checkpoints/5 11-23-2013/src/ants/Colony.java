package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;


public class Colony {
	ParamSetManager pSM;
	public ParamSetColony params;
	Vector<Ant> ants;
	public AntsMap map;
	public Color color;
	public int scentIndex;
	public Colony(ParamSetManager inpSM, AntsMap inmap, Color incolor, int inscentIndex) {
		pSM = inpSM;
		params = new ParamSetColony();
		pSM.addParamSet(params);
		map = inmap;
		color = incolor;
		scentIndex = inscentIndex;
		ants = new Vector<Ant>();
	}
	public void addAnt(int x, int y) {
		Tile tile = map.get(x, y);
		if(tile.getType() != Types.ANT) {
			Ant newAnt = new Ant(tile,this);
			ants.add(newAnt);
		}
	}
	public void moveAnts() {
		Random rand = new Random();
		for(Ant a: ants) {
			if(rand.nextFloat() < a.inv.slownessTotal) {
				a.jobManager.job.moveManager.move();
			}
		}
	}
	public void kill() {
		pSM.getParamSets().remove(params);
	}
}
