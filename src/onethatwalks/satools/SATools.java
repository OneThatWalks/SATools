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
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import onethatwalks.listeners.SAToolsPlayerListener;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author OneThatWalks
 * 
 * @date 12/11/11
 * 
 * @category Admin Tools, GUI, MISC
 * 
 * @version 1
 * 
 */
public final class SATools extends JavaPlugin {

	public final static Logger log = Logger.getLogger("Minecraft");
	public PluginDescriptionFile pdfFile;
	public SAToolsGUI gui;
	public FileConfiguration config;
	public boolean checkUpdate;
	public static String threadURL = "http://forums.bukkit.org/threads/admn-satools-v0-34-server-administration-made-easy-1060.20621/";
	private double confVersion;
	public World world;
	public PluginManager pm;
	private final SAToolsPlayerListener playerListener = new SAToolsPlayerListener(
			this);

	/**
	 * Downloads the updated software from the server if possible. TODO needs
	 * re-looked at sometime
	 */
	private void downloadUpdate() {
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
			while ((numRead = in.read(buffer, 0, 1024)) >= 0) {
				out.write(buffer, 0, numRead);
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
		if (JOptionPane.showConfirmDialog(
				null,
				"Update downloaded, would you like to end the server"
						+ System.getProperty("line.separator")
						+ "so you may use the updated version?",
				"Update Finished", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			getServer().shutdown();
		} else {
			log.warning("SATools: You are running an older version of SATools!");
		}
	}

	/**
	 * Visits a URL in the default browser
	 * 
	 * @param location
	 *            URL to visit
	 */
	public void goToSite(String location) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(location));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * loads the configuration. Appends settings based on the configuration.
	 * TODO re-look
	 */
	private void loadConfig() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = getConfig();
		if (configFile.exists()) {
			checkUpdate = config.getBoolean("AutoUpdate", true);
			confVersion = Double.parseDouble(config.getString("Version"));
			if (updateChecked()) {
				log.info(Double.toString(confVersion));
				if (confVersion < Double.parseDouble(pdfFile.getVersion())) {
					if (JOptionPane
							.showConfirmDialog(
									null,
									"There are new updates added to SATools\nWould you like to view the changelog?",
									"SATools Updated",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						goToSite(threadURL);
					}
				}
			} else {
				log.warning("Could not communicate with the update server");
			}
		} else {
			try {
				log.severe("SATools: SATools: No configuration, creating one instead");
				configFile.createNewFile();
				config = getConfig();
				config.set("SATools",
						"#" + pdfFile.getName() + " " + pdfFile.getVersion());
				config.set("AutoUpdate", true);
				config.set("Version", Double.parseDouble(pdfFile.getVersion()));
				config.save(configFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDisable() {
		saveConfiguration();
	}

	@Override
	public void onEnable() {
		pdfFile = getDescription();
		pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener,
				Event.Priority.Normal, this);
		// Check data folder
		if (!getDataFolder().exists()) {
			if (getDataFolder().mkdirs()) {
				log.info("SATools: Data folder created successfully");
			} else {
				log.warning("SATools: Could not create data folder");
			}
		}
		loadConfig();
		world = getServer().getWorlds().get(0);
		// Create the GUI
		gui = new SAToolsGUI(this);
		gui.setTitle(pdfFile.getName() + " " + pdfFile.getVersion());
		gui.mnCheckForUpdates.setSelected(checkUpdate);
	}

	/**
	 * Saves the server configuration. TODO Re-look.
	 */
	private void saveConfiguration() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = getConfig();
		if (configFile.exists()) {
			config.set("AutoUpdate", true);
			config.set("Version", pdfFile.getVersion());
		} else {
			try {
				log.severe("SATools: SATools: No configuration, creating one instead");
				configFile.createNewFile();
				config = getConfig();
				config.set("SATools",
						"#" + pdfFile.getName() + " " + pdfFile.getVersion());
				config.set("AutoUpdate", true);
				config.set("Version", Double.parseDouble(pdfFile.getVersion()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		saveConfig();
	}

	/**
	 * Sends the specified string to the server
	 * 
	 * @param text
	 *            String to send to the server
	 */
	public void sendCommand(String text) {
		try {
			getServer().dispatchCommand(getServer().getConsoleSender(), text);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Checks for updates from the server
	 * 
	 * @return true if update checked, otherwise false
	 */
	public boolean updateChecked() {
		try {
			URL pluginInfo = new URL(
					"https://raw.github.com/OneThatWalks/SATools/master/plugin.yml");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					pluginInfo.openStream()));
			String strLine;
			while ((strLine = in.readLine()) != null) {
				if (strLine.contains("version: ")) {
					String[] tokens = strLine.split(" ");
					String version = tokens[1];
					if (Double.parseDouble(version) > Double
							.parseDouble(pdfFile.getVersion())) {
						if (JOptionPane
								.showConfirmDialog(
										null,
										"There is an update available!\nWould you like to download this update?",
										"Update Ready",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							downloadUpdate();
						} else {
							getServer().shutdown();
						}
					} else {
						log.info("Running latest version of SATools. ");
					}
				}
			}
		} catch (MalformedURLException e) {
			log.severe("SATools: Malformed URL");
			return false;
		} catch (IOException e) {
			log.severe("SATools: Could no connect");
			return false;
		}
		return true;
	}
}
