package onethatwalks.satools;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Handle events for all Player related events
 * 
 * @author OneThatWalks
 */
public class SAToolsPlayerListener extends PlayerListener {
	private final SATools plugin;
	public static Player player;

	public SAToolsPlayerListener(SATools instance) {
		plugin = instance;
	}

	public void onPlayerJoin(final PlayerJoinEvent event) {
		plugin.getServer().getScheduler()
				.scheduleAsyncDelayedTask(plugin, new Runnable() {
					public void run() {
						player = event.getPlayer(); // register player that
													// joined
						{ // Welcome MEssage info
							Player[] op = plugin.getServer().getOnlinePlayers();
							String opString = "";
							for (int i = 0; i < op.length; i++) {
								Player playerRaw = op[i];
								String playerLoc = "x="
										+ playerRaw.getLocation().getBlockX()
										+ ", y="
										+ playerRaw.getLocation().getBlockY()
										+ ", z="
										+ playerRaw.getLocation().getBlockZ();
								String playerString = playerRaw
										.getDisplayName()
										+ " at ("
										+ playerLoc
										+ "), ";
								opString = opString + playerString;
							}
							player.sendMessage("Welcome to "
									+ plugin.getServer().getServerName() + ", "
									+ player.getDisplayName()
									+ ", Have Fun and Play Nice!");
							player.sendMessage("Connected users: " + opString);
						}
						{ // Run GUI Options
							SATools.log.info(player + " should be in the list");
							SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.addElement(player.getDisplayName());
							if (SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.getSize() == 0) {
								SAToolsGUI.jList_PLAYERS_PLAYERS
										.setSelectedValue(
												player.getDisplayName(), true);
							}
							SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.addElement(player.getDisplayName());
							if (SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.getSize() == 0) {
								SAToolsGUI.jComboBox_MAIN_CONSOLE_MESSAGE
										.setSelectedItem(player
												.getDisplayName());
							}
						}
					}
				}, 20);
	}

	public void onPlayerQuit(final PlayerQuitEvent event) {
		plugin.getServer().getScheduler()
				.scheduleAsyncDelayedTask(plugin, new Runnable() {
					public void run() {
						player = event.getPlayer(); // register player to quit
						SAToolsGUI.player = null;
						{ // GUI Options removal
							SATools.log.info(player
									+ " should be removed from the list");
							SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.removeElement(player.getDisplayName());
							if (SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.size() == 0) {
								SAToolsGUI.jList_PLAYERS_PLAYERS
										.setSelectedIndex(-1);
								SAToolsGUI.jLabel_PLAYERS_PLAYER_SNEAK_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_SLEEP_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_OP_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_DEAD_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_ITEM_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_HEALTH_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_ENTITYID_DATA
										.setText("Null");
								SAToolsGUI.jLabel_PLAYERS_PLAYER_LOCATION_DATA
										.setText("Null");
								SAToolsGUI.jPanel_PLAYERS_PLAYER
										.setVisible(false);
							}
							SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.removeElement(player.getDisplayName());
							if (SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.getSize() == 0) {
								SAToolsGUI.jComboBox_MAIN_CONSOLE_MESSAGE
										.setSelectedIndex(-1);
							}
						}
					}
				}, 20);
	}

}
