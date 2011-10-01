package onethatwalks.satools;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class SATools_final extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	private PluginDescriptionFile pdfFile;
	private SAToolsGUI_final gui;
	public Configuration config;
	public boolean checkUpdate;
	public int betaNum = 0;
	private String threadURL = "http://forums.bukkit.org/threads/admn-satools-v0-34-server-administration-made-easy-1060.20621/";

	@Override
	public void onDisable() {
		saveConfig();
	}

	@Override
	public void onEnable() {
		pdfFile = getDescription();
		loadConfig();
		// Check data folder
		if (!getDataFolder().exists()) {
			if (getDataFolder().mkdirs()) {
				log.info("SATools: Data folder created successfully");
			} else {
				log.warning("SATools: Could not create data folder");
			}
		}
		// Create the GUI
		gui = new SAToolsGUI_final(this);
		gui.setTitle(pdfFile.getName() + " " + pdfFile.getVersion() + "Beta "
				+ betaNum);
		gui.mnCheckForUpdates.setSelected(checkUpdate);
	}

	private void loadConfig() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = new Configuration(configFile);
		config.load();
		if (configFile.exists()) {
			checkUpdate = config.getBoolean("Auto Update", true);
			if (config.getInt("Beta", -1) < betaNum) {
				if (JOptionPane
						.showConfirmDialog(
								null,
								"Would you like to go to the thread to see the changes in this release?",
								"SATools Updated!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					goToThread();
				}
			} else if (config.getInt("Beta", -1) == betaNum) {
				log.info("SATools is up to date");
			} else {
				log.warning("Futuristic SATools in use!!!!");
			}
		} else {
			try {
				log.severe("SATools: SATools: No configuration, creating one instead");
				configFile.createNewFile();
				config = new Configuration(configFile);
				config.setHeader(pdfFile.getName() + " " + pdfFile.getVersion()
						+ "Beta " + betaNum);
				config.setProperty("Auto Update", true);
				config.setProperty("Beta", betaNum);
				if (config.save()) {
					log.info("Configuration Successfully Saved");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveConfig() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		config = new Configuration(configFile);
		config.load();
		if (configFile.exists()) {
			config.setProperty("Auto Update", true);
			config.setProperty("Beta", betaNum);
		} else {
			try {
				log.severe("SATools: SATools: No configuration, creating one instead");
				configFile.createNewFile();
				config = new Configuration(configFile);
				config.setHeader(pdfFile.getName() + " " + pdfFile.getVersion()
						+ "Beta " + betaNum);
				config.setProperty("Auto Update", true);
				config.setProperty("Beta", betaNum);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (config.save()) {
			log.info("Configuration Successfully Saved");
		}
	}

	public void goToThread() {
		try {
			java.awt.Desktop.getDesktop()
					.browse(java.net.URI.create(threadURL));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
