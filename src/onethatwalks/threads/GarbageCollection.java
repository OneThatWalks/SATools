package onethatwalks.threads;

import java.util.logging.Logger;

import onethatwalks.satools.SAToolsGUI;

class GarbageCollection extends Thread {
	public static final Logger log = Logger.getLogger("Minecraft");
	boolean runGC = true;

	public void run() {
		while (true) {
			if (runGC) {
				SAToolsGUI.jButton_MAIN_GC.setEnabled(false);
				Runtime r = Runtime.getRuntime();
				log.info("Running Garbage Collection please wait...");
				r.gc();
				runGC = false;
				SAToolsGUI.jButton_MAIN_GC.setEnabled(true);
				log.info("Garbage Collection is complete.");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}