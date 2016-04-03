package ants;

import java.awt.Color;
import java.util.Random;

import ants.ai.*;
import ants.nouns.Item;

public class Ant extends Entity {
	public Job job;
	static Random rand = new Random();
	public Colony colony;
	public Inventory inv;
	public int x, y;
	public Ant(int inx, int iny, Colony inmind) {
		blockID = 1;
		colony = inmind;
		x = inx;
		y = iny;
		inv = new Inventory();
//		job = new JobFindFood(this);
		job = new JobExplore(this);
	}
	public void move() {
		
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
}
