package ants;

import java.util.Vector;

import ants.ml.InitMLListener;

public class AntleEventManager {
	Vector<InitMLListener> initMLListeners;
	public void raiseInitMLEvent(MLInterface source) {
		if(initMLListeners != null) {			
			for(int i=0; i<initMLListeners.size(); i++) {
				InitMLListener l =  initMLListeners.get(i);
				if(l != null) {
					l.InitML(source);
				} else {
					initMLListeners.remove(i);
					System.err.println("removed  initMLListener at " + i);
				}
			}
		}
	}
	public void addInitMLListener(InitMLListener in) {
		if(in != null) {
			initMLListeners.add(in);
		} else {
			System.err.println("null initmllistener added");
		}

	}
	public static void resetMap() {
		
	}
}
