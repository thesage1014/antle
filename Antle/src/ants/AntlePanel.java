package ants;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.EventListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import ants.ml.AntStateData;

public final class AntlePanel extends JPanel implements Tickable, MouseListener, KeyEventDispatcher, ParamSetManager {
	AntleMap map;
	TickThread tickThread, renderThread;
	ParamSetGlobal ps;
	Vector<ParamSet> paramSets;
	UIBuilder ui;
	boolean showingHeatmap = false;
	private int recentTicks = 0;
	public AntlePanel(ParamSetGlobal inParams) {
		paramSets = new Vector<ParamSet>();
		paramSets.add(inParams);
		ps = (ParamSetGlobal)paramSets.get(0);
		map = new AntleMap(ps.mapW.i(),ps.mapH.i(), this);
		ui = new UIBuilder(this);
		tickThread = new TickThread(this, ps.playSpeed.i(), Tickable.GAME, ps);
		renderThread = new TickThread(this, ps.renderTickDelay.i(), Tickable.RENDER,ps);
		addMouseListener(this);
		addMouseListener(ui);
		addMouseWheelListener(ui);
		addMouseMotionListener(ui);
		if(ps.useMachineLearning.bool()) {
			ps.debugAnt = map.colonies.get(0).spawnMLAnt();
		}
	}
	
	public void paint(Graphics g) {
		if(showingHeatmap) {
			g.drawImage(map.getScentImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
		} else {
			Graphics2D g2 = (Graphics2D)g;
			BufferedImage mapBuffer = map.getImage();
			if(mapBuffer.getWidth() != 1) {				
				g2.drawImage(mapBuffer, 0, 0, ps.displayW.i(), ps.displayH.i(), this);
			} else {
				g2.setColor(Color.BLACK);
				g2.drawString("Initializing rl4j...  DL4J UI Server starting at http://localhost:9000", ps.displayW.i()/5*3, ps.displayH.i()/2);
				g2.drawString("R - restart", ps.displayW.i()/5*3, ps.displayH.i()/2+25);
				g2.drawString("Space - hide gui", ps.displayW.i()/5*3, ps.displayH.i()/2+38);
				g2.drawString("Right Click - show scent map", ps.displayW.i()/5*3, ps.displayH.i()/2+51);
			}
			Composite tempComposite = g2.getComposite();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
			ui.paint(g);
			g2.setComposite(tempComposite);
		}
	}

	@Override
	public void tick(int tickType) {
		recentTicks++;
		if(tickType == Tickable.GAME) {
			map.tick();
		} else if(tickType == Tickable.RENDER) {
			repaint();
		} else if(tickType == Tickable.PERFORMANCECOUNTER) {
			System.out.println(recentTicks);
			recentTicks = 0;
		}
		
	}
	public TickThread GetMLTickThread() {
		return tickThread;
	}
	@Override
	public AntStateData mlTick() {
		recentTicks++;
		map.tick();
		return null;
	}
	
	public void restart() {
		map.kill();
		AntleEventManager.resetMap();
		map = new AntleMap(ps.mapW.i(),ps.mapH.i(),this);
		if(ps.useMachineLearning.bool()) {
			ps.debugAnt = map.colonies.get(0).spawnMLAnt();
		}
	}

	@Override
	public void paramUpdate(Param param) {
		if(param == ps.playSpeed) {
			tickThread.delay = param.i();
		} else if(param == ps.renderTickDelay) {
			renderThread.delay = param.i();
		} else if(param.name == "Map width") {
			ps.mapH.value = param.i()/2;
		} else if(param.name == "Map height") {
			ps.mapW.value = param.i()*2;
		} else if(param == ps.useMachineLearning) {
			
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
//		System.out.println(e.getKeyChar() + " " + e.getID());
		ui.dispatchKeyEvent(e);
		if(e.getID() == KeyEvent.KEY_RELEASED) {
			if(e.getKeyCode() == KeyEvent.VK_R) restart();
			
		}
		
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3) showingHeatmap = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3) showingHeatmap = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		tick(Tickable.RENDER);
	}
	
	@Override
	public ParamSet getParamSet(int i) {
		return paramSets.get(i);
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public ParamSetGlobal getGlobal() {
		return ps;
	}

	@Override
	public void addParamSet(ParamSet inparamSet) {
		paramSets.add(inparamSet);
	}

	@Override
	public Vector<ParamSet> getParamSets() {
		return paramSets;
	}

	

	
}
