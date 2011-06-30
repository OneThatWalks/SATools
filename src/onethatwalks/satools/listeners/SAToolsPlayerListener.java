package onethatwalks.satools.listeners;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

import org.bukkit.ChatColor;
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
						if (SATools.gods.contains(player.getDisplayName())) {
							plugin.getServer().broadcastMessage(
									ChatColor.GOLD + player.getDisplayName()
											+ " is a god");
						}
						{ // Welcome Message info
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
							// List Add
							SATools.log.info(player + " should be in the list");
							SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.addElement(player.getDisplayName());
							if (SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.getSize() == 1) {
								SAToolsGUI.jList_PLAYERS_PLAYERS
										.setSelectedValue(
												player.getDisplayName(), true);
								if (SATools.gods.contains(player)) {
									SAToolsGUI.jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS
											.setSelected(true);
								}
							}
							// Console Message
							SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.addElement(player.getDisplayName());
							if (SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_CONSOLE_MESSAGE
										.setSelectedItem(player
												.getDisplayName());
							}
							// Spawn Creature
							SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION
									.addElement(player);
							if (SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_SPAWN_LOCATION
										.setSelectedItem(player);
							}
							// Spawn Object
							SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT
									.addElement(player);
							if (SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_SPAWN_LOCATION_OBJECT
										.setSelectedItem(player);
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
						String otherGuy = null;

						SAToolsGUI.player = null;
						{ // GUI Options removal
							// Player List
							SATools.log.info(player
									+ " should be removed from the list");
							if (SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.size() == 0) {
								SAToolsGUI.player = null;
								SAToolsGUI.jList_PLAYERS_PLAYERS
										.setSelectedIndex(-1);
								SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
										.removeElement(player.getDisplayName());
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
								SAToolsGUI.jPanel_PLAYERS_MODIFY
										.setVisible(false);
							} else if (SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
									.size() > 1
									&& SAToolsGUI.jList_PLAYERS_PLAYERS
											.getSelectedValue() == player
											.getDisplayName()) {
								for (int i = 0; i < plugin.getServer()
										.getOnlinePlayers().length; i++) {
									if (plugin.getServer().getOnlinePlayers()[i]
											.getDisplayName() != player
											.getDisplayName()) {
										otherGuy = plugin.getServer()
												.getOnlinePlayers()[i]
												.getDisplayName();
									}
								}
								SAToolsGUI.jList_PLAYERS_PLAYERS
										.setSelectedValue(otherGuy, true);
								SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
										.removeElement(player.getDisplayName());

							} else {
								SAToolsGUI.DefaultListModel_PLAYERS_PLAYERS
										.removeElement(player.getDisplayName());
							}
							// Console Message
							if (SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_CONSOLE_MESSAGE
										.setSelectedIndex(-1);
							}
							SAToolsGUI.DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
									.removeElement(player.getDisplayName());
							// Spawn Creature
							if (SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_SPAWN_LOCATION
										.setSelectedIndex(-1);
							}
							SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION
									.removeElement(player);
							// Spawn Object
							if (SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT
									.getSize() == 1) {
								SAToolsGUI.jComboBox_MAIN_SPAWN_LOCATION_OBJECT
										.setSelectedIndex(-1);
							}
							SAToolsGUI.defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT
									.removeElement(player);
						}
					}
				}, 20);
	}

}
