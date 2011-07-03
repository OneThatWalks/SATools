package onethatwalks.threads;

import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class GUIManager extends Thread {
	public static final Logger log = Logger.getLogger("Minecraft");
	Plugin plugin;

	public GUIManager(Plugin instance) {
		plugin = instance;
	}

	@Override
	public void run() {
		while (true) {
			// Player Info
			if (SAToolsGUI.player != null) { // If player is selected from
												// list
				Location player_loc = SAToolsGUI.player.getLocation();
				{ // Location
					SAToolsGUI.jLabel_PLAYERS_PLAYER_LOCATION_DATA.setText("( "
							+ player_loc.getBlockX() + ", "
							+ player_loc.getBlockY() + ", "
							+ player_loc.getBlockZ() + ")");
				}
				{ // Entity ID
					SAToolsGUI.jLabel_PLAYERS_PLAYER_ENTITYID_DATA
							.setText(Integer.toString(SAToolsGUI.player
									.getEntityId()));
				}
				{ // Health
					SAToolsGUI.jLabel_PLAYERS_PLAYER_HEALTH_DATA
							.setText(Integer.toString(SAToolsGUI.player
									.getHealth()));
				}
				{ // Current Item
					SAToolsGUI.jLabel_PLAYERS_PLAYER_ITEM_DATA
							.setText(SAToolsGUI.player.getItemInHand()
									.toString());
				}
				{ // Dead?
					SAToolsGUI.jLabel_PLAYERS_PLAYER_DEAD_DATA.setText(Boolean
							.toString(SAToolsGUI.player.isDead()));
					try {
						while (plugin.getServer()
								.getPlayer(SAToolsGUI.player.getDisplayName())
								.isDead()) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						// Nothing Happens
					}
				}
				{ // isOP ?
					SAToolsGUI.jLabel_PLAYERS_PLAYER_OP_DATA.setText(Boolean
							.toString(SAToolsGUI.player.isOp()));
				}
				{ // Sleep
					SAToolsGUI.jLabel_PLAYERS_PLAYER_SLEEP_DATA.setText(Boolean
							.toString(SAToolsGUI.player.isSleeping()));
				}
				{ // Sneak
					SAToolsGUI.jLabel_PLAYERS_PLAYER_SNEAK_DATA.setText(Boolean
							.toString(SAToolsGUI.player.isSneaking()));
				}
			}
			// END
			// Task Info
			if (SAToolsGUI.jList_SCHEDULE_TASKS.getSelectedValue() != null) {
				SAToolsGUI.jLabel_SCHEDULE_TASKS_INFO_NAME_DATA
						.setText(SATools.taskScheduler.getTask(
								SAToolsGUI.jList_SCHEDULE_TASKS
										.getSelectedValue().toString())
								.getName());
				SAToolsGUI.jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA.setText(String
						.valueOf(SATools.taskScheduler.getTask(
								SAToolsGUI.jList_SCHEDULE_TASKS
										.getSelectedValue().toString())
								.getExecutionTime()));
			} else {
				SAToolsGUI.jLabel_SCHEDULE_TASKS_INFO_NAME_DATA
						.setText("NO TASK");
				SAToolsGUI.jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA
						.setText("NO TASK");
			}
			// END
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}