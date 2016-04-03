package ants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public final class AntsMap {
	public int[] map,oldMap,erosionMap;
	Vector<Colony> colonies;
	ParamSetManager pSM;
	final int w, h;
	MapGenerator mapGen;
	Random rand = new Random();
	public AntsMap(int inw, int inh, ParamSetManager inpSM) {
		pSM = inpSM;
		w = inw;
		h = inh;
		map = new int[w*h];
		erosionMap = new int[w*h];
		
		colonies = new Vector<Colony>();
		mapGen = new MapGenerator();
		mapGen.generateEarlyTestMap(this, pSM);
		
		applyNextMap();
	}
	
	public int get(int x, int y) {
		return map[x+w*y];
	}
	public Type getType(int x, int y) {
		return Types.get(map[x+w*y]);		// TODO CHECK FOR THINGS THAT COULD BE USING THIS
	}
	public int getHeat(int x, int y) {
		return erosionMap[x+w*y];
	}
	public void set(int x, int y, int type) {
		map[x+w*y] = type;
	}
	public void setHeat(int x, int y, int type) {
		erosionMap[x+w*y] = type;
	}
	public int getOld(int x, int y) {
		return oldMap[x+w*y];
	}
	
	public BufferedImage getImage() {
//		byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
//		byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
//		byte[] cb = new byte[]{(byte) 44,	22,		14,		   44};
//		IndexColorModel model = new IndexColorModel(8,cr.length,cr,cg,cb);
//		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED,model);
//		image.getRaster().setPixels(0, 0, w, h, map);
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		for(int x=0; x<w; x++) {
			for(int y=0; y<h; y++) {
				Type tileType = Types.types[map[x + y*w]];
				Color tile = tileType.color;
//				System.out.print(tileType.name + " " + heatMap[x+y*w]);
//				System.out.println(tile.r + " " + tile.g + " " + tile.b);
				if(tileType.isBreakable) {
					float b = (float)(tileType.maxHealth-erosionMap[x+y*w])/tileType.maxHealth;
//					if(b != 1)
//						System.out.print(" " + b + "\n");
					g.setColor(new Color(
							(int)(((float)tile.getRed()*b)+tile.getRed())/2,
							(int)(((float)tile.getGreen()*b)+tile.getGreen())/2,
							(int)((float)tile.getBlue()*b)));
				}
//				else if(tileType.isEntity) {  //TODO AFTER TILE DATA
////					g.setColor()
//				} 
				else {
				
					g.setColor(new Color(tile.getRed(),tile.getGreen(),tile.getBlue()));
				}
				g.fillRect(x, y, 1, 1);
			}
		}
		for(int i=0;i<colonies.size();i++) {
			for(int a=0;a<colonies.get(i).ants.size();a++) {
				Ant ant = colonies.get(i).ants.get(a);
				g.setColor(ant.getColor());
				g.fillRect(ant.x, ant.y, 1, 1);
			}
		}
		return image;
	}
	public BufferedImage getHeatImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		image.getRaster().setPixels(0, 0, w, h, erosionMap);
		return image;
	}
	
	public void applyNextMap() {
		oldMap = map.clone();
	}

	public boolean erode(int x, int y, Entity eroder) { //Returns if a block was broken
		Type blockType = Types.get(get(x,y));
		if(Types.types[get(x,y)].isBreakable) {
			int erodeAmount = rand.nextInt(pSM.global().erosionPerStep.i())+1;
			if(getHeat(x, y) + erodeAmount >= blockType.maxHealth) {
				if(blockType.givesItem) {
					if(eroder.pickupItem(blockType.itemOnBreak)) {
						erosionMap[x+y*w] = Types.EMPTY;
						if(x>1 && x<w-2 && y>1 && y< h-2) {
							set(x,y,Types.EMPTY);
						}
					}
				} else {
					erosionMap[x+y*w] = Types.EMPTY;
					if(x>1 && x<w-2 && y>1 && y< h-2) {
						set(x,y,Types.EMPTY);
					}
				}
			} else {
				erosionMap[x+y*w] += erodeAmount;
			}
		}
		return false;
	}

	public void kill() {
		for(int i=0; i<colonies.size(); i++) {
			colonies.get(i).kill();
		}
	}
	
}
