package ants;

import java.util.Vector;

public class Colony {
	GlobalParamSet globalParams;
	ColonyParamSet params;
	Vector<Ant> ants;
	AntsMap map;
	public Colony(GlobalParamSet inParams, AntsMap inmap) {
		globalParams = inParams;
		params = new ColonyParamSet();
		map = inmap;
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
		for(Ant a: ants) {
			a.move();
		}
	}
}
