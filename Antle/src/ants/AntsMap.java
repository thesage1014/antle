package ants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import org.omg.CORBA.REBIND;

public final class AntsMap {
	public int[] erosionMap;
	private Tile[][] tileMap;
	Vector<Colony> colonies;
	ParamSetManager pSM;
	WorldBehavior world;
	final int w, h;
	MapGenerator mapGen;
	Random rand = new Random();
	BufferedImage imageBuffer;
	boolean readyToDraw = false, requestingImage = true;
	public Vector<Scent> scents;
	public AntsMap(int inw, int inh, ParamSetManager inpSM) {
		pSM = inpSM;
		w = inw;
		h = inh;
		tileMap = new Tile[w][h];
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				tileMap[i][j] = new Tile(i,j);
			}
		}
		scents = new Vector<Scent>();
		world = new WorldBehavior();
		imageBuffer = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
		erosionMap = new int[w*h];
		colonies = new Vector<Colony>();
		mapGen = new MapGenerator();
		mapGen.generateEarlyTestMap(this, pSM);
		readyToDraw = true;
//		applyNextMap();
	}
	
	public void layScent(Ant ant) {
		int x = ant.tile.x;
		int y = ant.tile.y;
		float[][] values = scents.get(scents.indexOf(ant.colony.scent)).values;
		values[x][y] += ant.scentValue;
//		if(ant.scentValue <= 5) {
//		} else {
//			for(int i=x-1;i<=x+1;i++) {
//				for(int j=y-1;j<=y+1;j++) {
////				System.out.println(x + " " + i + " | " + y + " " + j + " " + ant.colony.name);
//					values[i][j] += ant.scentValue/6f;
//				}
//			}
//			values[x][y] += ant.scentValue*.85;
//		}
		
	}
	
	public BufferedImage getImage() {
		requestingImage = true;
		return imageBuffer;
	}
	private BufferedImage buildImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		if(readyToDraw) {
//		byte[] cr = new byte[]{(byte)163,	82,		51,	(byte)135};
//		byte[] cg = new byte[]{(byte)132,	36,		41,	(byte)163};
//		byte[] cb = new byte[]{(byte) 44,	22,		14,		   44};
//		IndexColorModel model = new IndexColorModel(8,cr.length,cr,cg,cb);
//		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_INDEXED,model);
//		image.getRaster().setPixels(0, 0, w, h, map);
			Graphics2D g = image.createGraphics();
			WritableRaster r = image.getRaster();
			for(int x=0; x<w; x++) {
				for(int y=0; y<h; y++) {
					Tile curTile = tileMap[x][y];
					Type tileType = curTile.getType();
					Color tileColor = curTile.color;
//				System.out.print(tileType.name + " " + heatMap[x+y*w]);
//				System.out.println(tile.r + " " + tile.g + " " + tile.b);
					if(tileType.isBreakable) {
						float b = (float)(tileType.maxHealth-erosionMap[x+y*w])/tileType.maxHealth;
//					if(b != 1)
//						System.out.print(" " + b + "\n");
						r.setPixel(x, y, new int[]{
								(int)(((float)tileColor.getRed()*b)+tileColor.getRed())/2,
								(int)(((float)tileColor.getGreen()*b)+tileColor.getGreen())/2,
								(int)((float)tileColor.getBlue()*b)});
						
//						g.setColor(new Color(
//								(int)(((float)tile.getRed()*b)+tile.getRed())/2,
//								(int)(((float)tile.getGreen()*b)+tile.getGreen())/2,
//								(int)((float)tile.getBlue()*b)));
					} else if(tileType.isEntity) {
//						System.out.print(tileType.ID);
						try {
							Color curColor = curTile.entity.getColor();
							r.setPixel(x, y, new int[]{curColor.getRed(), curColor.getGreen(), curColor.getBlue()});
//							g.setColor(curTile.entity.getColor());
//							g.fillRect(curTile.x, curTile.y, 1, 1);
						} catch(Exception e) {
							System.out.println(curTile.x + " " + curTile.y + " " + curTile.getType().name);
						}
						
					} else {
						Color curColor = curTile.color;
						r.setPixel(x, y, new int[]{curColor.getRed(), curColor.getGreen(), curColor.getBlue()});
					}
//					g.fillRect(x, y, 1, 1);
				}
			}
//		for(int i=0;i<colonies.size();i++) {
//			for(int a=0;a<colonies.get(i).ants.size();a++) {
//				Ant ant = colonies.get(i).ants.get(a);
//				
//			}
//		}
		} else {
			Graphics g = image.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, w, h);
			g.drawString("Loading...", w/2, h/2);
		}
		requestingImage = false;
		return image;
	}
	public BufferedImage getScentImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		WritableRaster r = image.getRaster();
		float[][] totals = new float[w][h];
		float[][] hues = new float[w][h];
		for(int i=0;i<scents.size();i++) {
			Scent s = scents.get(i);
			for(int x=0;x<w;x++) {
				for(int y=0;y<h;y++) {
					totals[x][y] += s.values[x][y];
					if(s.values[x][y] != 0) {
//						if(s.values[x][y] < totals[x][y]) {
//							float hueTotal = s.getHue();
//							for(int j=0; j<i; j++) {
//								hueTotal += scents.get(i).getHue(); 
//							}
//							hues[x][y] = hueTotal;
//						} else {
//						}
						hues[x][y] = s.getHue();
					}
				}				
			}
		}
		
		for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				float value = (int)totals[x][y];
				if(value == 0) {
					value = .1f;
				} else {
					value = (1-1/value);
				}
				int hsb = Color.HSBtoRGB(hues[x][y], 1f, value);
				int[] rgb = new int[] {
					(hsb >> 16) & 0xFF,
					(hsb >> 8) & 0xFF,
					hsb & 0xFF
				};
				r.setPixel(x, y, rgb);
//						Math.min(value,255),
//						Math.min(Math.max(value-256,0),255),
//						Math.min(Math.max(value-128,0),511)});
			}
		}
		
		return image;
	}
	
//	public void applyNextMap() {
//		oldMap = map.clone();
//	}

	public boolean erode(int x, int y, Entity eroder) { //Returns if a block was broken
		Type blockType = tileMap[x][y].getType();
		if(blockType.isBreakable) {
			int erodeAmount = rand.nextInt(pSM.getGlobal().erosionPerStep.i())+1;
			if(getHeat(x, y) + erodeAmount >= blockType.maxHealth) {
				if(blockType.givesItem) {
					if(eroder.pickupItem(blockType.itemOnBreak)) {
						erosionMap[x+y*w] = 0;
						if(x>1 && x<w-2 && y>1 && y< h-2) {
							get(x,y).setTo(Types.EMPTY);
						}
					}
				} else {
					erosionMap[x+y*w] = 0;
					if(x>1 && x<w-2 && y>1 && y< h-2) {
						get(x,y).setTo(Types.EMPTY);
					}
				}
			} else {
				erosionMap[x+y*w] += erodeAmount;
			}
		}
		return false;
	}

	public void kill() {
		world = new WorldBehavior();
		for(int i=0; i<colonies.size(); i++) {
			colonies.get(i).kill();
		}
	}

	public void tick() {
		blurMap();
		world.antTick(this,pSM);
		if(requestingImage) {
			imageBuffer = buildImage();
		}
	}

	private void blurMap() {
		for(Scent s:scents) {
			for(int i=0;i<(w*h)/pSM.getGlobal().scentBlurSpeed.value;i++) {
				int blurx = rand.nextInt(w-3);
				int blury = rand.nextInt(h-3);
				int totalScent = 0;
//				totalScent += (int)s.values[blurx+1][blury];
				totalScent += (int)s.values[blurx+1][blury+1];
				totalScent += (int)s.values[blurx+2][blury+1];
//				totalScent += (int)s.values[blurx+3][blury+1];
//				totalScent += (int)s.values[blurx][blury+2];
				totalScent += (int)s.values[blurx+1][blury+2];
				totalScent += (int)s.values[blurx+2][blury+2];
//				totalScent += (int)s.values[blurx+2][blury+3];
				totalScent = (totalScent)/4;
//				s.values[blurx+1][blury] = (s.values[blurx+1][blury]+totalScent)/2;
				s.values[blurx+1][blury+1] = (s.values[blurx+1][blury+1]+totalScent)/2;
				s.values[blurx+2][blury+1] = (s.values[blurx+2][blury+1]+totalScent)/2;
//				s.values[blurx+3][blury+1] = (s.values[blurx+3][blury+1]+totalScent)/2;
//				s.values[blurx][blury+2] = (s.values[blurx][blury+2]+totalScent)/2;
				s.values[blurx+1][blury+2] = (s.values[blurx+1][blury+2]+totalScent)/2;
				s.values[blurx+2][blury+2] = (s.values[blurx+2][blury+2]+totalScent)/2;
//				s.values[blurx+2][blury+3] = (s.values[blurx+2][blury+3]+totalScent)/2;
			}
		}
	}

	public void addColony(Colony incolony) {
		colonies.add(incolony);
		scents.add(incolony.scent);
	}

	public Tile get(int x, int y) {
		return tileMap[x][y];
	}
	public int getHeat(int x, int y) {
		return erosionMap[x+w*y];
	}
	public void setHeat(int x, int y, int type) {
		erosionMap[x+w*y] = type;
	}
}

