package onethatwalks.threads;

import java.util.logging.Handler;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;
import onethatwalks.util.LogHandler;

public class GUIManager extends Thread {
	public static final Logger log = SATools.log;
	SAToolsGUI gui;
	SATools plugin;
	public boolean stop = false;
	public Runtime r = Runtime.getRuntime();
	public Handler handler;

	public GUIManager(SATools instance, SAToolsGUI instance2) {
		gui = instance2;
		plugin = instance;
	}

	@Override
	public void run() {
		handler = new LogHandler(gui.textArea_console);
		log.addHandler(handler);
		while (!stop) {
			// Mem usage
			gui.progressBar_mem.setValue((int) ((r.totalMemory() - r
					.freeMemory()) / 1024));
		}
	}

}