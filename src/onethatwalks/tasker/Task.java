package onethatwalks.tasker;

import java.io.File;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;

public class Task implements Runnable {

	private File macro;
	private long time;
	String name;
	private final TaskScheduler tasks = SATools.tasks;
	public static final Logger log = Logger.getLogger("Minecraft");

	public Task(String name, long time, String file) {
		this.macro = new File(file);
		this.time = time;
		this.name = name;
	}

	public long getExecutionTime() {
		return time;
	}

	public Task getTask() {
		return this;
	}

	@Override
	public void run() {

	}

}
