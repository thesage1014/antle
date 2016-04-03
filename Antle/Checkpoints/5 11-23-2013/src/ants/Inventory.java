package ants;

import java.util.Vector;


public class Inventory {
	public int maxSize = 1;
	public float slownessTotal = 1f;
	public Vector<Item> items = new Vector<Item>(0);
	public boolean addItem(Item item) {
		if(items.size() < maxSize) {
			items.add(item);
			slownessTotal *= item.slowdownAmount;
			return true;
		} else {
			return false;
		}
	}
	public boolean removeItem(Item item) {
		if(items.remove(item)) {
			if(item.slowdownAmount == 0) {
				reinitializeSlowness();
			} else {
				slownessTotal /= item.slowdownAmount;
			}
			return true; 
		} else {
			return false;
		}
	}
	private void reinitializeSlowness() {
		slownessTotal = 0;
		for(int i=0; i<items.size(); i++) {
			slownessTotal *= items.get(i).slowdownAmount;
		}
	}
}
