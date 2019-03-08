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

public final class StartGame extends JFrame {
	AntlePanel panel;
	ParamSetGlobal params;
	public StartGame() {
		params = new ParamSetGlobal();
		
		InitPanel();
	}
	public StartGame(ParamSetGlobal inParamset) {
		params = inParamset;
		InitPanel();
	}
	void InitPanel() {
		panel = new AntlePanel(params);
		if(params.useMachineLearning.bool()) {
			MLInterface mli = new MLInterface(panel);
			mli.tickThread = panel.tickThread; // TODO initialize better
		}
		panel.setBackground(Color.BLACK);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(panel);
		add(panel);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 512));
        pack();
        setVisible(true);
	}
	public static void main(String[] args) {
		StartGame frame = new StartGame();
	}
}
