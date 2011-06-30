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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import onethatwalks.satools.listeners.SAToolsEntityListener;
import onethatwalks.satools.listeners.SAToolsPlayerListener;
import onethatwalks.tasker.TaskScheduler;
import onethatwalks.threads.ThreadHandler;
import onethatwalks.util.NumberHandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.tips48.restartNow.RestartNowApi;

/**
 * SATools - Server Admin Tools
 * 
 * @author OneThatWalks
 */
public class SATools extends JavaPlugin {
	// Listeners
	private final SAToolsPlayerListener playerListener = new SAToolsPlayerListener(
			this);
	private final SAToolsEntityListener entityListener = new SAToolsEntityListener(
			this);
	// END
	// Plugin Description
	private PluginDescriptionFile pdfFile;
	// END
	// Grab the logger
	public static final Logger log = Logger.getLogger("Minecraft");
	// END
	// RestartNow - tips48; initialization
	public RestartNowApi restartNow;
	// END
	// Thread Handler
	public ThreadHandler threadHandler = new ThreadHandler(this);
	// END
	// Task Handler
	public static TaskScheduler taskScheduler;
	// END
	// Init plugin info holders
	public static double version;
	public static String name;
	public static ArrayList<String> authors_RAW;
	public static String authors = "";
	// END
	// Init GUI
	public SAToolsGUI gui = new SAToolsGUI(this);
	// END
	// Config
	Configuration config;
	// END
	// Number Handler
	NumberHandler numbers = new NumberHandler();
	// END
	// Update feature data
	URL pluginInfo;
	// END
	// Actual plugin variables
	public static World world;
	public static List<String> gods = new ArrayList<String>();
	public static long time;

	static enum Weather {
		CLEAR, STORM, THUNDER
	}

	private static final Map<String, Object> CONFIG_DEFAULTS = new HashMap<String, Object>();
	static {
		CONFIG_DEFAULTS.put("Gods", null);
	}

	// END

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

	public void onEnable() {
		// Grab plugin.yml file and contents
		pdfFile = this.getDescription();
		// Create data files
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
			log.info("Directory created");
		}
		// Check for updates
		checkUpdate();
		load();
		world = getServer().getWorld(getServer().getWorlds().get(0).getName());
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
		if (getServer().getPluginManager().isPluginEnabled("RestartNow")) {
			restartNow = (RestartNowApi) getServer().getPluginManager()
					.getPlugin("RestartNow");
		} else {
			log.warning("RestartNow is not found, or disabled.");
		}
	}

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
								log.warning("You are running an older version of SATools to prevent errors you may want to update your plugin.");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Your version is up to date!");
						}
					} else {
						log.severe("Error");
					}
				}
			}
		} catch (MalformedURLException e) {
			log.severe("Malformed URL");
		} catch (IOException e) {
			log.severe("Could no connect");
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
			if (restartNow != null) {
				restartNow.restartServer("Restarting to update",
						"WE are restarting the server, come back soon!",
						ChatColor.LIGHT_PURPLE, null);
			} else {
				log.warning("SATools: You do not have RestartNow, so we cannot restart for you.  Stopping instead...");
				if (getServer().dispatchCommand(
						new ConsoleCommandSender(getServer()), "stop"))
					;
			}
		} else {
			log.warning("You are running an older version of SATools!");
		}
	}

	/**
	 * Saves the current data [Currently only saves gods]
	 */
	private void save() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = new Configuration(configFile);
		log.info("Saving...");
		config.setHeader(name + " V" + version + " by: " + authors);
		config.setProperty("Gods", gods);
		config.save();
	}

	/**
	 * Loads the data
	 */
	private void load() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = new Configuration(configFile);
		config.load();
		if (configFile.exists()) {
			log.info("Loading gods...");
			gods = config.getStringList("Gods", null);
		} else {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * setWeather
	 * 
	 * Sets the weather for the current world
	 * 
	 * @param type
	 *            Weather to set to
	 */
	public void setWeather(Weather type) {
		if (type.equals(Weather.CLEAR)) {
			world.setStorm(false);
			world.setThundering(false);
		} else if (type.equals(Weather.STORM)) {
			world.setStorm(true);
		} else if (type.equals(Weather.THUNDER)) {
			world.setStorm(true);
			world.setThundering(true);
		} else {
			log.severe("Error setting weather!!!");
		}
	}

	/**
	 * spawnCreature Spawns a creature and makes sure it happens using
	 * spawnCreature in org.bukkit.world
	 * 
	 * @param loc
	 *            Location to spawn (based on input)
	 * @param type
	 *            type of creature to spawn
	 */
	public static void spawnCreature(Location loc, CreatureType type) {
		try {
			if (world.spawnCreature(loc, type) == null) {
				log.severe("Failed to create " + type.getName() + " at "
						+ loc.toString());
			} else {
				log.info("Spawned " + type.getName() + " at " + loc.toString());
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
	}

	/**
	 * Spawn an object at the desired location and checks if it happens
	 * 
	 * @param location
	 *            Location to spawn the object
	 * @param object
	 *            the object to spawn
	 */
	public static void spawnObject(Location location, String object) {
		try {
			if (object.trim().equalsIgnoreCase("tree")) {
				if (world.generateTree(
						location,
						TreeType.values()[random(0,
								TreeType.values().length - 1)])) {
					log.info("Tree created successfully");
				} else {
					log.severe("Failed to create tree");
				}
			} else if (object.trim().equalsIgnoreCase("lightning")) {
				if (world.strikeLightning(location) != null) {
					log.info("Lightning created successfully");
				} else {
					log.severe("Failed to create lightning");
				}
			} else if (object.trim().equalsIgnoreCase("light post")) {
				if (createLightPost(location)) {
					log.info("Lightning created successfully");
				} else {
					log.severe("Failed to create lightning");
				}
			} else {
				log.severe("Failed to spawn object " + object);
				world.getBlockAt(location).setType(org.bukkit.Material.TNT);
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}

	}

	private static boolean createLightPost(Location loc) {
		try {
			Block block_1 = world.getBlockAt(loc);
			Block block_2 = world.getBlockAt(loc.getBlockX(),
					loc.getBlockY() + 1, loc.getBlockZ());
			Block block_3 = world.getBlockAt(loc.getBlockX(),
					loc.getBlockY() + 2, loc.getBlockZ());
			block_1.setType(Material.FENCE);
			block_2.setType(Material.FENCE);
			block_3.setType(Material.GLOWSTONE);
			if (block_1.getType() == Material.FENCE
					&& block_2.getType() == Material.FENCE
					&& block_3.getType() == Material.GLOWSTONE) {
				return true;
			}
			return false;
		} catch (Exception e) {
			log.severe(e.getMessage());
			return false;
		}
	}

	/**
	 * Creates a random integer between the Minimum and the Maximum including
	 * those integers
	 * 
	 * @param Min
	 *            Minimum value
	 * @param Max
	 *            Maximum Value
	 * @return random int between Min and Max
	 */
	private static int random(int Min, int Max) {
		return Min + (int) (Math.random() * ((Max - Min) + 1));
	}

}