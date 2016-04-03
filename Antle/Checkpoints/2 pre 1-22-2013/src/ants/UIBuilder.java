package ants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.event.MouseInputListener;

public class UIBuilder implements MouseInputListener, MouseWheelListener {
	ParamSet ps;
	int psPanelScrollY=0, psPanelScrollYTarget=0;
	public UIBuilder(ParamSet inPs) {
		ps = inPs;
	}
	
	public void paint(Graphics g) {
		BufferedImage psPanelImage = new BufferedImage(260,ps.params.length*50+10,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics uig = psPanelImage.createGraphics();
		psPanelScrollY += (psPanelScrollYTarget-psPanelScrollY)*.2;
		g.translate(0, psPanelScrollY);
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
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getUnitsToScroll());
		psPanelScrollYTarget -= e.getUnitsToScroll()*20;
	}
	
	private void clickOperations(MouseEvent e) {
		if(e.getX() > 0 && e.getY() > 10 && e.getX() < 200 && e.getY() < ps.displayH.i()) {
			int adjustedY = (e.getY()-5-psPanelScrollY)/50;
			if(adjustedY >= ps.params.length || adjustedY < 0) return;
			Param curParam = ps.params[adjustedY];
			if(curParam.isReadOnly) return;
			double cappedX = e.getX()>190?190:e.getX();
			double adjustedX = 0;
			if(curParam.isToggleOnly) {
				adjustedX = e.getX()>100?1:0;
			} else {
				cappedX = e.getX()<10?10:cappedX;
				adjustedX = (cappedX-10)/180d*(curParam.max-curParam.min)+curParam.min;
			}
			curParam.value = adjustedX;
		}
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
