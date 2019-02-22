package ants;

//import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public final class AntsApplet extends JFrame {
	AntsPanel panel;
	ParamSetGlobal params;
	public static void main(String[] args) {
		// TODO Make and load config file for parameter overrides
		AntsApplet frame = new AntsApplet();
		frame.params = new ParamSetGlobal();
		frame.panel = new AntsPanel(frame, frame.params);
				frame.panel.setBackground(Color.BLACK);
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(frame.panel);
				frame.add(frame.panel);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        frame.setPreferredSize(new Dimension(1024, 512));
		        frame.pack();
		        frame.setVisible(true);
	}
}
