package onethatwalks.tasker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;

/**
 * Scheduling class for SATools - Heavy Credit from the people at Bukkit.
 * 
 * @author OneThatWalks
 * 
 */
public class TaskScheduler implements Runnable {
	private LinkedList<Task> tasks = new LinkedList<Task>();
	private Plugin plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	Thread t;
	int taskQueue = 0;
	public String macroFolder;

	public TaskScheduler(Plugin instance) {
		plugin = instance;
		macroFolder = plugin.getDataFolder() + File.separator + "macros"
				+ File.separator;
		t = new Thread(this);
		t.start();
		init();
	}

	private void init() {
		try {
			File mf = new File(macroFolder);
			if (!mf.exists()) {
				log.info("No macro folder found, making one instead.");
				mf.mkdir();
			} else {
				log.info("Macro directory found, loading...");
				File[] potentialMacros = mf.listFiles();
				for (File f : potentialMacros) {
					String file = f.getName().trim();
					String[] tokens = file.split("\\.");
					String name = tokens[0].trim();
					String type = tokens[1].trim();
					if (!type.equalsIgnoreCase("macro")) {
						continue;
					}
					InputStream is = new FileInputStream(f);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					String strLine;
					int lineNumber = 1;
					long time = -1;
					// Read File Line By Line
					while ((strLine = br.readLine()) != null) {
						if (lineNumber == 1) {
							String rawTime = strLine.replaceFirst("#", "");
							time = Long.parseLong(rawTime);
						}
						lineNumber++;
					}
					is.close();
					registerTask(name, time, f.getPath());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void scheduleTask(Task task) {
		if (!tasks.isEmpty()) {
			long theTime = task.getExecutionTime();
			int where = -1;
			for (Task tt : tasks) {
				long compareTime = tt.getExecutionTime();
				if (theTime >= compareTime) {
					if (tasks.indexOf(tt) != tasks.size() - 1) {
						where = tasks.indexOf(tt) + 1;
						continue;
					} else {
						where = tasks.indexOf(tt) + 1;
					}
				} else if (theTime < compareTime) {
					if (tasks.indexOf(tt) != 0) {
						where = tasks.indexOf(tt) - 1;
						continue;
					} else {
						where = 0;
					}
				} else {
					log.severe("Error: scheduleTask(Task task) TaskScheduler.class");
				}
			}
			try {
				if (taskQueue > where) {
					taskQueue = taskQueue++;
				}
				tasks.add(where, task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			tasks.add(task);
		}
	}

	public void registerTask(String name, long time, String file) {
		if (getTask(name) == null) {
			Task newTask = new Task(name, time, file, plugin);
			SAToolsGUI.defaultListModel_SCHEDULE_TASKS.addElement(newTask
					.getName());
			scheduleTask(newTask);
		}
	}

	public Task getTask(String name) {
		for (Task t : getTasks()) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public LinkedList<Task> getTasks() {
		return tasks;
	}

	public void killTask(Task task) {
		tasks.remove(task.getName());
		SAToolsGUI.defaultListModel_SCHEDULE_TASKS
				.removeElement(task.getName());
		task.getFile().delete();
		task = null;
	}

	public void killAllTasks() {
		SAToolsGUI.defaultListModel_SCHEDULE_TASKS.removeAllElements();
		tasks.clear();
	}

	@Override
	public void run() {
		boolean stop = false;
		long taskTime = -1;
		long currentTime = -1;
		Task task = null;
		while (true) {
			if (!tasks.isEmpty()) {
				do {
					synchronized (tasks) {
						task = null;
						if (!tasks.isEmpty()) {
							task = tasks.get(taskQueue);
							if (task != null) {
								currentTime = SATools.time;

								taskTime = task.getExecutionTime();

								if (currentTime >= taskTime
										&& currentTime <= taskTime + 100) {
									processTask(task);
									if (taskQueue == tasks.size() - 1) {
										log.info("end of task queue.");
										taskQueue = 0;
									} else {
										taskQueue++;
									}
								} else {
									stop = true;
								}
							} else {
								stop = true;
							}
						} else {
							stop = true;
						}
					}
				} while (!stop);

				long sleepTime = 0;
				if (task == null) {
					sleepTime = 60000;
				} else {
					currentTime = SATools.time;
					sleepTime = (taskTime - currentTime) * 50 + 25;
				}
				if (sleepTime <= -1) {
					sleepTime = 60000;
				}
				synchronized (tasks) {
					try {
						log.info(task.getName());
						log.info(Long.toString(sleepTime));
						tasks.wait(sleepTime);
					} catch (InterruptedException ie) {
					}
				}
			}
		}
	}

	private void processTask(Task task) {
		task.run();
	}
}
