package onethatwalks.threads;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

public class ThreadHandler extends Thread {
	static Plugin plugin;
	public static GarbageCollection garbageCollection = new GarbageCollection();
	public static GUIManager guiManager = null;
	public static final Logger log = Logger.getLogger("Minecraft");
	public static TimeTracker timeTracker = new TimeTracker();

	public static void runGC() {
		garbageCollection.runGC = true;
	}

	private ArrayList<Thread> threadMap = new ArrayList<Thread>();

	public ThreadHandler(Plugin instance) {
		plugin = instance;
		guiManager = new GUIManager(instance);
		threadMap.add(garbageCollection);
		threadMap.add(timeTracker);
		threadMap.add(guiManager);
	}

	/**
	 * Destroys all threads associated with ThreadHandler
	 */
	public void destroyAllThreads() {
		for (Thread t : threadMap) {
			destroyThread(t);
		}
	}

	/**
	 * @param threads
	 *            The Thread/Threads to destroy
	 */
	public void destroyThread(Thread... threads) {
		for (Thread thread : threads) {
			// thread.interrupt();
			// Lets try a different way to end a thread
			if (threadMap.contains(thread)) {
				thread = null;
			}
		}
	}

	public void run(Thread runOnce) {
		if (runOnce != null) {
			runOnce.run();
		} else {
			log.warning("SATools.ThreadHandler: Please use a proper thread.");
		}
	}

	/**
	 * Starts all the threads associated with ThreadHandler
	 */
	public void startAllThreads() {
		for (Thread t : threadMap) {
			startThread(t);
		}
	}

	/**
	 * Starts the specified thread or a number of threads
	 * 
	 * @param threads
	 *            Thread/Threads to start
	 */
	public void startThread(Thread... threads) {
		for (Thread thread : threads) {
			// log.info("SATools.ThreadHandler: Starting thread "
			// + thread.getName());
			thread.start();
		}
	}
}
