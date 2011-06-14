package tips48.restartnow;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class RestartNow implements Restart {
	/*
	 * This class should be where all the code is.
	 */
	public void restartServer(String restartMessage, String kickMessage,
			ChatColor color) {
		restartServer(restartMessage, kickMessage, color);
	}

	/*
	 * This method kicks everyone on the server String kickMessage is the string
	 * the users client recieves when it is kicked.
	 */
	public void kickAll(String kickMessage) {
		kickAll(kickMessage);
	}

	/*
	 * Another easy method, it disables a plugin. USE CARFULLY Plugin p is the
	 * plugin you want disabled.
	 */
	public void disablePlugin(Plugin p) {
		disablePlugin(p);
	}

	/*
	 * This method enables a plugin. It might give an error if the plugin is
	 * already enabled. Plugin p is the plugin you want enabled.
	 */
	public void enablePlugin(Plugin p) {
		enablePlugin(p);
	}

	/*
	 * This method reloads a plugin. Use with caution! Basically, this method
	 * disables the plugin, waits until the plugin is fully disabled, and
	 * enables it again. Plugin p is the plugin you want reloaded.
	 */
	public void restartPlugin(Plugin p) {
		restartPlugin(p);
	}

	/*
	 * Reloads the server.
	 */
	public void reloadServer() {
		reloadServer();
	}

	/*
	 * Saves all worlds
	 */
	public void saveWorlds() {
		saveWorlds();
	}
}
