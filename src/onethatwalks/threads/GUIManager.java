package onethatwalks.threads;

import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;
import onethatwalks.util.MethodHandler;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class GUIManager extends Thread {
	public static final Logger log = Logger.getLogger("Minecraft");
	Plugin plugin;
	MethodHandler methods = SATools.methods;

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
			// Worlds Selection - World info on main screen
			if (SAToolsGUI.jMenu_WORLDS.getMenuComponentCount() == 0) {
				for (int i = 0; i < plugin.getServer().getWorlds().size(); i++) {
					SAToolsGUI.jMenu_WORLDS.add(methods.newMenuItem(plugin
							.getServer().getWorlds().get(i)));
					log.info("[DEBUG] SATools.GUIManager: found "
							+ plugin.getServer().getWorlds().get(i).getName()
							+ ", size = "
							+ SAToolsGUI.jMenu_WORLDS.getMenuComponentCount());
				}
			}
			if (!SAToolsGUI.jLabel_MAIN_WORLD.getText().equalsIgnoreCase(
					"world: " + SATools.world.getName())) {
				SAToolsGUI.jLabel_MAIN_WORLD.setText("World: "
						+ SATools.world.getName());
			}
			// END
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}