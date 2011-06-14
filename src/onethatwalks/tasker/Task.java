package onethatwalks.tasker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

public class Task implements Runnable {

	private File macro;
	private long time;
	String name;
	private String[] instructions;
	private Exception InvalidInstructionException;
	private SATools plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	private Exception NullInstructionException;
	public String[] raw_commands = { "serverSay", "playerSay", "setTime",
			"setWeather", "spawnMob", "spawnObject", "givePlayer",
			"playerHealth" };
	public ArrayList<String> commands = new ArrayList<String>();

	public Task(String name, long time, String file, SATools instance) {
		this.macro = new File(file);
		this.time = time;
		this.name = name;
		plugin = instance;
		for (String c : raw_commands) {
			commands.add(c);
		}
	}

	public long getExecutionTime() {
		return time;
	}

	public Task getTask() {
		return this;
	}

	@Override
	public void run() {
		instructions = createLocalizedInstructions(macro);
		if (instructions != null && instructions.length != 0) {
			for (int i = 0; i < instructions.length; i++) {
				try {
					doInstruction(instructions[i]);
				} catch (Exception e) {
					log.severe("Error on line: " + (i + 1) + " of "
							+ macro.getName());
					e.printStackTrace();
				}
			}
		}
	}

	private void doInstruction(String string) throws Exception { // TODO add
																	// line
																	// number
																	// regognition.
		if (!string.isEmpty()) {
			if (string.startsWith("/")) {
				doCommand(string);
			} else if (string.startsWith(commands.get(0))) { // serverSay
				String[] tokens = string.split(" ");
				String message = tokens[1];
				plugin.getServer().broadcastMessage(message);
			} else if (string.startsWith(commands.get(1))) { // playerSay
				String[] tokens = string.split(" ");
				String message = tokens[1];
				String player = tokens[2];
				// check if player online
				if (plugin.getServer().getPlayer(player) != null) {
					plugin.getServer().getPlayer(player).sendMessage(message);
				} else {
					log.warning(player
							+ " is not online.  Skipping instruction");
				}
			} else if (string.startsWith(commands.get(2))) { // setTime
				String[] tokens = string.split(" ");
				String arg = tokens[1];
				if (isLong(arg)) {
					SATools.world.setTime(Long.parseLong(arg));
				} else {
					log.warning("Your instruction was not a valid time.");
				}
			} else if (string.startsWith(commands.get(3))) { // setWeather
				String[] tokens = string.split(" ");
				String arg = tokens[1];
				if (arg.equalsIgnoreCase("clear")) {
					SAToolsGUI.jButton_MAIN_WEATHER_CLEAR.doClick();
				} else if (arg.equalsIgnoreCase("storm")) {
					SAToolsGUI.jButton_MAIN_WEATHER_STORM.doClick();
				} else if (arg.equalsIgnoreCase("thunder")) {
					SAToolsGUI.jButton_MAIN_WEATHER_THUNDER.doClick();
				}
			} else if (string.startsWith(commands.get(4))) {
				String[] tokens = string.split(" ");
				String creatureText = tokens[1];
				String where = tokens[2];
				String[] xyz = where.split(",");
				CreatureType creature = getCreature(creatureText);
				if (creature != null) {
					if (SAToolsGUI.isNumeric(xyz[0])
							&& SAToolsGUI.isNumeric(xyz[1])
							&& SAToolsGUI.isNumeric(xyz[2])) {
						int x = Integer.parseInt(xyz[0]);
						int y = Integer.parseInt(xyz[1]);
						int z = Integer.parseInt(xyz[2]);
						Location loc = new Location(SATools.world, x, y, z);
						SATools.world.spawnCreature(loc, creature);
					} else {
						log.warning("x,y,z invalid, skipping instruction.");
					}
				} else {
					log.warning("Creature not found in instruction. Skipping");
				}
			} else if (string.startsWith(commands.get(5))) { // spawnObject
				String[] tokens = string.split(" ");
				String object = null;
				String where = null;
				if (tokens.length == 3) {
					object = tokens[1];
					where = tokens[2];
				} else if (tokens.length == 4) {
					object = tokens[1] + " " + tokens[2];
					where = tokens[3];
				}
				String[] xyz = where.split(",");
				if (object.isEmpty() || object == null) {
					if (SAToolsGUI.isNumeric(xyz[0])
							&& SAToolsGUI.isNumeric(xyz[1])
							&& SAToolsGUI.isNumeric(xyz[2])) {
						int x = Integer.parseInt(xyz[0]);
						int y = Integer.parseInt(xyz[1]);
						int z = Integer.parseInt(xyz[2]);
						Location loc = new Location(SATools.world, x, y, z);
						SATools.spawnObject(loc, object);
					} else {
						log.warning("x,y,z invalid, skipping instruction.");
					}
				} else {
					log.warning("Object not found in instruction. Skipping");
				}
			} else if (string.startsWith(commands.get(6))) { // givePlayer
				String[] tokens = string.split(" ");
				String player = tokens[1];
				String count = tokens[2];
				String item = tokens[3];
				if (!player.isEmpty()) {
					if (plugin.getServer().getPlayer(player) != null) {
						Player p = plugin.getServer().getPlayer(player);
						plugin.getServer().dispatchCommand(
								new ConsoleCommandSender(plugin.getServer()),
								"give " + p.getDisplayName() + " " + item + " "
										+ count);
					} else {
						log.info("Player doesn't exsist or is offline, skipping instruction");
					}
				} else {
					log.warning("There was no player");
				}
			} else if (string.startsWith(commands.get(7))) { // playerHealth
				String[] tokens = string.split(" ");
				String player = tokens[1];
				String hearts = tokens[2]; // 0-20
				if (!player.isEmpty()) {
					if (plugin.getServer().getPlayer(player) != null) {
						if (SAToolsGUI.isNumeric(hearts)) {
							Player p = plugin.getServer().getPlayer(player);
							p.setHealth(Integer.parseInt(hearts));
						} else {
							log.warning("Not a valid health value, skipping instruction");
						}
					} else {
						log.info("Player doesn't exsist or is offline, skipping instruction");
					}
				} else {
					log.warning("There was no player");
				}
			} else {
				throw InvalidInstructionException;
			}
		} else {
			throw NullInstructionException;
		}
	}

	private CreatureType getCreature(String creature) {
		for (CreatureType ct : CreatureType.values()) {
			if (creature.equalsIgnoreCase(ct.getName())) {
				return ct;
			}
		}
		return null;
	}

	private boolean isLong(String arg) {
		try {
			Long.parseLong(arg);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private void doCommand(String string) {
		plugin.getServer().dispatchCommand(
				new ConsoleCommandSender(plugin.getServer()), string);
	}

	private String[] createLocalizedInstructions(File file) {
		String[] result = {};
		try {
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine;
			int line = 1;
			while ((strLine = br.readLine()) != null) {
				result[line - 1] = strLine;
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
