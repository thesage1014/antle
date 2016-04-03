package ants;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.util.Arrays;
import java.util.Random;

public final class AntsMap {
	public int[] map,oldMap,heatMap;
	ParamSet ps;
	final int w, h;
	int foodLeft=0;
	MapGenerator mapGen;

	public AntsMap(int inw, int inh, ParamSet inParams) {
		w = inw;
		h = inh;
		ps = inParams;
		map = new int[w*h];
		heatMap = new int[w*h];
		
		mapGen = new MapGenerator();
		mapGen.generateEarlyTestMap(this, ps);
		// Count all food
		for(int i=0; i<map.length; i++) if(map[i]==Type.food) foodLeft++;
		
		applyCurrentMap();
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
	public int getOld(int x, int y) {
		return oldMap[x+w*y];
	}
	
	public BufferedImage getImage() {
		byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
		byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
		byte[] cb = new byte[]{(byte)44,	22,		14,		44};
		IndexColorModel model = new IndexColorModel(8,cr.length,cr,cg,cb);
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED,model);
		image.getRaster().setPixels(0, 0, w, h, map);
		return image;
	}
	public BufferedImage getHeatImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		image.getRaster().setPixels(0, 0, w, h, heatMap);
		return image;
	}
	
	public void applyCurrentMap() {
		oldMap = map.clone();
	}

}
