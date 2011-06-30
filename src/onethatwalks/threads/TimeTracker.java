package onethatwalks.threads;

import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

public class TimeTracker extends Thread {
	public static final Logger log = Logger.getLogger("Minecraft");

	public void run() {
		while (true) {
			SATools.time = SATools.world.getTime();
			SAToolsGUI.jLabel_MAIN_TIME_DATA.setText(Long
					.toString(SATools.time));
			SAToolsGUI.jLabel_MAIN_WEATHER_DATA.setText(SAToolsGUI
					.checkConditions());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
