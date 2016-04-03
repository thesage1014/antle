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
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public final class AntsPanel extends JPanel implements Tickable, MouseListener, KeyEventDispatcher, ParamSetManager {
	AntsApplet applet;
	AntsMap map;
	TickThread tickThread, renderThread;
	ParamSetGlobal ps;
	Vector<ParamSet> paramSets;
	UIBuilder ui;
	boolean showingHeatmap = false;
	private int recentTicks = 0;
	public AntsPanel(AntsApplet inapplet, ParamSetGlobal inParams) {
		paramSets = new Vector<ParamSet>();
		paramSets.add(inParams);
		applet = inapplet;
		ps = (ParamSetGlobal)paramSets.get(0);
		map = new AntsMap(ps.mapW.i(),ps.mapH.i(), this);
		ui = new UIBuilder(this);
		tickThread = new TickThread(this, ps.tickDelay.i(), Tickable.GAME);
		renderThread = new TickThread(this, ps.renderTickDelay.i(), Tickable.RENDER);
		addMouseListener(this);
		addMouseListener(ui);
		addMouseWheelListener(ui);
		addMouseMotionListener(ui);
	}
	
	public void paint(Graphics g) {
//		super.paint(g);
		if(showingHeatmap) {
			g.drawImage(map.getScentImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
		} else {
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(map.getImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
			Composite tempComposite = g2.getComposite();
//			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
//			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .1f));
//			g2.drawImage(map.getHeatImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
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
	
	private void restart() {
		map.kill();
		map = new AntsMap(ps.mapW.i(),ps.mapH.i(),this);
	}

	@Override
	public void paramUpdate(Param param) {
		if(param.name == "Tick delay(ms)") {
			tickThread.delay = param.i();
		} else if(param.name == "Render tick delay(ms)") {
			renderThread.delay = param.i();
		} else if(param.name == "Map width") {
			ps.mapH.value = param.i()/2;
		} else if(param.name == "Map height") {
			ps.mapW.value = param.i()*2;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
