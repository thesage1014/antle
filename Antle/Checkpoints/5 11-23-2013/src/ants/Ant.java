package ants;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import ants.ai.*;

public class Ant extends Entity {
	public JobManager jobManager;
	Vector<AntListener> listeners;
	static Random rand = new Random();
	public Colony colony;
	public Ant(Tile intile, Colony incolony) {
		super(intile, incolony.map, Types.ANT);
		colony = incolony;
		listeners = new Vector<AntListener>();
		jobManager = new JobManager(new JobOldExplore(this, jobManager));
//		jobManager = new JobManager(new JobCollectFood(this, jobManager));
		
	}
	public void addAntListener(AntListener inlistener) {
		listeners.add(inlistener);
	}
	@Override
	public boolean pickupItem(Item item) {
		return inv.addItem(item);
		
	}
	@Override
	public Color getColor() {
		if(inv.items.size() != 0) {
			Item firstItem = inv.items.get(0);
			int r=(colony.color.getRed()+firstItem.color.getRed()*2)/3;
			int g=(colony.color.getGreen()+firstItem.color.getGreen()*2)/3;
			int b=(colony.color.getBlue()+firstItem.color.getBlue()*2)/3;
			return new Color(r,g,b);
		} else {
			return colony.color;
		}
		
	}
	public void raiseEvent(EventAntMoved e) {
		for(AntListener l : listeners) {
			l.antMoved(e);
		}
	}
}
