package onethatwalks.threads;

import java.util.logging.Handler;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;
import onethatwalks.util.LogHandler;

import org.bukkit.plugin.Plugin;

public class GUIManager extends Thread {
	public static final Logger log = SATools.log;
	Plugin plugin;
	SAToolsGUI gui;
	public boolean stop = false;
	public Runtime r = Runtime.getRuntime();
	public Handler handler;

	public GUIManager(Plugin instance, SAToolsGUI instance2) {
		plugin = instance;
		gui = instance2;
	}

	@Override
	public void run() {
		handler = new LogHandler(gui.textArea);
		log.addHandler(handler);
		while (!stop) {
			// Mem usage
			gui.progressBar_mem.setValue((int) ((r.totalMemory() - r
					.freeMemory()) / 1024));
		}
	}

}