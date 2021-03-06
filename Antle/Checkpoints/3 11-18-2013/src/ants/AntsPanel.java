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

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public final class AntsPanel extends JPanel implements Tickable, MouseListener, KeyEventDispatcher, ParamUpdateHandler {
	AntsApplet applet;
	AntsMap map;
	TickThread tickThread, renderThread;
	WorldBehavior world;
	GlobalParamSet ps;
	UIBuilder ui;
	boolean showingHeatmap = false;
	public AntsPanel(AntsApplet inapplet, GlobalParamSet inParams) {
		applet = inapplet;
		ps = inParams;
		map = new AntsMap(ps.mapW.i(),ps.mapH.i(), ps);
		world = new WorldBehavior();
		ui = new UIBuilder(ps,this);
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
			g.drawImage(map.getHeatImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
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
		if(tickType == Tickable.GAME) {
			map.applyNextMap();
			world.antTick(map,ps);
		} else if (tickType == Tickable.RENDER) {
			repaint();
		} else {
			
		}
		
	}
	
	private void restart() {
		map = new AntsMap(map.w,map.h,ps);
		world = new WorldBehavior();
	}

	@Override
	public void paramUpdate(Param param) {
		if(param.name == "Tick delay(ms)") {
			tickThread.delay = param.i();
		} else if(param.name == "Render tick delay(ms)") {
			renderThread.delay = param.i();
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
		tick(Tickable.GAME);
		tick(Tickable.RENDER);
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
