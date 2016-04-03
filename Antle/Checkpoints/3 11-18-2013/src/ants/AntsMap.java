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
	public int[] map,oldMap,heatMap;
	Vector<Colony> colonies;
	GlobalParamSet ps;
	final int w, h;
	MapGenerator mapGen;
	Random rand = new Random();
	public AntsMap(int inw, int inh, GlobalParamSet inParams) {
		w = inw;
		h = inh;
		ps = inParams;
		map = new int[w*h];
		heatMap = new int[w*h];
		
		colonies = new Vector<Colony>();
		mapGen = new MapGenerator();
		mapGen.generateEarlyTestMap(this, ps);
		
		applyNextMap();
	}
	
	public int get(int x, int y) {
		return map[x+w*y];
	}
	public int getHeat(int x, int y) {
		return heatMap[x+w*y];
	}
	public void set(int x, int y, int type) {
		map[x+w*y] = type;
	}
	public void setHeat(int x, int y, int type) {
		heatMap[x+w*y] = type;
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
				TileColor tile = tileType.color;
//				System.out.print(tileType.name + " " + heatMap[x+y*w]);
//				System.out.println(tile.r + " " + tile.g + " " + tile.b);
				if(tileType.isBreakable) {
					float b = (float)(tileType.maxHealth-heatMap[x+y*w])/tileType.maxHealth;
//					if(b != 1)
//						System.out.print(" " + b + "\n");
					g.setColor(new Color(
							(int)(((float)tile.r*b)+tile.r)/2,
							(int)(((float)tile.g*b)+tile.g)/2,
							(int)((float)tile.b*b)));
				} else {
					g.setColor(new Color(tile.r,tile.g,tile.b));
				}
				g.fillRect(x, y, 1, 1);
			}
		}
		return image;
	}
	public BufferedImage getHeatImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		image.getRaster().setPixels(0, 0, w, h, heatMap);
		return image;
	}
	
	public void applyNextMap() {
		oldMap = map.clone();
	}

	public void erode(int x, int y) {
		int blockType = get(x,y);
		if(Types.types[get(x,y)].isBreakable) {
			int erodeAmount = rand.nextInt(ps.erosionPerStep.i())+1;
			if(getHeat(x, y) + erodeAmount >= Types.types[blockType].maxHealth) {
				heatMap[x+y*w] = 0;
				if(x>1 && x<w-2 && y>1 && y< h-2) {
//					bustSquare(x, y);
					set(x,y,Types.EMPTY);
				}
			} else {
				heatMap[x+y*w] += erodeAmount;
			}
		}
	}
	
	void bustSquare(int inx, int iny) {
		int blockType = get(inx,iny);
		for(int x=inx-1;x<inx+2;x++) {
			for(int y=iny-1;y<iny+2;y++) {
				if(Types.types[blockType].isBreakable) set(x,y, Types.EMPTY);
				setHeat(x, y, 0);
			}
		}
	}
	
}
