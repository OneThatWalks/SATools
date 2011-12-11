/*
 * This file is part of SATools, which is licensed under the GNU GPL.
 * 
 * Any use or modification of this file will be in compliance of the
 * GNU GPL v3 or later version(s). Unauthorized distribution or 
 * distributed as "pay-ware" will be handled in the fullest extent
 * of the violators law.
 * 
 * If you have questions contact me at
 * OneThatWalks@live.com.
 */
package onethatwalks.threads;

import java.util.logging.Handler;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;
import onethatwalks.util.LogHandler;

public class GUIManager extends Thread {
	public final Logger log;
	SAToolsGUI gui;
	SATools plugin;
	public boolean stop = false;
	public Runtime r = Runtime.getRuntime();
	public Handler handler;

	public GUIManager(SATools instance, SAToolsGUI instance2) {
		gui = instance2;
		plugin = instance;
		log = SATools.log;
	}

	@Override
	public void run() {
		handler = new LogHandler(gui.textArea_console);
		log.addHandler(handler);
		while (!stop) {
			// Mem usage
			gui.progressBar_mem.setValue((int) ((r.totalMemory() - r
					.freeMemory()) / 1024));
			//Server Tab
			gui.lblTimeData.setText(Long.toString(plugin.world.getTime()));
			gui.lblWeatherData.setText(getWeather());
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getWeather() {
		if (plugin.getServer().getWorlds().get(0).hasStorm()) {
			if (plugin.world.isThundering()) {
				return "Thunder Storm";
			}
			return "Rain/Snow";
		}
		return "Clear";
	}

}