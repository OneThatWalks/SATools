package onethatwalks.tasker;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeMap;

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

	public TaskScheduler(SATools instance) {
		plugin = instance;
		Thread t = new Thread(this);
		t.start();
	}

	protected void addTask(Task task) {
		tasksQueue.put(task.name, task);
		scheduleTask(task);
	}

	private void scheduleTask(Task task) {
		if (!tasks.isEmpty()) {
			// TODO
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
			int taskQueue = 0;
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

			if (sleepTime < 50) {
				sleepTime = 50;
			} else if (sleepTime > 60000) {
				sleepTime = 60000;
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
