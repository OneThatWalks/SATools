package onethatwalks.satools;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * SATools - Server Admin Tools
 * 
 * @author OneThatWalks
 */
public class SATools extends JavaPlugin {
	private final SAToolsPlayerListener playerListener = new SAToolsPlayerListener(
			this);
	@SuppressWarnings("unused")
	private final SAToolsBlockListener blockListener = new SAToolsBlockListener(
			this);
	public static final Logger log = Logger.getLogger("Minecraft");
	public static double version;
	public static String name;
	public static ArrayList<String> authors_RAW;
	public static String authors = "";
	SAToolsGUI gui = new SAToolsGUI(this);
	TimeWatch tw = new TimeWatch();
	GarbageCollection gc = new GarbageCollection();
	private boolean twAlive = true;
	private boolean gcAlive = true;
	static boolean runGC = false;
	long time;

	public void onDisable() {
		System.out.println("SATools Disabled, Thanks for using SATools!");
		SAToolsGUI.piAlive = false;
		SAToolsGUI.pi.interrupt();
		twAlive = false;
		tw.interrupt();
		gcAlive = false;
		gc.interrupt();
	}

	public void onEnable() {
		// Event Register
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener,
				Event.Priority.Normal, this);
		// Grab plugin.yml file and contents
		PluginDescriptionFile pdfFile = this.getDescription();
		// Enable Text to be seen in server log
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");
		// Start Threads
		gc.start();
		runGC = true;
		tw.start();
		// plugin info setting
		version = Double.parseDouble(pdfFile.getVersion());
		name = pdfFile.getName();
		authors_RAW = pdfFile.getAuthors();
		for (int i = 0; i < authors_RAW.size(); i++) { // Set up author line
			authors = authors + authors_RAW.get(i)
					+ (authors_RAW.size() == 1 ? "" : ", ");
		}
		// Gui set up and start
		gui.setTitle(name + " v" + version
				+ (authors_RAW.isEmpty() ? "" : " - " + authors));
		gui.setVisible(true);
	}

	class TimeWatch extends Thread {
		public void run() {
			try {
				while (twAlive) {
					time = getServer().getWorld("DarrisonCraft").getTime();
					if (time >= 13000 && time <= 13200) {
						getServer().broadcastMessage(
								ChatColor.RED + "Time to sleep");
						runGC = true;
						Thread.sleep(10000);
					}
					if (time >= 0 && time <= 200) {
						runGC = true;
						Thread.sleep(10000);
					}
					Thread.sleep(2500);
				}
			} catch (InterruptedException e) {
				log.info(e.getMessage());
			}
		}
	}

	class GarbageCollection extends Thread {
		public void run() {
			try {
				while (gcAlive) {
					if (runGC) {
						Runtime r = Runtime.getRuntime();
						log.info("Running Garbage Collection please wait...");
						r.gc();
						runGC = false;
						log.info("Garbage Collection is complete.");
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				log.info(e.getMessage());
			}
		}
	}

}