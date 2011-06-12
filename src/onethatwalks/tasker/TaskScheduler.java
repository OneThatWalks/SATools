package onethatwalks.tasker;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.logging.Logger;

import onethatwalks.satools.SATools;

/**
 * Scheduling class for SATools - Heavy Credit from the people at Bukkit.
 * 
 * @author OneThatWalks
 * 
 */
public class TaskScheduler implements Runnable {
	private TreeMap<String, Task> tasksQueue = new TreeMap<String, Task>();
	private LinkedList<Task> tasks = new LinkedList<Task>();
	private SATools plugin;
	public static final Logger log = Logger.getLogger("Minecraft");
	Thread t;
	int taskQueue = 0;

	public TaskScheduler(SATools instance) {
		plugin = instance;
		t = new Thread(this);
		t.start();
	}

	protected void addTask(Task task) {
		t.interrupt();
		tasksQueue.put(task.name, task);
		scheduleTask(task);
		t.notify();
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
				tasks.add(where, task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			tasks.add(task);
		}
	}

	public void registerTask(String name, long time, String file) {
		if (getTask(name) != null) {
			Task newTask = new Task(name, time, file, plugin);
			addTask(newTask);
		}
	}

	private Task getTask(String name) {
		if (tasksQueue.containsKey(name)) {
			return tasksQueue.get(name);
		}
		return null;
	}

	public Collection<Task> getTasks() {
		return tasksQueue.values();
	}

	public void killTask(Task task) {
		tasks.remove(task.name);
	}

	public void killAllTasks() {
		tasks.clear();
	}

	@Override
	public void run() {
		while (true) {
			boolean stop = false;
			long taskTime = -1;
			long currentTime = -1;
			Task first = null;
			do {
				synchronized (tasks) {
					first = null;
					if (!tasks.isEmpty()) {
						first = tasks.get(taskQueue);
						if (first != null) {
							currentTime = SATools.time;

							taskTime = first.getExecutionTime();

							if (currentTime >= taskTime
									&& currentTime <= taskTime + 100) {
								processTask(first);
								if (taskQueue == tasks.size() - 1) {
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
			if (first == null) {
				sleepTime = 6000;
			} else {
				currentTime = SATools.time;
				sleepTime = (taskTime - currentTime);
			}
			synchronized (tasks) {
				try {
					tasks.wait(sleepTime);
				} catch (InterruptedException ie) {
				}
			}
		}
	}

	private void processTask(Task task) {
		task.run();
	}
}
