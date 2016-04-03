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
	public Colony(ParamSetManager inpSM, AntsMap inmap, Color incolor) {
		pSM = inpSM;
		params = new ParamSetColony();
		pSM.addParamSet(params);
		map = inmap;
		color = incolor;
		ants = new Vector<Ant>();
	}
	public void addAnt(int x, int y) {
		for(int i=0;i<ants.size();i++) {
			Ant a = ants.get(i);
			if(a.x == x && a.y == y) {
				return;
			}
		}
		ants.add(new Ant(x, y, this));
		map.set(x,y,Types.ANT);
	}
	public void moveAnts() {
		Random rand = new Random();
		for(Ant a: ants) {
			if(rand.nextFloat() < a.inv.slownessTotal) {
				a.job.manager.move();
			}
		}
	}
	public void kill() {
		pSM.getParamSets().remove(params);
	}
}
