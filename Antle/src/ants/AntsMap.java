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

//import org.omg.CORBA.REBIND;

public final class AntsMap {
	public int[] erosionMap;
	private Tile[][] tileMap;
	Vector<Colony> colonies;
	ParamSetManager pSM;
	WorldLogic world;
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
		scents.add(new Scent("Food", new int[]{135,163,44}, Scent.FOODindex, w,h));
		world = new WorldLogic();
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
		(ant.colony.scent.values)[x][y] += ant.scentValue;
		
	}
	public void layScent(int x, int y, Scent s, float value) {
		s.values[x][y] += value;
	}
	
	public BufferedImage getImage() {
		requestingImage = true;
		return imageBuffer;
	}
	private BufferedImage buildImage() {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		if(readyToDraw) {
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
							int[] curColor = curTile.entity.getColor();
							r.setPixel(x, y, new int[]{curColor[0], curColor[1], curColor[2]});
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
						if(s.values[x][y] < totals[x][y]) {
							if(s.values[x][y] > 1) {
								hues[x][y] = s.getHue();
							}
						} else {
							hues[x][y] = s.getHue();
						}
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
					value = (1-50/(value+49));
				}
				int hsb = Color.HSBtoRGB(hues[x][y], .9f, value);
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

	public boolean attackBlock(int x, int y, Entity eroder) { //Returns if a block was broken
		Type blockType = tileMap[x][y].getType();
		if(blockType == TileTypes.FOOD && pSM.getGlobal().useMachineLearning.bool()) {
			pSM.getGlobal().debugAnt.AddReward(10, this);
		}
		if(blockType.isBreakable) {
			int erodeAmount = rand.nextInt(pSM.getGlobal().erosionPerStep.i())+1;
			if(getErosion(x, y) + erodeAmount >= blockType.maxHealth) {
				if(blockType.givesItem) {
					if(eroder.pickupItem(blockType.itemOnBreak)) {
						erosionMap[x+y*w] = 0;
						if(x>1 && x<w-2 && y>1 && y< h-2) {
							get(x,y).setTo(TileTypes.EMPTY);
						}
					}
				} else {
					erosionMap[x+y*w] = 0;
					if(x>1 && x<w-2 && y>1 && y< h-2) {
						get(x,y).setTo(TileTypes.EMPTY);
					}
				}
			} else {
				erosionMap[x+y*w] += erodeAmount;
			}
		}
		return false;
	}

	public void kill() {
		world = new WorldLogic();
		for(int i=0; i<colonies.size(); i++) {
			colonies.get(i).kill();
		}
		colonies.clear();
	}

	void tick() {
		blurMap();
		
		world.antTick(this,pSM);
		if(requestingImage) {
			imageBuffer = buildImage();
		}
	}

	private void blurMap() {
		for(Scent s:scents) {
			for(int x=0; x<w; x++) {
				for(int y=0; y<h; y++) {
					if(tileMap[x][y].getType().isSolid) {
						s.values[x][y] *= .99f;
					} else {
						s.values[x][y] *= .995f;
					}
				}
			}
			for(int i=0;i<(w*h)/pSM.getGlobal().scentBlurSpeed.value/2;i++) { // Cross pattern blur droplets
				int blurx = rand.nextInt(w-3)+1;
				int blury = rand.nextInt(h-3)+1;
				int totalScent = 0;
				totalScent += (int)s.values[blurx][blury];
				totalScent += (int)s.values[blurx-1][blury];
				totalScent += (int)s.values[blurx+1][blury];
				totalScent += (int)s.values[blurx][blury-1];
				totalScent += (int)s.values[blurx][blury+1];
				totalScent = (totalScent)/5;
				s.values[blurx][blury] = (s.values[blurx][blury]+totalScent)/2;
				s.values[blurx-1][blury] = (s.values[blurx-1][blury]+totalScent)/2;
				s.values[blurx+1][blury] = (s.values[blurx+1][blury]+totalScent)/2;
				s.values[blurx][blury-1] = (s.values[blurx][blury-1]+totalScent)/2;
				s.values[blurx][blury+1] = (s.values[blurx][blury+1]+totalScent)/2;
			}
			for(int i=0;i<(w*h)/pSM.getGlobal().scentBlurSpeed.value/2;i++) { // Square pattern blur droplets
				int blurx = rand.nextInt(w-3);
				int blury = rand.nextInt(h-3);
				float totalScent = 0;
				int openSpaces = 0;
				for(int tx=0; tx<2; tx++) {
					for(int ty=0; ty<2; ty++) {
						if(!tileMap[blurx+tx][blury+ty].getType().isSolid) {
							openSpaces++;
						}
						totalScent += s.values[blurx+tx][blury+ty];
					}					
				}
				totalScent = totalScent/openSpaces;
				for(int tx=0; tx<2; tx++) {
					for(int ty=0; ty<2; ty++) {
						if(openSpaces != 0) {
							if(!tileMap[blurx+tx][blury+ty].getType().isSolid) {
								s.values[blurx+tx][blury+ty] = totalScent;
							} else {
								s.values[blurx+tx][blury+ty] = 0;
							}
						}
					}					
				}
			}
		}
	}

	public void addColony(ParamSetManager inpSM, int inx, int iny, String inname, int[] incolor) {
//		inpSM, distX, map.h/2, Names.getName(), map, new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))
		Colony newColony = new Colony(inpSM, inx, iny, inname, this, incolor, scents.size());
		scents.add(newColony.scent);
		colonies.add(newColony);
	}

	public Tile get(int x, int y) {
		return tileMap[x][y];
	}
	int getErosion(int x, int y) {
		return erosionMap[x+w*y];
	}
	void setErosion(int x, int y, int type) {
		erosionMap[x+w*y] = type;
	}

	public boolean InBounds(int x, int y) {
		return (x < w && x > 0 && y < h && y > 0);
	}
}

