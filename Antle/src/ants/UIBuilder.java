package ants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.event.MouseInputListener;

public class UIBuilder implements MouseInputListener, MouseWheelListener, KeyEventDispatcher {
//	GlobalParamSet ps;
//	Vector<ParamSet> paramSets;
	ParamSetManager pSM;
	int currentParamSet=0, psPanelScrollY=0, psPanelScrollYTarget=0;
	boolean mouseDown = false, showingMenu = true;
	public UIBuilder(ParamSetManager inparamSetHandler) {
		pSM = inparamSetHandler;
//		ps = inPs;
	}
	
	public void paint(Graphics g) {
		if(showingMenu) {
			ParamSet ps = pSM.getParamSet(currentParamSet);
			BufferedImage psPanelImage = new BufferedImage(260,ps.params.length*50+10,BufferedImage.TYPE_4BYTE_ABGR);
			Graphics uig = psPanelImage.createGraphics();
			psPanelScrollY += (psPanelScrollYTarget-psPanelScrollY)*.2;
			
			for(int i=0; i<pSM.getParamSets().size(); i++) {
				ParamSet curPS = pSM.getParamSet(i);
				int colorValue;
				if(curPS == ps) {
					colorValue = 255; 
				} else {
					colorValue = 150+20*(i%2);
				}
				g.setColor(new Color(colorValue,colorValue,colorValue));
				g.fillRect(0, i*50, 50, 50);
				g.setColor(new Color(0,0,0));
				g.drawString(curPS.name, 5, i*50 + 20);
			}
			g.translate(50, psPanelScrollY);
			uig.setColor(new Color(26,24,7));
			uig.fillRect(0,0,200,ps.params.length*50+10);
			uig.setColor(new Color(51,44,26));
			uig.fillRect(200,0,60,ps.params.length*50+10);
			for(int i=0; i<ps.params.length; i++) {
				Param curParam = ps.params[i];
				if(curParam.isReadOnly) 
					uig.setColor(new Color(163,138,130));
				else 
					uig.setColor(new Color(163,72,44));
				if(curParam.isToggleOnly) {
					uig.fillRect(10,i*50+10,180,40);
					uig.setColor(new Color(255,255,255));
					uig.fillRect(160,i*50+20,20,20);
					if(curParam.b()) {
						uig.setColor(new Color(0,0,0));
						uig.fillRect(165,i*50+25,10,10);
					}
					uig.setColor(new Color(255,255,255));
					uig.drawString(curParam.name, 20, i*50+35);
					uig.drawString(curParam.b() + "", 205, i*50+35);
				} else {
					double relativeItemWidth = (curParam.value-curParam.min)/(curParam.max-curParam.min)*180;
					uig.fillRect(10,i*50+10,(int)relativeItemWidth,40);
					uig.setColor(new Color(255,255,255));
					uig.drawString(curParam.name, 20, i*50+35);
					uig.drawString(curParam.value + "", 205, i*50+35);
				}
			}
			g.drawImage(psPanelImage,0,0,null);
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		psPanelScrollYTarget -= e.getUnitsToScroll()*20;
	}
	
	void clickOperations(MouseEvent e) {
		ParamSet ps = pSM.getParamSet(currentParamSet);
		if(showingMenu) {
			if(e.getID() == MouseEvent.MOUSE_PRESSED) {
				mouseDown = true;
			} else if(e.getID() == MouseEvent.MOUSE_RELEASED) {
				mouseDown = false;
			}
			if((e.getButton() == MouseEvent.BUTTON1) || mouseDown) {
				if(e.getX()<50 && e.getY()/50 < pSM.getParamSets().size()) {
					currentParamSet = e.getY()/50;
					psPanelScrollY = 0;
					psPanelScrollYTarget = 0;
				} else {
					int x = e.getX() - 50;
					int y = e.getY();
					if(x > 0 && y > 10 && x < 200 && y < pSM.getGlobal().displayH.i()) {
						int adjustedY = (y-5-psPanelScrollY)/50;
						if(adjustedY >= ps.params.length || adjustedY < 0) return;
						Param curParam = ps.params[adjustedY];
						if(curParam.isReadOnly) return;
						double cappedX = x>190?190:x;
						double adjustedX = 0;
						if(curParam.isToggleOnly) {
							if(e.getID() == MouseEvent.MOUSE_RELEASED) {
								curParam.toggle();
							}
						} else {
							cappedX = x<10?10:cappedX;
							adjustedX = (cappedX-10)/180d*(curParam.max-curParam.min)+curParam.min;
							curParam.value = adjustedX;
						}
						pSM.paramUpdate(curParam);
					} else if(x >= 250 && x < 270) {
						
					}
				}
			}
		}
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_RELEASED) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) showingMenu = !showingMenu;
			
		}
		return false;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		clickOperations(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		clickOperations(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		clickOperations(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

	
	
}
