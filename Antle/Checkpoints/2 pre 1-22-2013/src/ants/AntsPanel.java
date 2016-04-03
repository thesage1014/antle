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

public final class AntsPanel extends JPanel implements Tickable, MouseListener, KeyEventDispatcher {
	AntsApplet applet;
	AntsMap map;
//	TickThread ticker;
	WorldBehavior brain;
	ParamSet ps;
	UIBuilder ui;
	boolean showingHeatmap = false, showingMenu = true;
	public AntsPanel(AntsApplet inapplet, ParamSet inParams) {
		applet = inapplet;
		ps = inParams;
		map = new AntsMap(ps.mapW.i(),ps.mapH.i(), ps);
		brain = new WorldBehavior(ps);
		ui = new UIBuilder(ps);
		new TickThread(this, ps.tickDelay.i(), Tickable.GAME);
		new TickThread(this, ps.renderTickDelay.i(), Tickable.RENDER);
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
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .075f));
			g2.drawImage(map.getHeatImage(), 0, 0, ps.displayW.i(), ps.displayH.i(), this);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .7f));
			if(showingMenu) ui.paint(g);
			g2.setComposite(tempComposite);
		}
		
	}

	@Override
	public void tick(int tickType) {
		if(tickType == Tickable.GAME) {
			map.applyCurrentMap();
			brain.antTick(map,ps);
		} else if (tickType == Tickable.RENDER) {
			repaint();
		} else {
			
		}
		
		
	}
	private void restart() {
		map = new AntsMap(map.w,map.h,ps);
		brain = new WorldBehavior(ps);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		System.out.println(e.getKeyChar() + " " + e.getID());
		if(e.getID() == KeyEvent.KEY_RELEASED) {
			if(e.getKeyCode() == KeyEvent.VK_R) restart();
			if(e.getKeyCode() == KeyEvent.VK_SPACE) showingMenu = !showingMenu;
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
	public void mouseClicked(MouseEvent arg0) {
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
