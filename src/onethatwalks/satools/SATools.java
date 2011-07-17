package onethatwalks.satools;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import onethatwalks.satools.listeners.SAToolsEntityListener;
import onethatwalks.satools.listeners.SAToolsPlayerListener;
import onethatwalks.tasker.TaskScheduler;
import onethatwalks.threads.ThreadHandler;
import onethatwalks.util.MethodHandler;
import onethatwalks.util.NumberHandler;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

/**
 * SATools - Server Admin Tools
 * 
 * @author OneThatWalks
 */
public class SATools extends JavaPlugin {

	public static String authors = "";
	public static ArrayList<String> authors_RAW;
	// END
	// Config
	public static Configuration config;
	public static List<String> gods = new ArrayList<String>();
	// END
	// Grab the logger
	public static final Logger log = Logger.getLogger("Minecraft");
	public static String name;
	// END
	// Task Handler
	public static TaskScheduler taskScheduler;
	public static long time;
	// END
	// Init plugin info holders
	public static double version;
	// END
	// Actual plugin variables
	public static World world;
	private boolean checkUpdate;
	private final SAToolsEntityListener entityListener = new SAToolsEntityListener();
	// END
	// Init GUI
	public SAToolsGUI gui = new SAToolsGUI(this);
	// END
	// Number Handler
	NumberHandler numbers = new NumberHandler();

	// END
	// Plugin Description
	private PluginDescriptionFile pdfFile;

	// END

	// Listeners
	private final SAToolsPlayerListener playerListener = new SAToolsPlayerListener(
			this);

	// END
	// Update feature data
	URL pluginInfo;

	private boolean showUpdateBox;

	// END
	// Thread Handler
	public ThreadHandler threadHandler = new ThreadHandler(this);

	// END
	// MethodHandler
	public static MethodHandler methods = new MethodHandler();

	/**
	 * WIP Auto-Update method
	 * 
	 * Will check if you need to update version
	 */
	private void checkUpdate() {
		try {
			pluginInfo = new URL(
					"https://raw.github.com/OneThatWalks/SATools/master/plugin.yml");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					pluginInfo.openStream()));
			String strLine;
			while ((strLine = in.readLine()) != null) {
				if (strLine.contains("version: ")) {
					String[] tokens = strLine.split(" ");
					String version = tokens[1];
					if (numbers.isDouble(version)
							&& numbers.isDouble(pdfFile.getVersion())) {
						if (Double.parseDouble(version) > Double
								.parseDouble(pdfFile.getVersion())) {
							if (JOptionPane
									.showConfirmDialog(
											null,
											"There is a new update! Would you like to download?",
											"Update Available!",
											JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
								download();
							} else {
								log.warning("SATools: You are running an older version of SATools to prevent errors you may want to update your plugin.");
							}
						} else {
							if (showUpdateBox) {
								JOptionPane.showMessageDialog(null,
										"Your version is up to date!");
							} else {
								log.info("SATools: Your version is up to date!");
							}
						}
					} else {
						log.severe("SATools: Error");
					}
				}
			}
		} catch (MalformedURLException e) {
			log.severe("SATools: Malformed URL");
		} catch (IOException e) {
			log.severe("SATools: Could no connect");
		}

	}

	private void download() {
		OutputStream out = null;
		URLConnection uc = null;
		InputStream in = null;
		try {
			URL url = new URL(
					"https://github.com/downloads/OneThatWalks/SATools/SATools.jar");
			out = new BufferedOutputStream(new FileOutputStream("plugins"
					+ File.separator + "SATools.jar"));
			uc = url.openConnection();
			in = uc.getInputStream();
			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;
			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (out != null && in != null) {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (JOptionPane.showConfirmDialog(null,
				"Update downloaded, would you like to restart?",
				"Update Finished", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			getServer().reload();
		} else {
			log.warning("SATools: You are running an older version of SATools!");
		}
	}

	/**
	 * Loads the data
	 */
	private void load() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = new Configuration(configFile);
		config.load();
		if (configFile.exists()) {
			log.info("SATools: Loading gods...");
			gods = config.getStringList("Gods", null);
			checkUpdate = config.getBoolean("Check for updates", true);
			showUpdateBox = config.getBoolean("Show update check", true);
		} else {
			try {
				log.severe("SATools: SATools: No configuration, creating one instead");
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDisable() {
		// Save data
		save();
		// end GUI
		gui.dispose();
		gui = null;
		// End those pesky threads
		threadHandler.destroyAllThreads();
		// Thank the user
		System.out.println("SATools Disabled, Thanks for using SATools!");
	}

	@Override
	public void onEnable() {
		// Grab plugin.yml file and contents
		pdfFile = this.getDescription();
		// Create data files
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
			log.info("SATools: Directory created");
		}
		// We load before update because it decides if we check for updates of
		// not
		load();
		// Check for updates
		if (checkUpdate) {
			checkUpdate();
		}
		world = getServer().getWorlds().get(0);
		// Event Register
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener,
				Event.Priority.Normal, this);
		// Enable Text to be seen in server log
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");
		// Start Threads
		threadHandler.startAllThreads();
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
		taskScheduler = new TaskScheduler(this);
	}

	/**
	 * Saves the current data [Currently only saves gods]
	 */
	private void save() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		if (configFile.exists()) {
			config = new Configuration(configFile);
			config.load();
		} else {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			config = new Configuration(configFile);
		}
		log.info("SATools: Saving...");
		config.setHeader(name + " V" + version + " by: " + authors);
		config.setProperty("Gods", gods);
		config.save();
	}

}