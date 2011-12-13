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

public final class GUIManager extends Thread {
	private final Logger log;
	private final SAToolsGUI gui;
	private final SATools plugin;
	public final boolean stop = false;
	public final Runtime r = Runtime.getRuntime();
	private final Handler handler;

	public GUIManager(SATools instance, SAToolsGUI instance2) {
		gui = instance2;
		plugin = instance;
		log = SATools.log;
		handler = new LogHandler(gui.textArea_console);
	}

	/**
	 * Gets the current Minecraft weather conditions
	 * 
	 * @return the weather conditions
	 */
	private String getWeather() {
		if (plugin.getServer().getWorlds().get(0).hasStorm()) {
			if (plugin.world.isThundering()) {
				return "Thunder Storm";
			}
			return "Rain/Snow";
		}
		return "Clear";
	}

	@Override
	public void run() {
		log.addHandler(handler);
		while (!stop) {
			// Server Tab
			gui.lblTimeData.setText(Long.toString(plugin.world.getTime()));
			gui.lblWeatherData.setText(getWeather());
		}
	}

}