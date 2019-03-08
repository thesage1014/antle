package ants;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;

/** @deprecated use StartGame */
public final class AntleApplet extends JApplet {
	AntlePanel panel;
	ParamSetGlobal params;
	public void init() {
		params = new ParamSetGlobal();
		panel = new AntlePanel(params);
		setBackground(Color.BLACK);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(panel);
		add(panel);
	}
}