package ants;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;

public final class AntsApplet extends JApplet {
	AntsPanel panel;
	GlobalParamSet params;
	public void init() {
		// TODO Make and load config file for parameter overrides
		params = new GlobalParamSet();
		panel = new AntsPanel(this, params);
		setBackground(Color.BLACK);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(panel);
		add(panel);
	}
}
