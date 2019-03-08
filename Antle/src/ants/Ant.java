package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import ants.ai.*;
import ants.ml.AntStateData;

public class Ant extends Entity {
	public JobManager jobManager;
	Vector<AntListener> listeners;
	static Random rand = new Random();
	public Colony colony;
	public Scent scent;
	public float scentValue = 2;
	
	public Ant(Tile intile, Colony incolony) {
		super(intile, incolony.map, TileTypes.ANT);
		colony = incolony;
		scent = colony.scent;
		listeners = new Vector<AntListener>();
		jobManager = new JobManager(this);
		
	}
	public void addAntListener(AntListener inlistener) {
		listeners.add(inlistener);
	}
	
	@Override
	public boolean pickupItem(Item item) {
		if (inv.addItem(item)) {
			raiseEvent(new EventAntRecievedItem(this,this,item));
			return true;
		}
		return false;
	}
	@Override
	public int[] getColor() {
		if(inv.items.size() != 0) {
			Item firstItem = inv.items.get(0);
			int r=(colony.color[0]+firstItem.color.getRed()*2)/3;
			int g=(colony.color[1]+firstItem.color.getGreen()*2)/3;
			int b=(colony.color[2]+firstItem.color.getBlue()*2)/3;
			return new int[]{r,g,b};
		} else {
			return colony.color;
		}
	}
	@Override
	public boolean moveEntity() {
		return jobManager.moveAnt();
	}
	public void raiseEvent(EventAntCreated e) {
		for(AntListener l : listeners) {
			l.antCreated(e);
		}
	}
	public void raiseEvent(EventAntDestroyed e) {
		for(AntListener l : listeners) {
			l.antDestroyed(e);
		}
	}
	public void raiseEvent(EventAntJobChanged e) {
		for(AntListener l : listeners) {
			l.antChangedJobs(e);
		}
	}
	public void raiseEvent(EventAntMoved e) {
		for(AntListener l : listeners) {
			l.antMoved(e);
		}
	}
	public void raiseEvent(EventAntRecievedItem e) {
		for(AntListener l : listeners) {
			l.antRecievedItem(e);
		}
	}
	public void kill() {
		listeners.clear();
	}
	
}
