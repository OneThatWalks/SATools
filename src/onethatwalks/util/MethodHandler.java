package onethatwalks.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

public class MethodHandler {

	private final Logger log = Logger.getLogger("Minecraft");

	public static enum Weather {
		CLEAR, STORM, THUNDER
	}

	/**
	 * Creates a new menu item in the JMenu_WORLDS menu
	 * 
	 * @param world
	 *            World to convert to menu item
	 * @return JMenuItem
	 */
	public JMenuItem newMenuItem(final World world) {
		JMenuItem temp = null;
		if (temp == null) {
			temp = new JMenuItem();
			temp.setText(world.getName());
			temp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SATools.world = world;
				}
			});
		}
		return temp;
	}

	/**
	 * Checks the worlds weather conditions
	 * 
	 * @return the current world weather conditions
	 */
	public String checkConditions() {
		try {
			if (SATools.world != null) {
				if (SATools.world.hasStorm()) {
					if (SATools.world.isThundering()) {
						return "Thunder";
					}
					return "Storm";
				} else {
					return "Clear";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Cannot Compute";
	}

	/**
	 * My method to give a player a specified item
	 * 
	 * @param p
	 *            Player to give item to
	 * @param item
	 *            The item name
	 * @param amount
	 *            Amount to give
	 * @return true if item given, otherwise false.
	 */
	public boolean doGiveItem(Player p, String item, int amount) {
		if (p != null) {
			if (item != null) {
				if (amount > 0) {
					short damage = 0;
					if (SAToolsGUI.items.get(item) == Material.WOOL) { // Wool
						// damage
						// for
						// differnt colors
						String[] values = { "White", "Orange", "Magenta",
								"Light Blue", "Yellow", "Light Green", "Pink",
								"Gray", "Light Gray", "Cyan", "Purple", "Blue",
								"Brown", "Dark Green", "Red", "Black" };
						HashMap<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < values.length; i++) {
							map.put(i, values[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What color?", "Wool Color Selection",
								JOptionPane.INFORMATION_MESSAGE, null, map
										.values().toArray(), map.get(0));
						for (int o : map.keySet()) {
							if (map.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == values.length - 1) {
								log.severe("SATools.SAToolsGUI: Cannot get integer");
								return false;
							}
						}
					} else if (SAToolsGUI.items.get(item) == Material.LOG) { // Wood
						// damage
						// for
						// different
						// types
						String[] values = { "Oak/Regular", "Spruce/Pine",
								"Birch" };
						HashMap<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < values.length; i++) {
							map.put(i, values[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What type?", "Wood Type Selection",
								JOptionPane.INFORMATION_MESSAGE, null, map
										.values().toArray(), map.get(0));
						for (int o : map.keySet()) {
							if (map.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == values.length - 1) {
								log.severe("SATools.SAToolsGUI: Cannot get integer");
								return false;
							}
						}
					} else if (SAToolsGUI.items.get(item) == Material.STEP) { // Slabs
						// material
						String[] values = { "Stone", "Sandstone", "Wooden",
								"Cobblestone" };
						HashMap<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < values.length; i++) {
							map.put(i, values[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What materal slab do you want?",
								"Slab Selection",
								JOptionPane.INFORMATION_MESSAGE, null, map
										.values().toArray(), map.get(0));
						for (int o : map.keySet()) {
							if (map.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == values.length - 1) {
								log.severe("SATools.SAToolsGUI: Cannot get integer");
								return false;
							}
						}
					} else if (SAToolsGUI.items.get(item) == Material.INK_SACK) { // Different
						// types of
						// dye's
						String[] values = { "Ink Sack", "Rose Red",
								"Cactus Green", "Cocoa Beans", "Lapis Lazuli",
								"Purple Dye", "Cyan Dye", "Light Gray Dye",
								"Gray Dye", "Pink Dye", "Lime Dye",
								"Dandelion Yellow", "Light Blue Dye",
								"Magenta", "Orange Dye", "Bone Meal" };
						HashMap<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < values.length; i++) {
							map.put(i, values[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What color dye do you want?",
								"Dye Color Selection",
								JOptionPane.INFORMATION_MESSAGE, null, map
										.values().toArray(), map.get(0));
						for (int o : map.keySet()) {
							if (map.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == values.length - 1) {
								log.severe("SATools.SAToolsGUI: Cannot get integer");
								return false;
							}
						}
					} else if (SAToolsGUI.items.get(item) == Material.SAPLING) { // Different
						// sapplings
						String[] values = { "Oak/Regular", "Spruce/Pine",
								"Birch" };
						HashMap<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < values.length; i++) {
							map.put(i, values[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What type of sapling do you want?",
								"Sapling Selelction",
								JOptionPane.INFORMATION_MESSAGE, null, map
										.values().toArray(), map.get(0));
						for (int o : map.keySet()) {
							if (map.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == values.length - 1) {
								log.severe("SATools.SAToolsGUI: Cannot get integer");
								return false;
							}
						}
					}
					org.bukkit.inventory.ItemStack is = new org.bukkit.inventory.ItemStack(
							SAToolsGUI.items.get(item), amount, damage);
					p.getInventory().addItem(is);
					{ // The heart of this method
						if (SAToolsGUI.player.getInventory().contains(is)) {
							return true;
						}
						return false;
					}
				} else {
					log.severe("SATools.SAToolsGUI: doGiveItem(): no amount or negative count");
					return false;
				}
			} else {
				log.severe("SATools.SAToolsGUI: doGiveItem(): no item or invalid type");
				return false;
			}
		} else {
			log.severe("SATools.SAToolsGUI: doGiveItem(): no player or invalid player");
			return false;
		}
	}

	/**
	 * Creates a light post at the specified location
	 * 
	 * @param loc
	 *            Location to spawn
	 * @return true if spawned, otherwise false
	 */
	public boolean createLightPost(Location loc) {
		try {
			Block block_1 = SATools.world.getBlockAt(loc);
			Block block_2 = SATools.world.getBlockAt(loc.getBlockX(),
					loc.getBlockY() + 1, loc.getBlockZ());
			Block block_3 = SATools.world.getBlockAt(loc.getBlockX(),
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
	public int random(int Min, int Max) {
		return Min + (int) (Math.random() * ((Max - Min) + 1));
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
	public void spawnCreature(Location loc, CreatureType type) {
		try {
			if (SATools.world.spawnCreature(loc, type) == null) {
				log.severe("SATools: SATools: Failed to create "
						+ type.getName() + " at " + loc.toString());
			} else {
				log.info("SATools: Spawned " + type.getName() + " at "
						+ loc.toString());
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
	public void spawnObject(Location location, String object) {
		try {
			if (object.trim().equalsIgnoreCase("tree")) {
				if (SATools.world.generateTree(
						location,
						TreeType.values()[random(0,
								TreeType.values().length - 1)])) {
					log.info("SATools: Tree created successfully");
				} else {
					log.severe("SATools: Failed to create tree");
				}
			} else if (object.trim().equalsIgnoreCase("lightning")) {
				if (SATools.world.strikeLightning(location) != null) {
					log.info("SATools: Lightning created successfully");
				} else {
					log.severe("SATools: Failed to create lightning");
				}
			} else if (object.trim().equalsIgnoreCase("light post")) {
				if (createLightPost(location)) {
					log.info("SATools: Lightning created successfully");
				} else {
					log.severe("SATools: Failed to create lightning");
				}
			} else {
				log.severe("SATools: Failed to spawn object " + object);
				SATools.world.getBlockAt(location).setType(
						org.bukkit.Material.TNT);
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}

	}

	/**
	 * setWeather
	 * 
	 * Sets the weather for the current world
	 * 
	 * Weather to set to
	 */
	public void setWeather(Weather type) {
		if (type.equals(Weather.CLEAR)) {
			SATools.world.setStorm(false);
			SATools.world.setThundering(false);
		} else if (type.equals(Weather.STORM)) {
			SATools.world.setStorm(true);
		} else if (type.equals(Weather.THUNDER)) {
			SATools.world.setStorm(true);
			SATools.world.setThundering(true);
		} else {
			log.severe("SATools: Error setting weather!!!");
		}
	}

	public List<String> scanArray(List<String> array) {
		List<String> result = array;
		for (int i = 0; i < result.size(); i++) {
			String temp = array.get(i);
			int matches = 0;
			for (Object o : array) {
				if (o.equals(temp)
						&& result.indexOf(o) != result.lastIndexOf(o)) {
					matches++;
					result.set(result.indexOf(o), "Null");
				}
			}
		}
		while (result.contains("Null")) {
			result.remove("Null");
		}
		return result;
	}

}
