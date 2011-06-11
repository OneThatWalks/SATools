package onethatwalks.tasker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.bukkit.command.ConsoleCommandSender;

import onethatwalks.satools.SATools;

public class Task implements Runnable {

	private File macro;
	private long time;
	String name;
	private String[] instructions;
	private Exception InstructionSetInvalidException;
	private SATools plugin;
	public static final Logger log = Logger.getLogger("Minecraft");

	public Task(String name, long time, String file, SATools instance) {
		this.macro = new File(file);
		this.time = time;
		this.name = name;
		plugin = instance;
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
					e.printStackTrace();
				}
			}
		}
	}

	private void doInstruction(String string) throws Exception {
		if (!string.isEmpty()) {
			if (string.startsWith("/")) {
				doCommand(string);
			} else {

			}
		} else {
			throw InstructionSetInvalidException;
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
